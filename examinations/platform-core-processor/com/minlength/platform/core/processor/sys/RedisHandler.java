package com.minlength.platform.core.processor.sys;

import redis.clients.jedis.Jedis;

/**
 * redis的操作接口
 * @author shugang
 *
 */
public interface RedisHandler<T> {

	/**
	 * jedis的具体操作
	 * @param jedis
	 * @return
	 */
	public T execute(Jedis jedis);
	
}
