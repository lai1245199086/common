package com.cll.dao;

import java.util.List;
import java.util.Set;

public interface JedisClient {
	/**
	 * 获取key的值
	 * 
	 * @Title: get
	 * @param @param
	 *            key
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String get(String key);

	/**
	 * 获取所有key
	 * 
	 * @Title: getAll
	 * @param @return
	 * @return Set<String>
	 * @throws @author
	 *             Wanghao
	 */
	Set<String> getAll();

	byte[] get(byte[] key);

	/**
	 * 新增key值
	 * 
	 * @Title: set
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String set(String key, String value);

	String set(byte[] key, byte[] value);

	/**
	 * 返回名称为hkey的hash中key对应的value
	 * 
	 * @Title: hget
	 * @param @param
	 *            hkey
	 * @param @param
	 *            key
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String hget(String hkey, String key);

	/**
	 * 向名称为hkey的hash中添加元素key
	 * 
	 * @Title: hset
	 * @param @param
	 *            hkey
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long hset(String hkey, String key, String value);

	/**
	 * 名称为key的string增1操作
	 * 
	 * @Title: incr
	 * @param @param
	 *            key
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long incr(String key);

	/**
	 * 设置key值过期时间
	 * 
	 * @Title: expire
	 * @param @param
	 *            key
	 * @param @param
	 *            second
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long expire(String key, int second);

	/**
	 * 查看key值剩余生存时间
	 * 
	 * @Title: ttl
	 * @param @param
	 *            key
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long ttl(String key);

	/**
	 * 删除一个key
	 * 
	 * @Title: del
	 * @param @param
	 *            key
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long del(String key);

	/**
	 * 删除名称为hkey的hash中键为key的域
	 * 
	 * @Title: hdel
	 * @param @param
	 *            hkey
	 * @param @param
	 *            key
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long hdel(String hkey, String key);

	/**
	 * 判断key是否存在
	 * 
	 * @Title: isExists
	 * @param @param
	 *            key
	 * @param @return
	 * @return boolean
	 * @throws @author
	 *             Wanghao
	 */
	boolean isExists(String key);

	/**
	 * 返回值的类型
	 * 
	 * @Title: type
	 * @param @param
	 *            key
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String type(String key);

	/**
	 * 重命名key
	 * 
	 * @Title: keyrename
	 * @param @param
	 *            oldname
	 * @param @param
	 *            newname
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String keyrename(String oldname, String newname);

	/**
	 * 返回当前数据库中key的数目
	 * 
	 * @Title: dbsize
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long dbsize();

	/**
	 * 按索引查询.切换到指定的数据库，数据库索引号 index 用数字值指定，以 0 作为起始索引值。
	 * 
	 * @Title: select
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String select(int index);

	/**
	 * 删除当前选择数据库中的所有key
	 * 
	 * @Title: flushdb
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String flushdb();

	/**
	 * 删除所有数据库中的所有key
	 * 
	 * @Title: flushall
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String flushall();

	/**
	 * 返回库中多个string的value
	 * 
	 * @Title: mget
	 * @param @param
	 *            keys
	 * @param @return
	 * @return List<String>
	 * @throws @author
	 *             Wanghao
	 */
	List<String> mget(String... keys);

	/**
	 * 批量设置多个string的值
	 * 
	 * @Title: mset
	 * @param @param
	 *            keys
	 * @param @return
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String mset(String... keys);

	/**
	 * 添加string，名称为key，值为value
	 * 
	 * @Title: setnx
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @return
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long setnx(String key, String value);

	/**
	 * 名称为key的string增加integer
	 * 
	 * @Title: incrby
	 * @param key
	 * @param integer
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long incrby(String key, int integer);

	/**
	 * 名称为key的string减1操作
	 * 
	 * @Title: decr
	 * @param key
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long decr(String key);

	/**
	 * 名称为key的string减少integer
	 * 
	 * @Title: decrby
	 * @param key
	 * @param integer
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	long decrby(String key, int integer);

	/**
	 * 名称为key的string的值附加value
	 * 
	 * @Title: append
	 * @param key
	 * @param value
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long append(String key, String value);

	/**
	 * 返回名称为key的string的value的子串
	 * 
	 * @Title: substr
	 * @param key
	 * @param start
	 * @param end
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String substr(String key, int start, int end);

	/**
	 * 删除多个key
	 * 
	 * @Title: mdel
	 * @param key
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long mdel(String... key);

	// #################### List ################################
	/**
	 * 在名称为key的list头添加一个值为value的 元素
	 * 
	 * @Title: lpush
	 * @param key
	 * @param value
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	long lpush(String key, String value);

	/**
	 * 在名称为key的list尾添加一个值为value的元素
	 * 
	 * @Title: lpush
	 * @param key
	 * @param value
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	long rpush(String key, String value);

	/**
	 * 返回名称为key的list的长度
	 * 
	 * @Title: llen
	 * @param key
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long llen(String key);

	/**
	 * 返回名称为key的list中start至end之间的元素
	 * 
	 * @Title: lrange
	 * @param key
	 * @param start
	 * @param end
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	List<String> lrange(String key, int start, int end);

	/**
	 * 截取名称为key的list
	 * 
	 * @Title: ltrim
	 * @param key
	 * @param start
	 * @param end
	 * @return List<String>
	 * @throws @author
	 *             Wanghao
	 */
	String ltrim(String key, int start, int end);

