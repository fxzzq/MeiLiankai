package com.minlength.platform.core.processor.context;

import java.util.Stack;

import com.minlength.platform.core.processor.RouteProcessor;

/**
 * 
 * <p>用于保存RouteProcessor的上下文变量</p>
 * <br/>
 * @Title: RouteProcessorContext.java 
 * @Package com.meloclan.platform.core.processor.context 
 * @author shugang
 * @date 2017 2017-5-24 下午6:17:21 
 * @version v1.0
 */
public interface RouteProcessorContext {
	
	/**
	 * 流程处理过程中的处理顺序栈
	 */
	public static final ThreadLocal<Stack<Class<? extends RouteProcessor>>> ROUTE_STACK = new ThreadLocal<Stack<Class<? extends RouteProcessor>>>();
	
	/**
	 * 当前线程正在执行的被选中的处理器单元
	 */
	public static final ThreadLocal<RouteProcessor> CURRENT_ROUTE_PROCESSOR = new ThreadLocal<RouteProcessor>();
	
	/**
	 * 记录当前线程的异常信息
	 */
	public static final ThreadLocal<Throwable> CURRENT_PROCESSOR_EXCEPTION = new ThreadLocal<Throwable>();

}
