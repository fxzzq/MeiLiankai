package com.minlength.platform.core.processor.sys;

import com.minlength.platform.core.processor.ResultProcessor;

/**
 * 
 * <p>操作redis缓存数据库</p>
 * <br/>
 * @Title: RedisProcessor.java 
 * @Package com.meloclan.platform.core.processor.sys 
 * @author shugang
 * @date 2017 2017-5-14 下午10:34:28 
 * @version v1.0
 */
public interface RedisProcessor extends ResultProcessor {
		
	/**
	 * 执行redis缓存数据操作
	 * @param database 需要操作的数据库
	 * @param handler 具体的操作
	 * @return
	 */
	public <T extends Object> RedisProcessor redis(String name, int database, RedisHandler<T> handler);

	/**
	 * 执行redis缓存数据操作
	 * @param database	操作的数据库
	 * @param handler	实际操作
	 * @return
	 */
	public <T extends Object> RedisProcessor redis(int database, RedisHandler<T> handler);
}
