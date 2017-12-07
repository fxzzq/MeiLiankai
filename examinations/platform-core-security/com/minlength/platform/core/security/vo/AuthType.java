package com.minlength.platform.core.security.vo;

/**
 * 认证方式
 * @author shugang
 *
 */
public enum AuthType {
	
	//表单验证
	FORM_AUTH("FORM_AUTH"),
	
	//企业微信应用认证
	ENTERPRISE_WEIXIN_AUTH("ENTERPRISE_WEIXIN_AUTH")
	;
	//认证方式的字符串表示
	private String value;
	AuthType(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
