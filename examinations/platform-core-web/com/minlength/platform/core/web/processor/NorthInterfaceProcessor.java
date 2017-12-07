package com.minlength.platform.core.web.processor;

import javax.servlet.http.HttpServletRequest;

import com.minlength.platform.core.processor.ResultProcessor;

/**
 * 北向接口调用处理器
 * @author shugang
 *
 */
public interface NorthInterfaceProcessor extends ResultProcessor {
	
	/**
	 * 接收Application类型的JSON数据，该方法通常在实现 北向接口的时候使用
	 * @param name
	 * @param request
	 * @return
	 */
	public NorthInterfaceProcessor acceptParameterApplicationJson(String name, HttpServletRequest request);
	
	/**
	 * 接收Application类型的JSON数据，该方法通常在实现 北向接口的时候使用
	 * @param request
	 * @return
	 */
	public NorthInterfaceProcessor acceptParameterApplicationJson(HttpServletRequest request);

}
