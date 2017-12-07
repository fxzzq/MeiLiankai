package com.minlength.platform.core.function.processor;

import com.minlength.platform.core.function.vo.Function;
import com.minlength.platform.core.function.vo.Operation;
import com.minlength.platform.core.processor.ResultProcessor;

/**
 * 
 * <p>执行具体操作的处理器单元</p>
 * <br/>
 * @Title: FunctionOperationProcessor.java 
 * @Package com.meloclan.component.function.processor 
 * @author shugang
 * @date 2017 2017-5-25 下午4:49:10 
 * @version v1.0
 */
public interface FunctionOperationProcessor extends ResultProcessor, FunctionOperationProcessorContext {
	
	/**
	 * 执行具体的功能操作
	 * @param opt
	 * @return
	 */
	public FunctionOperationProcessor doFunctionOperation(String name, Operation opt);
	
	/**
	 * 进行表单验证
	 * @param function	功能
	 * @param opt		操作
	 * @return
	 */
	public FunctionOperationProcessor doValidate(Function function, Operation opt);
	
}
