package com.minlength.platform.core.processor;

import com.minlength.platform.core.processor.context.RouteProcessorContext;

/**
 * 
 * <p>可在各个处理单元路由的流程处理器</p>
 * <br/>
 * @Title: RouteProcessor.java 
 * @Package com.meloclan.platform.core.processor 
 * @author shugang
 * @date 2017 2017-5-13 下午6:01:07 
 * @version v1.0
 */
public interface RouteProcessor extends MagicProcessor, RouteProcessorContext{	
	
	/**
	 * 将当前线程的处理器，路由到特定的处理单元集合中
	 * @param clazz		处理单元集合接口，也是一个处理器
	 * @return
	 */
	public <T extends RouteProcessor> T routeTo(Class<T> clazz);
	
	/**
	 * 增强一个处理器的能力，添加新的处理功能
	 * @param clazz		处理单元集合接口，也是一个处理器
	 * @param instance	接口的实例
	 */
	public <T extends RouteProcessor> void enhance(Class<? extends RouteProcessor> clazz, T instance);
		
}
