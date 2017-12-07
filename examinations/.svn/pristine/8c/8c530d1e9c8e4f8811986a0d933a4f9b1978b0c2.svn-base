package com.minlength.platform.core.function.vo;

import java.util.List;

import com.minlength.platform.core.utils.xml.ElementMapping;


/**
 * 系统单一功能描述类
 * @author shug
 *
 */
public class Function {
	
	/**
	 * 功能的ID，该ID应该全系统唯一
	 */
	private String id;
	
	/**
	 * 功能的名称
	 */
	private String name;
	
	/**
	 * 功能的描述
	 */
	private String desc;
		
	/**
	 * 功能的版本
	 */
	private String version;
	
	/**
	 * 功能的类型,取值和表示如下
	 * SIMPLECRUD:表示一个简单的增、删、改、查功能
	 * 
	 */
	private String type;
	
	/**
	 * 该表格是否可编辑
	 */
	private boolean grideditable = false;
	
	private String view;

	private String ref;
	
	/**
	 * 当编辑页面加载完毕后需要执行的js代码
	 */
	private String onedtpageready;
	
	/**
	 * 当列表页面加载完毕的时候，需要执行的js代码
	 */
	private String onlistpageready;
	
	//新增页面加载完毕需要执行的代码
	private String onnewpageready;
	
	public String getOnnewpageready() {
		return onnewpageready;
	}

	public void setOnnewpageready(String onnewpageready) {
		this.onnewpageready = onnewpageready;
	}

	public String getOnlistpageready() {
		return onlistpageready;
	}

	public void setOnlistpageready(String onlistpageready) {
		this.onlistpageready = onlistpageready;
	}

	public String getOnedtpageready() {
		return onedtpageready;
	}

	public void setOnedtpageready(String onedtpageready) {
		this.onedtpageready = onedtpageready;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	/**
	 * 定义页面的表单描述
	 */
	@ElementMapping(element="form",type=Property.class)
	private List<Property> form;
	
	@ElementMapping(element="operations",type=Operation.class)
	private List<Operation> operations;
	
	public Operation getOperationById(String id){
		if(id == null || this.operations == null){
			return null;
		}
		for(Operation _operation : this.operations){
			if(id.equalsIgnoreCase(_operation.getId())){
				return _operation;
			}else{//获取子操作
				if(_operation.getOperations() == null){continue;}
				for(Operation _operation1 : _operation.getOperations()){
					if(id.equals(_operation1.getId())){
						return _operation1;
					}
				}
			}
		}
		return null;
	}
	
	public Operation getOperationById(String id,String optfid){
		if(id == null || this.operations == null){
			return null;
		}
		if (optfid == null) {
			return getOperationById(id);
		}
		for(Operation _operation : this.operations){
			if(optfid.equalsIgnoreCase(_operation.getId())){
				for(Operation _operation1 : _operation.getOperations()){
					if(id.equalsIgnoreCase(_operation1.getId())){
						return _operation1;
					}
				}
			}
		}
		return null;
	}
	
	public Property getPropertyById(String id){
		if(id != null){id = id.trim();}
		if(form == null){
			return null;
		}
		for(Property _p : form){
			if(_p.getId().equals(id)){
				return _p;
			}
		}
		return null;
	}
	
	public Property getPropertyByName(String name){
		if(form == null){
			return null;
		}
		for(Property _p : form){
			if(_p.getName().equals(name)){
				return _p;
			}
		}
		return null;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public boolean isGrideditable() {
		return grideditable;
	}

	public void setGrideditable(boolean grideditable) {
		this.grideditable = grideditable;
	}

	public List<Property> getForm() {
		return form;
	}

	public void setForm(List<Property> form) {
		this.form = form;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
}
