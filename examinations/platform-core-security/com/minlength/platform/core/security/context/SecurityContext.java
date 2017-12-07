package com.minlength.platform.core.security.context;

import java.util.Map;

import com.minlength.platform.core.security.vo.AuthClient;

/**
 * 系统安全模块的一些上下文变量
 * @author shugang
 *
 */
public interface SecurityContext {
	
	/**
	 * 系统当前的登录认证用户
	 */
	public static ThreadLocal<String> CURRENT_LOGIN_USER_ACCOUNT = new ThreadLocal<String>();
	
	//当前的登录用户详细信息
	public static ThreadLocal<Map<String, Object>> CURRENT_LOGIN_USER_DETAILS = new ThreadLocal<Map<String, Object>>();
	
	/**
	 * 当前认证客户端
	 */
	public static ThreadLocal<AuthClient> CURRENT_AUTH_CLIENT = new ThreadLocal<AuthClient>();
	
	//默认提供的一些宏定义
	public static interface DEFAULT_MACRO_KEY {
		//当前用户ID宏
		public static final String MACRO_CURRENT_LOGIN_USER_ID = "current_user_id";
		//当前登录用户账号宏
		public static final String MACRO_CURRENT_LOGIN_ACCOUNT = "current_user_account";
	}
	
	//一些默认的页面key值
	public static interface DEFAULT_PAGE_KEY {
		
		//表单验证的用户名
		public static final String FORM_AUTH_ACCOUNT = "account";
		
		//表单验证的密码
		public static final String FORM_AUTH_PASSWORD = "pwd";
		
	}
	
}
