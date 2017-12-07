package com.minlength.platform.core.processor.sys;

import java.util.List;

import com.minlength.platform.core.processor.CustomProcessorHandler;
import com.minlength.platform.core.processor.RunnableProcessor;

/**
 * 
 * <p>集成了系统所有提供的</p>
 * <br/>
 * @Title: SystemProcessor.java 
 * @Package com.meloclan.platform.core.processor.sys 
 * @author shugang
 * @date 2017 2017-5-22 下午4:56:45 
 * @version v1.0
 */
public interface SystemProcessor extends RunnableProcessor {	
	
	/*=====================JDBCProcessor=========================*/
	
	/**
	 * 开始一个JDBC事务
	 * @return
	 */
	public SystemProcessor beginJdbcTransaction();
	
	/**
	 * 结束一个JDBC事务
	 * @return
	 */
	public SystemProcessor commitJdbcTransaction();
	
	/**
	 * 通过jdbc更新一批数据到数据库
	 * @param name		当前操作单元名称
	 * @param sql		需要执行的更新sql
	 * @param datas		更新的数据
	 * @return
	 */
	public SystemProcessor jdbcUpdate(String name, String sql, List<Object[]> datas);

	/**
	 * 通过jdbc更新一批数据到数据库
	 * @param sql		需要执行的更新sql
	 * @param datas		更新的数据
	 * @return
	 */
	public SystemProcessor jdbcUpdate(String sql, List<Object[]> datas);
	
	/**
	 * 通过jdbc查询数据
	 * @param name		当前操作单元的自定义名称
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句参数
	 * @return
	 */
	public SystemProcessor jdbcQuery(String name, String sql, Object[] params);
	
	/**
	 * 通过jdbc查询数据
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句的参数
	 * @return
	 */
	public SystemProcessor jdbcQuery(String sql, Object[] params);
	
	/**
	 * 通过jdbc查询数据
	 * @param name		当前操作单元的自定义名称
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句参数
	 * @param withtotal 是否返回满足条件的所有数据条数
	 * @return
	 */
	public SystemProcessor jdbcQuery(String name, String sql, Object[] params, boolean withtotal);
	
	/**
	 * 通过jdbc查询数据
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句的参数
	 * @param withtotal 是否返回满足条件的所有数据条数
	 * @return
	 */
	public SystemProcessor jdbcQuery(String sql, Object[] params, boolean withtotal);
	
	/*================ReidsProcessor===================*/
	
	/**
	 * 执行redis缓存数据操作
	 * @param database 需要操作的数据库
	 * @param handler 具体的操作
	 * @return
	 */
	public <T extends Object> SystemProcessor redis(String name, int database, RedisHandler<T> handler);

	/**
	 * 执行redis缓存数据操作
	 * @param database	操作的数据库
	 * @param handler	实际操作
	 * @return
	 */
	public <T extends Object> SystemProcessor redis(int database, RedisHandler<T> handler);
	
	
	/*=====================CustomProcessor=====================*/
	/**
	 * 执行自定义的处理单元
	 * @param handler	真实的处理方法
	 * @param objects	传入的完毕参数，这些参数将会传入到CustomProcessorHandler中
	 * @return
	 */
	public SystemProcessor execute(CustomProcessorHandler handler, Object...objects);
	
	/**
	 * 执行自定义的处理单元
	 * @param name		执行后参数的名称
	 * @param handler	真实的处理方法
	 * @param objects	传入的完毕参数，这些参数将会传入到CustomProcessorHandler中
	 * @return
	 */
	public SystemProcessor execute(String name, CustomProcessorHandler handler, Object...objects);
	
	/*====================mongodb==========================*/	
	/**
	 * 执行操作
	 * @param handler
	 * @return
	 */
	public SystemProcessor execute(String name, MongoHandler handler);
	
	public SystemProcessor execute(MongoHandler handler);
	
}
