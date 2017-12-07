package com.minlength.platform.core.function.vo;

/**
 * 用于描述页面输入字段的数据来源
 * @author shug
 *
 */
public class DataSource {
	
	/**
	 * 数据的来源类型
	 * input:默认
	 * predefined:预定义，使用json数据封装表示
	 * ajax:异步请求后天数据，使用json数据格式传递
	 */
	private String type="input";
	
	/**
	 * 当type不为input的时候该参数生效，表示数据来源的源，可以url或者数据
	 */
	private String value;
	
	/**
	 * 数据的默认值
	 */
	private String defaultvalue;

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

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
}
