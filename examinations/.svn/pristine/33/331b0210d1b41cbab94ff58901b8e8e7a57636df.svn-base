package com.minlength.platform.core.function.processor;

import org.springframework.context.ApplicationContext;

import com.minlength.platform.core.function.vo.Function;
import com.minlength.platform.core.function.vo.Operation;

public interface FunctionOperationProcessorContext {
	
	public static ThreadLocal<ApplicationContext> ApplicationContext = new ThreadLocal<ApplicationContext>();
	
	/**
	 * 当前功能
	 */
	public static ThreadLocal<Function> CURRENT_FUNCTION = new ThreadLocal<Function>();
	
	/**
	 * 当前操作
	 */
	public static ThreadLocal<Operation> CURRENT_OPERATION = new ThreadLocal<Operation>();
		
}
