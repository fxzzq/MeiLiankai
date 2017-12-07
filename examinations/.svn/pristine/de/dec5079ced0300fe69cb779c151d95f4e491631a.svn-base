package com.minlength.platform.core.web.processor;

import javax.servlet.http.HttpServletRequest;

import com.minlength.platform.core.processor.ResultProcessor;

/**
 * 
 * <p>用于处理web请求的一些常用操作处理单元</p>
 * <br/>
 * @Title: WebRequestProcessor.java 
 * @Package com.meloclan.component.function.processor 
 * @author shugang
 * @date 2017 2017-5-25 上午10:11:45 
 * @version v1.0
 */
public interface WebRequestProcessor extends ResultProcessor {
	
	/**
	 * 接收客户端通过web请求传入的参数信息
	 * @param name		参数接收后的名称
	 * @param request	http请求
	 * @return
	 */
	public WebRequestProcessor acceptParameter(String name, HttpServletRequest request);
	
	/**
	 * 接收客户端通过web请求传入的参数信息
	 * @param request
	 * @return
	 */
	public WebRequestProcessor acceptParameter(HttpServletRequest request);	

}
