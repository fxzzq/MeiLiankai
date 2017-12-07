package com.minlength.platform.core.function.vo;

public class Sql {
	private String name;
	private String value;
	private String params;
	private String type;
	
	//获取查询全部数据条数的语句
	private String totalcmd;
	
	//表名,当type为MONGO_FIND的时候才生效
	private String tablename;
	//要显示的字段，当type为MONGO_FIND的时候才生效
	private String projection;
	
	public String getTotalcmd() {
		return totalcmd;
	}
	public void setTotalcmd(String totalcmd) {
		this.totalcmd = totalcmd;
	}
	public String getProjection() {
		return projection;
	}
	public void setProjection(String projection) {
		this.projection = projection;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
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
}
