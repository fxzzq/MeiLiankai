package com.minlength.platform.core.function.vo;

import java.util.List;

import com.minlength.platform.core.utils.xml.ElementMapping;



/**
 * 用于描述页面表单的每个输入项的类
 * @author shug
 *
 */
public class Property {
	
	/**
	 * 输入项的ID
	 */
	private String id;
	
	/**
	 * 输入项的名称,在页面表现为字段的Label标签
	 */
	private String name;
	
	/**
	 * 输入项的类型，该类型跟html的类型一同通用，如果不填写默认为input
	 */
	private String type="input";
	
	/**
	 * 该输入项是否为必填项
	 */
	private boolean required = false;
	
	/**
	 * 添加的时候是否显示
	 */
	private boolean show4create = true;
	
	/**
	 * 添加的时候触发加载数据
	 */
	private boolean loadtrigger = false;
	
	/**
	 * 该输入项是否可以修改
	 */
	private boolean modifiable = true;
	
	/**
	 * 修改的时候是否显示
	 */
	private boolean show4modify = true;
	
	/**
	 * 该输入项是否提供查询功能
	 */
	private boolean queryable = true;
	
	/**
	 * 表格中是否显示，默认为true
	 */
	private boolean show4table = true;
	
	/**
	 * 表格中列是否可编辑（Function的grideditable为true时，此配置生效），默认为false
	 */
	private boolean coleditable = false;
	
	/**
	 * 当前列是否允许排序
	 */
	private boolean sortable=false;
	
	/**
	 * 最大长度
	 */
	private String maxlength = "255";
	
	/**
	 * 列宽
	 */
	private String columnwidth;	
	
	/**
	 * 输入数据的来源
	 */	
	private DataSource datasource;	
	
	/**
	 * 是否可以拖动
	 */
	private boolean flex = false;
	
	/**
	 * 内容是否暂时tooltip，默认为false
	 */
	private boolean tooltip = false;
	
	/**
	 * 在添加或者修改的时候，该输入项需要通过的验证队列，必须全部满足才能通过验证
	 */
	@ElementMapping(element="validates",type=Validate.class)
	private List<Validate> validates;
	
	public String getColumnwidth() {
		return columnwidth;
	}
	public void setColumnwidth(String columnwidth) {
		this.columnwidth = columnwidth;
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
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isModifiable() {
		return modifiable;
	}
	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}
	public boolean isQueryable() {
		return queryable;
	}
	public void setQueryable(boolean queryable) {
		this.queryable = queryable;
	}
	public DataSource getDatasource() {
		if(this.datasource == null){this.datasource = new DataSource();}
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	public List<Validate> getValidates() {
		return validates;
	}
	public void setValidates(List<Validate> validates) {
		this.validates = validates;
	}
	public boolean isShow4create() {
		return show4create;
	}
	public void setShow4create(boolean show4create) {
		this.show4create = show4create;
	}
	public boolean isShow4modify() {
		return show4modify;
	}
	public void setShow4modify(boolean show4modify) {
		this.show4modify = show4modify;
	}
	public boolean isShow4table() {
		return show4table;
	}
	public void setShow4table(boolean show4table) {
		this.show4table = show4table;
	}
	public boolean isColeditable() {
		return coleditable;
	}
	public void setColeditable(boolean coleditable) {
		this.coleditable = coleditable;
	}
	public boolean isLoadtrigger() {
		return loadtrigger;
	}
	public void setLoadtrigger(boolean loadtrigger) {
		this.loadtrigger = loadtrigger;
	}
	public String getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public boolean isFlex() {
		return flex;
	}
	public void setFlex(boolean flex) {
		this.flex = flex;
	}
	public boolean isTooltip() {
		return tooltip;
	}
	public void setTooltip(boolean tooltip) {
		this.tooltip = tooltip;
	}	
	
}
