package com.minlength.platform.core.security.manager;

import javax.servlet.http.HttpServletRequest;

import com.minlength.platform.core.security.vo.AuthClient;

/**
 * 认证客户端的识别器，用于识别客户端类型以及收集客户端信息
 * @author shugang
 *
 */
public interface AuthClientDiscriminator {
	
	/**
	 * 对请求的客户端进行识别
	 * @param request	请求对象
	 * @return
	 */
	public AuthClient discriminate(HttpServletRequest request);

}
