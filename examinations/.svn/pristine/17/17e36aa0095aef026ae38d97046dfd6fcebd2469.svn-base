package com.minlength.platform.core.processor.sys;

import com.minlength.platform.core.processor.ResultProcessor;

/**
 * 对mongodb的操作
 * @author shugang
 *
 */
public interface MongoProcessor extends ResultProcessor {
	
	/**
	 * 执行mongo操作
	 * @param name		结果的变量名
	 * @param handler	执行操作
	 * @return
	 */
	public MongoProcessor execute(String name, MongoHandler handler, Object...objects);
	
	/**
	 * 执行mongo操作
	 * @param handler
	 * @return
	 */
	public MongoProcessor execute(MongoHandler handler, Object...objects);
	
	/**
	 * 执行查询操作
	 * @param name			查询结果存放变量
	 * @param tablename		需要查询的数据表
	 * @param filter		需要查询的过滤bson
	 * @param projection	查询需要显示的字段
	 * @param start			分页查询开始数据索引(如果为-1表示不分页)
	 * @param limit			每页数据条数（如果为-1表示不分页）
	 * @return
	 */
	public MongoProcessor find(String name, String tablename, String filter, String projection, int start, int limit, Object...objects);
	
	/**
	 * 执行查询操作
	 * @param tablename		需要查询的数据表
	 * @param filter			需要查询的过滤bson
	 * @param projection	查询需要显示的字段
	 * @param start			分页查询开始数据索引(如果为-1表示不分页)
	 * @param limit			每页数据条数（如果为-1表示不分页）
	 * @return
	 */
	public MongoProcessor find(String tablename, String filter, String projection, int start, int limit, Object...objects);
	
	/**
	 * 根据查询条件统计数据总条数
	 * @param name
	 * @param tablename
	 * @param bson
	 * @return
	 */
	public MongoProcessor count(String name, String tablename, String bson, Object...objects);
	
	/**
	 * 根据查询条件统计数据总条数
	 * @param tablename
	 * @param bson
	 * @return
	 */
	public MongoProcessor count(String tablename, String bson, Object...objects);
	
	/**
	 * 执行aggregate
	 * @param name		执行结果的保存变量
	 * @param tablename	需要查找的表名
	 * @param bson		查询语句
	 * @return
	 */
	public MongoProcessor aggregate(String name, String tablename, String bson, Object...objects);
	
	/**
	 * 执行aggregate
	 * @param tablename
	 * @param bson
	 * @return
	 */
	public MongoProcessor aggregate(String tablename, String bson, Object...objects);
	
}
