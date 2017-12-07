package com.minlength.platform.core.web.context;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface WebSystemContext {
	
	//与I18N相关的上下文变量
	public static interface TEMPLATE {
		
		//i18n数据参数
		public static final String KEY_I18N = "i18n";
		
		//请求路径的basePath
		public static final String KEY_BASEPATH = "basePath";		
		
		//前端的请求参数
		public static final String KEY_REQ_PARAMS = "req_params";
		
		//当前请求的语言环境
		public static final ThreadLocal<Locale> LOCALE = new ThreadLocal<Locale>();
		
		//本次请求的默认模板替换参数
		public static final ThreadLocal<Map<String, Object>> TEMPLATE_DEFAULT_PARAMS = new ThreadLocal<Map<String, Object>>();
		
	}
	
	//日志相关的变量定义
	public static interface LOG {
		
		//WEB北向接口调用
		public static final String KEY_WEB_NORTH_IF_LOGGER = "webnorthif";
		
		//WEB南向接口
		public static final String KEY_WEB_SOUTH_IF_LOGGER = "websouthif";
		
		//数据库升级脚本执行日志
		public static final String KEY_SYSTEM_DATABASE_UPDATE_LOGGER = "dbupdate";
		
		//系统启动的时候一些关键日志
		public static final String KEY_SYSTEM_INIT_LOGGER = "init";
		
		//安全认证日志
		public static final String KEY_SECURITY_LOGGER = "security";
		
		//文件上传下载功能日志
		public static final String KEY_FILEMANAGER_LOGGER = "filemanager";
				
	}

	/**
	 * 当前线程的请求
	 */
	public static final ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<HttpServletRequest>();
	
}
