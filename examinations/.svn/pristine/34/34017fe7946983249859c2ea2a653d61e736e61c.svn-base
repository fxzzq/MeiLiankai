package com.minlength.platform.core.processor;

import com.minlength.platform.core.processor.context.ResultProcessorContext;

/**
 * 
 * <p>可记录结果的流程处理器</p>
 * <br/>
 * @Title: ResultProcessor.java 
 * @Package com.meloclan.platform.core.processor 
 * @author shugang
 * @date 2017 2017-5-13 下午6:05:41 
 * @version v1.0
 */
public interface ResultProcessor extends RouteProcessor, ResultProcessorContext{
	
	/**
	 * 根据名称，获取结果
	 * @param name		在某个处理器执行节点时，取的名称
	 * @param clazz		结果的类型
	 * @return
	 */
	public <T extends Object> T getResult(String name, Class<T> clazz);
	
	/**
	 * 最近一个处理节点的执行结果
	 * @param clazz		结果的类型
	 * @return
	 */
	public <T extends Object> T preValue(Class<T> clazz);
	
	/**
	 * 结束一个流程执行，并且返回最近一次的执行结果
	 * @param clazz			结果类型
	 * @return
	 */
	public <T extends Object> T end(Class<T> clazz);
	
	/**
	 * 结束一个流程执行，并且返回自定名称的处理单元结果
	 * @param name		处理单元处理结果名称
	 * @param clazz		结果类型
	 * @return
	 */
	public <T extends Object> T end(String name, Class<T> clazz);
	
	
}
