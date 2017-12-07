package com.minlength.platform.core.security.vo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证客户端信息
 * @author shugang
 *
 */
public class AuthClient {
	//客户端的类型
	private RequestClientType clientType;
	
	//认证后的客户端token信息
	private String token;
	
	//客户端的认证类型
	private AuthType authtype;
	
	//参数
	private Map<String, Object> params;
	
	private String sessionid;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public AuthType getAuthtype() {
		return authtype;
	}

	public void setAuthtype(AuthType authtype) {
		this.authtype = authtype;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getToken() {
		if(this.token == null || "".equals(this.token.trim())){this.token = this.sessionid;}
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public RequestClientType getClientType() {
		return clientType;
	}

	public void setClientType(RequestClientType clientType) {
		this.clientType = clientType;
	}
	
}
