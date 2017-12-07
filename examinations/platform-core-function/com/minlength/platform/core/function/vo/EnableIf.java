package com.minlength.platform.core.function.vo;

import java.util.List;

import com.minlength.platform.core.utils.xml.ElementMapping;



/**
 * 
 * @author Administrator
 *
 */
public class EnableIf {
	
	/**
	 * 验证关系，OR或者是AND
	 */
	private String type = "AND";
	
	/**
	 * 验证的正则表达是列表
	 */
	@ElementMapping(element="exps",type=Exp.class)
	private List<Exp> exps;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Exp> getExps() {
		return exps;
	}

	public void setExps(List<Exp> exps) {
		this.exps = exps;
	}
	
}
