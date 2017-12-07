package com.minlength.platform.core.security.vo;

import java.util.Map;

/**
 * 用户认证成功后的通行票据
 * @author shugang
 *
 */
public class Ticket {
	
	//通行令牌
	private String token;
	
	//登录账号
	private String account;
	
	//认证方式
	private AuthType authtype;
	
	//过期时间
	private long overtime;
	
	//用户的账号详细信息
	private Map<String, Object> userInfo;
	
	//认证客户端
	private AuthClient client;
	
	//当前票据状态
	private int state = -1;
	
	//登录失败的错误码
	private String failCode;
	
	//登录成功后需要跳转的地址
	private String forward;
	
	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Map<String, Object> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Map<String, Object> userInfo) {
		this.userInfo = userInfo;
	}

	public AuthType getAuthtype() {
		if(this.authtype == null && this.client != null){
			this.authtype = client.getAuthtype();
		}
		return authtype;
	}

	public void setAuthtype(AuthType authtype) {
		this.authtype = authtype;
	}

	public void setClient(AuthClient client) {
		this.authtype = client.getAuthtype();
		this.client = client;
	}

	public AuthClient getClient() {
		return client;
	}
	
	public long getOvertime() {
		return overtime;
	}

	public void setOvertime(long overtime) {
		this.overtime = overtime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public String getFailCode() {
		return failCode;
	}

	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}


	//票据状态
	public static interface STATE {
		
		//未登录		
		public static final int NOTLOGIN = 0;
		
		//已登录
		public static final int LOGIN = 1;
		
		//登录失败
		public static final int LOGIN_FAIL = 2;
		
		//已过去
		public static final int OVERDUE = 3;
		
		
	}
	
}
