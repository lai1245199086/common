package com.cll.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient {
	@Autowired
	private JedisPool jedisPool;

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public byte[] get(byte[] key) {
		Jedis jedis = jedisPool.getResource();
		byte[] result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String set(byte[] key, byte[] value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long mdel(String... key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

	@Override
	public Set<String> getAll() {
		Jedis jedis = jedisPool.getResource();
		Set<String> keys = jedis.keys("*");
		jedis.close();
		return keys;
	}

	@Override
	public boolean isExists(String key) {
		Jedis jedis = jedisPool.getResource();
		boolean b = jedis.exists(key);
		jedis.close();
		return b;
	}

	@Override
	public String type(String key) {
		Jedis jedis = jedisPool.getResource();
		String type = jedis.type(key);
		jedis.close();
		return type;
	}

	@Override
	public String keyrename(String oldname, String newname) {
		Jedis jedis = jedisPool.getResource();
		String name = jedis.rename(oldname, newname);
		jedis.close();
		return name;
	}

	@Override
	public long dbsize() {
		Jedis jedis = jedisPool.getResource();
		long size = jedis.dbSize();
		jedis.close();
		return size;
	}

	@Override
	public String select(int index) {
		Jedis jedis = jedisPool.getResource();
		String key = jedis.select(index);
		jedis.close();
		return key;
	}

	@Override
	public String flushdb() {
		Jedis jedis = jedisPool.getResource();
		String flush = jedis.flushDB();
		jedis.close();
		return flush;
	}

	@Override
	public String flushall() {
		Jedis jedis = jedisPool.getResource();
		String flush = jedis.flushAll();
		jedis.close();
		return flush;
	}

	@Override
	public List<String> mget(String... keys) {
		Jedis jedis = jedisPool.getResource();
		List<String> values = jedis.mget(keys);
		jedis.close();
		return values;
	}

	@Override
	public String mset(String... keys) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.mset(keys);
		jedis.close();
		return s;
	}

	@Override
	public long setnx(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long l = jedis.setnx(key, value);
		jedis.close();
		return l;
	}

	@Override
	public long incrby(String key, int integer) {
		Jedis jedis = jedisPool.getResource();
		long l = jedis.incrBy(key, integer);
		jedis.close();
		return l;
	}

	@Override
	public long decr(String key) {
		Jedis jedis = jedisPool.getResource();
		long l = jedis.decr(key);
		jedis.close();
		return l;
	}

	@Override
	public long decrby(String key, int integer) {
		Jedis jedis = jedisPool.getResource();
		long l = jedis.decrBy(key, integer);
		jedis.close();
		return l;
	}

	@Override
	public long append(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long l = jedis.append(key, value);
		jedis.close();
		return l;
	}

	@Override
	public String substr(String key, int start, int end) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.substr(key, start, end);
		jedis.close();
		return s;
	}

	@Override
	public long lpush(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long l = jedis.lpush(key, value);
		jedis.close();
		return l;
	}

	@Override
	public long rpush(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long l = jedis.rpush(key, value);
		jedis.close();
		return l;
	}

	@Override
	public long llen(String key) {
		Jedis jedis = jedisPool.getResource();
		long len = jedis.llen(key);
		jedis.close();
		return len;
	}

	@Override
	public List<String> lrange(String key, int start, int end) {
		Jedis jedis = jedisPool.getResource();
		List<String> list = jedis.lrange(key, start, end);
		jedis.close();
		return list;
	}

	@Override
	public String ltrim(String key, int start, int end) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.ltrim(key, start, end);
		jedis.close();
		return s;
	}

	@Override
	public String lindex(String key, long index) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.lindex(key, index);
		jedis.close();
		return s;
	}

	@Override
	public String lset(String key, int index, String value) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.lset(key, index, value);
		jedis.close();
		return s;
	}

	@Override
	public long lrem(String key, int count, String value) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.lrem(key, count, value);
		jedis.close();
		return l;
	}

	@Override
	public String lpop(String key) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.lpop(key);
		jedis.close();
		return s;
	}

	@Override
	public String rpop(String key) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.rpop(key);
		jedis.close();
		return s;
	}

	@Override
	public String rpoplpush(String srckey, String dstkey) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.rpoplpush(srckey, dstkey);
		jedis.close();
		return s;
	}

	@Override
	public Long sadd(String key, String... member) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.sadd(key, member);
		jedis.close();
		return l;
	}

	@Override
	public Long srem(String key, String... members) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.srem(key, members);
		jedis.close();
		return l;
	}

	@Override
	public String spop(String key) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.spop(key);
		jedis.close();
		return s;
	}

	@Override
	public Long smove(String srckey, String dstkey, String member) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.smove(srckey, dstkey, member);
		jedis.close();
		return l;
	}

	@Override
	public Long scard(String key) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.scard(key);
		jedis.close();
		return l;
	}

	@Override
	public Boolean sismember(String key, String member) {
		Jedis jedis = jedisPool.getResource();
		Boolean b = jedis.sismember(key, member);
		jedis.close();
		return b;
	}

	@Override
	public Set<String> sinter(String... keys) {
		Jedis jedis = jedisPool.getResource();
		Set<String> sets = jedis.sinter(keys);
		jedis.close();
		return sets;
	}

	@Override
	public Long sinterstore(String dstkey, String... keys) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.sinterstore(dstkey, keys);
		jedis.close();
		return l;
	}

	@Override
	public Set<String> sunion(String... keys) {
		Jedis jedis = jedisPool.getResource();
		Set<String> sets = jedis.sunion(keys);
		jedis.close();
		return sets;
	}

	@Override
	public Long sunionstore(String dstkey, String... keys) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.sunionstore(dstkey, keys);
		jedis.close();
		return l;
	}

	@Override
	public Set<String> sdiff(String... keys) {
		Jedis jedis = jedisPool.getResource();
		Set<String> sets = jedis.sdiff(keys);
		jedis.close();
		return sets;
	}

	@Override
	public Long sdiffstore(String dstkey, String... keys) {
		Jedis jedis = jedisPool.getResource();
		Long l = jedis.sdiffstore(dstkey, keys);
		jedis.close();
		return l;
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = jedisPool.getResource();
		Set<String> sets = jedis.smembers(key);
		jedis.close();
		return sets;
	}

	@Override
	public String srandmember(String key) {
		Jedis jedis = jedisPool.getResource();
		String s = jedis.srandmember(key);
		jedis.close();
		return s;
	}

}