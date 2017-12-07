package com.minlength.platform.core.web.processor;

import javax.servlet.http.HttpServletRequest;

import com.minlength.platform.core.processor.sys.SystemProcessor;

public interface WebSystemProcessor extends SystemProcessor{

	/**
	 * 接收客户端通过web请求传入的参数信息
	 * @param name		参数接收后的名称
	 * @param request	http请求
	 * @return
	 */
	public WebSystemProcessor acceptParameter(String name, HttpServletRequest request);
	
	/**
	 * 接收客户端通过web请求传入的参数信息
	 * @param request
	 * @return
	 */
	public WebSystemProcessor acceptParameter(HttpServletRequest request);
	
	public WebSystemProcessor acceptParameterApplicationJson(String name, HttpServletRequest request);
	
	public WebSystemProcessor acceptParameterApplicationJson(HttpServletRequest request);
	
}