	/**
	 * 返回名称为key的list中index位置的元素
	 * 
	 * @Title: lindex
	 * @param key
	 * @param index
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String lindex(String key, long index);

	/**
	 * 给名称为key的list中index位置的元素赋值
	 * 
	 * @Title: lset
	 * @param key
	 * @param index
	 * @param value
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String lset(String key, int index, String value);

	/**
	 * 删除count个key的list中值为value的元素
	 * 
	 * @Title: lrem
	 * @param key
	 * @param count
	 * @param value
	 * @return long
	 * @throws @author
	 *             Wanghao
	 */
	long lrem(String key, int count, String value);

	/**
	 * 返回并删除名称为key的list中的首元素
	 * 
	 * @Title: lpop
	 * @param key
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String lpop(String key);

	/**
	 * 返回并删除名称为key的list中的尾元素
	 * 
	 * @Title: lpop
	 * @param key
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String rpop(String key);

	/**
	 * 返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部
	 * 
	 * @Title: rpoplpush
	 * @param srckey
	 * @param dstkey
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String rpoplpush(String srckey, String dstkey);

	// ########################### SET
	// ############################################
	/**
	 * 向名称为key的set中添加元素member
	 * 
	 * @Title: sadd
	 * @param key
	 * @param member
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	Long sadd(String key, String... members);

	/**
	 * 删除名称为key的set中的元素member
	 * 
	 * @Title: srem
	 * @param key
	 * @param member
	 * @return Long
	 * @throws @author
	 *             Wanghao
	 */
	Long srem(String key, String... members);

	/**
	 * 随机返回并删除名称为key的set中一个元素
	 * 
	 * @Title: spop
	 * @param key
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String spop(String key);

	/**
	 * member元素从srckey集合移到dstkey集合 。member 元素从 source 集合中被移除，并添加到 destination
	 * 集合中去
	 * 
	 * @Title: smove
	 * @param srckey
	 * @param dstkey
	 * @param member
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	Long smove(String srckey, String dstkey, String member);

	/**
	 * 返回名称为key的set的基数
	 * 
	 * @Title: scard
	 * @param key
	 * @return Long
	 * @throws @author
	 *             Wanghao
	 */
	Long scard(String key);

	/**
	 * member是否是名称为key的set的元素
	 * 
	 * @Title: sismember
	 * @param key
	 * @param member
	 * @return Boolean
	 * @throws @author
	 *             Wanghao
	 */
	Boolean sismember(String key, String member);

	/**
	 * 求交集
	 * 
	 * @Title: sinter
	 * @param keys
	 * @return List<String>
	 * @throws @author
	 *             Wanghao
	 */
	Set<String> sinter(String... keys);

	/**
	 * 求交集并将交集保存到dstkey的集合
	 * 
	 * @Title: sinterstore
	 * @param dstkey
	 * @param keys
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	Long sinterstore(String dstkey, String... keys);

	/**
	 * 求并集
	 * 
	 * @Title: sunion
	 * @param keys
	 * @return List<String>
	 * @throws @author
	 *             Wanghao
	 */
	Set<String> sunion(String... keys);

	/**
	 * 求并集并将并集保存到dstkey的集合
	 * 
	 * @Title: sunionstore
	 * @param dstkey
	 * @param keys
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	Long sunionstore(String dstkey, String... keys);

	/**
	 * 求差集
	 * 
	 * @Title: sdiff
	 * @param keys
	 * @return Set<String>
	 * @throws @author
	 *             Wanghao
	 */
	Set<String> sdiff(String... keys);

	/**
	 * 求差集并将差集保存到dstkey的集合
	 * 
	 * @Title: sdiffstore
	 * @param dstkey
	 * @param keys
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	Long sdiffstore(String dstkey, String... keys);

	/**
	 * 返回名称为key的set的所有元素
	 * 
	 * @Title: smembers
	 * @param key
	 * @return Set<String>
	 * @throws @author
	 *             Wanghao
	 */
	Set<String> smembers(String key);

	/**
	 * 随机返回名称为key的set的一个元素
	 * 
	 * @Title: srandmember
	 * @param key
	 * @return String
	 * @throws @author
	 *             Wanghao
	 */
	String srandmember(String key);
	// ########################### Hash ####################################
}
