package com.minlength.platform.core.function.vo;

/**
 * 用于描述页面输入单元的验证,目前的所有验证都为后台验证
 * 验证为true则表示验证通过，false表示验证失败
 * @author shug
 *
 */
public class Validate {
	
	/**
	 * 验证的类型
	 * reg:正则表达是验证
	 * script:表达式验证
	 */
	private String type;
	
	/**
	 * 验证的表达式，可以是正则或者是beanshell可以识别的java代码
	 */
	private String exp;
	
	/**
	 * 如果验证为false的情况下，需要给出的返回提示
	 */
	private String emsg;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getEmsg() {
		return emsg;
	}

	public void setEmsg(String emsg) {
		this.emsg = emsg;
	}

}
