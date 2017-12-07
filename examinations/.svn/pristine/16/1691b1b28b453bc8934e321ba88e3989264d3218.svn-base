package com.minlength.platform.core.processor.context;

import java.util.Map;

import com.minlength.platform.core.processor.RunnableProcessor;
import com.minlength.platform.core.processor.RunnableProcessor.ProcState;

/**
 * 
 * <p>用于保存RunnableProcessor定义的上下文变量</p>
 * <br/>
 * @Title: RunnableProcessorContext.java 
 * @Package com.meloclan.platform.core.processor.context 
 * @author shugang
 * @date 2017 2017-5-24 下午6:20:38 
 * @version v1.0
 */
public interface RunnableProcessorContext {
	
	/**
	 * 当前线程处理器的状态
	 */
	public static final ThreadLocal<ProcState> STATE = new ThreadLocal<RunnableProcessor.ProcState>();

	/**
	 * 处理器在当前线程启动的时候，传入的初始参数
	 */
	public static final ThreadLocal<Map<String, Object>> INPUT = new ThreadLocal<Map<String,Object>>();
	

}
