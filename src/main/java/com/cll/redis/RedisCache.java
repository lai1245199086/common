package com.cll.redis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义标注在Service实现方法上的注解，用于传递类型参数
 * 
 * @ClassName: RedisCache
 * @author Wh
 * @date 2017年6月5日 下午4:23:18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
	@SuppressWarnings("rawtypes")
	Class type();

	public int expire() default 0; // 缓存多少秒,默认无限期
}
