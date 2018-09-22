package com.cll.redis;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cll.util.JsonUtils;
import com.cll.dao.JedisClient;

@Aspect
@Component
public class CacheInterceptor {

	@Autowired
	JedisClient jedisClient;

	// 前置由于数据库数据变更 清理redis缓存
	@Before("@annotation(redisEvict)")
	public void doBefore(JoinPoint jp, RedisEvict redisEvict) {
		try {
			String modelName = redisEvict.type().getName();
			// 清除对应缓存
			jedisClient.del(modelName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("缓存服务器出现问题");
		}
	}

	// 配置环绕方法
	@Around("@annotation(redisCache)")
	public Object doAround(ProceedingJoinPoint pjp, RedisCache redisCache) throws Throwable {
		Class<?> modelType = redisCache.type();
		// 去Redis中看看有没有我们的数据 包名+ 类名 + 方法名 + 参数(多个)
		String cacheKey = getCacheKey(pjp);
		System.out.println(cacheKey);
		String value = null;
		try {// 当取redis发生异常时，为了不影响程序正常执行，需要try..catch()...
				// 检查redis中是否有缓存
			value = jedisClient.hget(modelType.getName(), cacheKey);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("缓存服务器出现问题");
		}
		// result是方法的最终返回结果
		Object result = null;
		if (null == value) {
			// 缓存未命中
			System.out.println("缓存未命中");
			// 后端查询数据
			result = pjp.proceed();
			try {// 当取redis发生异常时，为了不影响程序正常执行，需要try..catch()...
					// 序列化结果放入缓存
				String json = serialize(result);
				jedisClient.hset(modelType.getName(), cacheKey, json);
				if (redisCache.expire() > 0) {
					jedisClient.expire(cacheKey, redisCache.expire());// 设置缓存时间
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("缓存服务器出现问题");
			}
		} else {
			try {// 当数据转换失败发生异常时，为了不影响程序正常执行，需要try..catch()...
					// 得到被代理方法的返回值类型
				Class<?> returnType = ((MethodSignature) pjp.getSignature()).getReturnType();
				// 把json反序列化
				result = deserialize(value, returnType, modelType);
				// 缓存命中
				System.out.println("缓存命中");
			} catch (Exception e) {
				// 数据转换失败，到后端查询数据
				result = pjp.proceed();
				e.printStackTrace();
				System.out.println("缓存命中,但数据转换失败...");
			}
		}
		return result;
	}

	private Object deserialize(String jsonString, Class<?> clazz, Class<?> modelType) {
		// 序列化结果应该是List对象
		if (clazz.isAssignableFrom(List.class)) {
			return JsonUtils.jsonToList(jsonString, modelType);
		}
		// 序列化结果是普通对象
		return JsonUtils.jsonToPojo(jsonString, clazz);
	}

	private String serialize(Object target) {
		return JsonUtils.objectToJson(target);
	}

	// 包名+ 类名 + 方法名 + 参数(多个) 生成Key
	private String getCacheKey(ProceedingJoinPoint pjp) {
		StringBuffer key = new StringBuffer();
		// 包名+ 类名 cn.core.serice.product.ProductServiceImpl.productList
		String packageName = pjp.getTarget().getClass().getName();
		key.append(packageName);
		// 方法名
		String methodName = pjp.getSignature().getName();
		key.append(".").append(methodName);
		// 参数(多个)
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			// 参数
			key.append(".").append(arg.toString());
		}
		return key.toString();
	}
}
