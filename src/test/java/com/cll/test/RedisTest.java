package com.cll.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cll.dao.JedisClientSingle;
import com.cll.service.AddService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisTest {
	Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private AddService addService;

	@Resource
	private JedisClientSingle jedisClientSingle;
	
	@Test
	public void getTest() {
		//简单的Redis存入操作
		String setValueResult = addService.setName("RedisKey", "Hello Redis!");
		log.debug("setValueResult ==> " + setValueResult);
		Set<String> keys = addService.getAllKeys();
		for (String key : keys) {
			//log.debug(key + " ==> " + addService.getName(key));
			log.debug(key);
		}
	}

	// String类型
	@Test
	public void test2() {
		jedisClientSingle.set("name", "wangh");
		jedisClientSingle.set("age", "18");
		jedisClientSingle.set("sex", "man");
		jedisClientSingle.set("addr", "man");

		long size = jedisClientSingle.dbsize();
		log.debug("key数量:" + size);
		Iterator<String> its = jedisClientSingle.getAll().iterator();
		while (its.hasNext()) {
			log.debug("key:" + its.next());
		}
		long age = jedisClientSingle.decr("age");
		log.debug("age:" + age);
//		long d = jedisClientSingle.mdel("java.lang.String", "a", "v");
//		log.debug("mdel:" + d);
		long addr = jedisClientSingle.expire("addr", 100000);
		log.debug("addr:" + addr);
		long ttl = jedisClientSingle.ttl("addr");
		log.debug("ttl:" + ttl);
		// String nname = jedisClientSingle.keyrename("addr", "address");
		// log.debug(nname);
	}

	// List 类型
	@Test
	public void test3() {
		jedisClientSingle.lpush("list", "1");
		jedisClientSingle.lpush("list", "2");
		jedisClientSingle.lpush("list", "3");
		jedisClientSingle.lpush("list", "1");

		long l = jedisClientSingle.llen("list");
		log.debug("长度：" + l);

		List<String> lists = jedisClientSingle.lrange("list", 0, -1);
		for (String elem : lists) {
			log.debug("list中值：" + elem);
		}

		String four = jedisClientSingle.lset("list", 3, "4");
		log.debug("第四个值改为：" + four);

		jedisClientSingle.rpush("list1", "1");
		jedisClientSingle.rpush("list1", "2");
		jedisClientSingle.rpush("list1", "3");
		List<String> lists1 = jedisClientSingle.lrange("list1", 0, -1);
		for (String elem : lists1) {
			log.debug("list1中值：" + elem);
		}
		long rem = jedisClientSingle.lrem("list1", 2, "1");
		log.debug("rem:" + rem);
	}

	// SET
	@Test
	public void test4() {
		jedisClientSingle.sadd("set", "1", "2", "3", "1");
		long l = jedisClientSingle.scard("set");
		log.debug("基数：" + l);
		Iterator<String> its = jedisClientSingle.smembers("set").iterator();
		while (its.hasNext()) {
			log.debug("值为：" + its.next());
		}
		boolean b = jedisClientSingle.sismember("set", "1");
		long ll = jedisClientSingle.smove("set", "set1", "1");
		
		Iterator<String> its1 = jedisClientSingle.smembers("set1").iterator();
		while (its1.hasNext()) {
			log.debug("set1值为：" + its1.next());
		}
		Iterator<String> its2 = jedisClientSingle.smembers("set").iterator();
		while (its2.hasNext()) {
			log.debug("值为：" + its2.next());
		}
	}
}
