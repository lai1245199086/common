package com.cll.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json字符串互转类
 * 
 * @ClassName: JsonUtils
 * @author Wh
 * @date 2017年6月5日 下午6:54:34
 */
public class JsonUtils {
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (Exception e) {
			logger.error("异常",e);
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			logger.error("异常",e);
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * 
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			logger.error("异常",e);
		}
		return null;
	}

	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String, T> jsonToMap(String jsonStr, Class<T> clazz) throws Exception {
		Map<String, Map<String, Object>> map = MAPPER.readValue(jsonStr, new TypeReference<Map<String, T>>() {
		});
		Map<String, T> result = new HashMap<String, T>();
		for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), mapTopojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json string convert to map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonTomap(String jsonStr) {
		try {
			return MAPPER.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			logger.error("异常",e);
		}
		return null;
	}

	/**
	 * map convert to javaBean
	 */
	public static <T> T mapTopojo(Map<?, ?> map, Class<T> clazz) {
		try {
			return MAPPER.convertValue(map, clazz);
		} catch (Exception e) {
			logger.error("异常",e);
		}
		return null;
	}
}
