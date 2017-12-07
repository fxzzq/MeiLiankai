package com.minlength.platform.core.processor.context;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>定义ResultProcessor定义的上下文变量</p>
 * <br/>
 * @Title: ResultProcessorContext.java 
 * @Package com.meloclan.platform.core.processor.context 
 * @author shugang
 * @date 2017 2017-5-24 下午6:18:49 
 * @version v1.0
 */
public interface ResultProcessorContext {
	
	/**
	 * 用于记录当前线程中，在流程处理器处理数据工程中指定了名称的结果集
	 */
	public static final ThreadLocal<Map<String, Object>> NAMED_VALUE = new ThreadLocal<Map<String, Object>>();
	
	/**
	 * 用于记录处理器在当前线程执行过程中的所有结果对象
	 */
	public static final ThreadLocal<List<Object>> PRO_VALUE = new ThreadLocal<List<Object>>();
	

}
