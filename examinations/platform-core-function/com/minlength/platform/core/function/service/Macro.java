package com.minlength.platform.core.function.service;

/**
 * 自定义宏接口
 * @author shugang
 *
 */
public interface Macro {

	/**
	 * 获取自定义宏的名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 获取自定义宏的值
	 * @return
	 */
	public Object getValue();
	
	
	
}
