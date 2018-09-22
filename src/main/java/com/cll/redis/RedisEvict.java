package com.cll.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义标注在Service实现方法上的注解，用于传递类型参数
 * 
 * @ClassName: RedisEvict
 * @author Wh
 * @date 2017年6月5日 下午4:25:49
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisEvict {
	@SuppressWarnings("rawtypes")
	Class type();
}
