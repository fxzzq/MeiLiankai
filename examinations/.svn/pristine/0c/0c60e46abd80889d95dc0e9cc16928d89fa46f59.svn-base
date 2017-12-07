package com.minlength.platform.core.utils;

import org.apache.log4j.Logger;

/**
 * 
 * <p>日志记录工具类</p>
 * <br/>
 * @Title: DebugUtil.java 
 * @Package com.meloclan.platform.core.utils 
 * @author shugang
 * @date 2017 2017-5-24 上午10:41:17 
 * @version v1.0
 */
public class DebugUtil {
	
	private Logger logger;
	
	private DebugUtil(Logger logger){
		this.logger = logger;
	}
	
	public static DebugUtil getInstance(String name){
		Logger logger = Logger.getLogger(name);		
		return new DebugUtil(logger);
	}
	
	public static DebugUtil getInstance(Class<?> clazz){
		Logger logger = Logger.getLogger(clazz);		
		return new DebugUtil(logger);
	}
	
	public void info(String message, Throwable t){
		if(this.logger == null){return;}
		this.logger.info(message, t);
	}
	
	public void info(String message){
		if(this.logger == null){return;}
		this.logger.info(message);
	}
	
	public void info(Throwable t){
		if(this.logger == null){return;}
		this.logger.info(t);
	}
	
	public void warn(String message, Throwable t){
		if(this.logger == null){return;}
		this.logger.warn(message, t);
	}
	
	public void warn(String message){
		if(this.logger == null){return;}
		this.logger.warn(message);
	}
	
	public void warn(Throwable t){
		if(this.logger == null){return;}
		this.logger.warn(t);
	}
	
	public void error(String message, Throwable t){
		if(this.logger == null){return;}
		this.logger.error(message, t);
	}
	
	public void error(String message){
		if(this.logger == null){return;}
		this.logger.error(message);
	}
	
	public void error(Throwable t){
		if(this.logger == null){return;}
		this.logger.error(t.getMessage(),t);
	}
	
	public void debug(String message, Throwable t){
		if(this.logger == null){return;}
		this.logger.debug(message, t);
	}
	
	public void debug(String message){
		if(this.logger == null){return;}
		this.logger.debug(message);
	}
	
	public void debug(Throwable t){
		if(this.logger == null){return;}
		this.logger.debug(t);
	}
	
}
