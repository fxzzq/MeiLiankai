package com.minlength.platform.core.processor;

/**
 * 
 * <p>自定义处理单元的实际处理代码</p>
 * <br/>
 * @Title: CustomProcessorHandler.java 
 * @Package com.meloclan.platform.core.processor 
 * @author shugang
 * @date 2017 2017-5-24 下午5:23:08 
 * @version v1.0
 */
public interface CustomProcessorHandler {
	
	/**
	 * 执行自定义处理单元
	 * @param objects	保留参数，有用于自行定义
	 * @return
	 */
	public Object execute(Object...objects);

}
