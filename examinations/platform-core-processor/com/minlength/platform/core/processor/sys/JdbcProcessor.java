package com.minlength.platform.core.processor.sys;

import java.sql.Connection;
import java.util.List;

import com.minlength.platform.core.processor.ResultProcessor;

/**
 * 
 * <p>通过JDBC操作数据库</p>
 * <br/>
 * @Title: JdbcProcessor.java 
 * @Package com.meloclan.platform.core.processor.sys 
 * @author shugang
 * @date 2017 2017-5-14 下午10:34:13 
 * @version v1.0
 */
public interface JdbcProcessor extends ResultProcessor {
	
	/**
	 * 当前线程使用的数据库连接
	 */
	public static final ThreadLocal<Connection> JDBC_CONNECTION = new ThreadLocal<Connection>();
	
	/**
	 * 开始一个JDBC事务
	 * @return
	 */
	public JdbcProcessor beginJdbcTransaction();
	
	/**
	 * 结束一个JDBC事务
	 * @return
	 */
	public JdbcProcessor commitJdbcTransaction();
	
	/**
	 * 通过jdbc更新一批数据到数据库
	 * @param name		当前操作单元名称
	 * @param sql		需要执行的更新sql
	 * @param datas		更新的数据
	 * @return
	 */
	public JdbcProcessor jdbcUpdate(String name, String sql, List<Object[]> datas);
	public JdbcProcessor jdbcUpdate(String name, String sql, Object[] datas);

	/**
	 * 通过jdbc更新一批数据到数据库
	 * @param sql		需要执行的更新sql
	 * @param datas		更新的数据
	 * @return
	 */
	public JdbcProcessor jdbcUpdate(String sql, List<Object[]> datas);
	public JdbcProcessor jdbcUpdate(String sql, Object[] datas);
	
	/**
	 * 通过jdbc查询数据
	 * @param name		当前操作单元的自定义名称
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句参数
	 * @return
	 */
	public JdbcProcessor jdbcQuery(String name, String sql, Object[] params);
	
	/**
	 * 通过jdbc查询数据
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句的参数
	 * @return
	 */
	public JdbcProcessor jdbcQuery(String sql, Object[] params);
	
	/**
	 * 通过jdbc查询数据
	 * @param name		当前操作单元的自定义名称
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句参数
	 * @param withtotal 是否返回满足条件的所有数据条数
	 * @return
	 */
	public JdbcProcessor jdbcQuery(String name, String sql, Object[] params, boolean withtotal);
	
	/**
	 * 通过jdbc查询数据
	 * @param sql		需要执行的查询语句
	 * @param params	查询语句的参数
	 * @param withtotal 是否返回满足条件的所有数据条数
	 * @return
	 */
	public JdbcProcessor jdbcQuery(String sql, Object[] params, boolean withtotal);
	
}
