package com.minlength.platform.core.function.vo;

import java.util.List;

import com.minlength.platform.core.utils.xml.ElementMapping;


/**
 * 功能的某单个操作功能
 * @author shug
 *
 */
public class Operation {
	
	/**
	 * 操作的ID，该ID应该页面唯一，因为做权限控制的时候需要用到
	 */
	private String id;
	
	/**
	 * 操作的名称
	 */
	private String name;
	
	/**
	 * 操作的类型，其中
	 * BATCH:表示批量操作，放在表格的顶部，
	 * TAB_ON_CREATE:表示页面建立的时候就添加
	 * SINGLE:单个操作，放在每行数据中
	 * INTERFACE:表示接口，不会在页面显示
	 */
	private String location;
	
	/**
	 * 操作类型
	 * 执行的表达式，如果type
	 * 如果为HQL_QUERY，则为HQL查询语句；
	 * 如果为HQL_UPDATE,则为HQL更新语句；
	 * 如果为SQL，则为SQL语句；
	 * 如果为SQL_UPDATE,则为SQL语句更新；
	 * 如果为SQL_BATCH_UPDATE,则为批量SQL语句更新；
	 * 如果为FORWARD是一个面板类
	 * 如果为HTTP则是一个url地址
	 * 如果为PROCESS则表示执行一个流程
	 */
	private String type;
	
	/**
	 * 执行的表达式，如果type
	 * 如果为HQL_QUERY，则为HQL查询语句；
	 * 如果为HQL_UPDATE,则为HQL更新语句；
	 * 如果为SQL，则为SQL语句；
	 * 如果为SQL_UPDATE,则为SQL语句更新；
	 * 如果为FORWARD是一个面板类
	 * 如果为HTTP则是一个url地址
	 * 如果为PROCESS则表示执行一个流程,该值为流程的定义key
	 */
	private String value;
	
	/**
	 * 操作过程中需要使用到的参数，使用"$字段名称"样式表示
	 */
	private String params;
	
	/**
	 * type为FORWARD，向页面传递参数
	 */
	private String forwardParams;
	
	/**
	 * 操作的描述
	 */
	private String desc;
	
	/**
	 * 执行操作的时候需要验证的字段
	 */
	private String validate;
	
	/**
	 * 操作的图标显示
	 */
	private String iconCls;
	
	/**
	 * 当为一对多的时候，many端的数据接口
	 */
	private String many;
	
	/**
	 * location:BATCH批量操作按钮提示
	 */
	private String optTip;
	
	/**
	 * 是否在流程申请中显示
	 */
	private boolean bpmview;
	
	@ElementMapping(element="batch",type=Sql.class)
	private List<Sql> batch;
	
	/**
	 * 操作可用条件
	 */
	private EnableIf enableif;
	
	/**
	 * 子级操作，例如一对多配置
	 */
	@ElementMapping(element="operations",type=Operation.class)
	private List<Operation> operations;
	
	public EnableIf getEnableif() {
		return enableif;
	}

	public void setEnableif(EnableIf enableif) {
		this.enableif = enableif;
	}

	public List<Sql> getBatch() {
		return batch;
	}

	public void setBatch(List<Sql> batch) {
		this.batch = batch;
	}

	public String getMany() {
		return many;
	}

	public void setMany(String many) {
		this.many = many;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public String getForwardParams() {
		return forwardParams;
	}

	public void setForwardParams(String forwardParams) {
		this.forwardParams = forwardParams;
	}

	public boolean isBpmview() {
		return bpmview;
	}

	public void setBpmview(boolean bpmview) {
		this.bpmview = bpmview;
	}

	public String getOptTip() {
		return optTip;
	}

	public void setOptTip(String optTip) {
		this.optTip = optTip;
	}

}
