package com.minlength.platform.core.processor;

/**
 * 
 * <p>用户自定义的处理单元类</p>
 * <br/>
 * @Title: CustomProcessor.java 
 * @Package com.meloclan.platform.core.processor 
 * @author shugang
 * @date 2017 2017-5-24 下午5:24:00 
 * @version v1.0
 */
public interface CustomProcessor extends ResultProcessor {
	
	/**
	 * 执行自定义的处理单元
	 * @param handler	真实的处理方法
	 * @param objects	传入的完毕参数，这些参数将会传入到CustomProcessorHandler中
	 * @return
	 */
	public CustomProcessor execute(CustomProcessorHandler handler, Object...objects);
	
	/**
	 * 执行自定义的处理单元
	 * @param name		执行后参数的名称
	 * @param handler	真实的处理方法
	 * @param objects	传入的完毕参数，这些参数将会传入到CustomProcessorHandler中
	 * @return
	 */
	public CustomProcessor execute(String name, CustomProcessorHandler handler, Object...objects);

}
