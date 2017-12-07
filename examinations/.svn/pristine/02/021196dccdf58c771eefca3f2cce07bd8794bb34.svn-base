package com.minlength.platform.core.processor.event;

/**
 * 
 * <p>ResultProcessor处理器类中涉及到的事件</p>
 * <br/>
 * @Title: ResultProcessorEvent.java 
 * @Package com.meloclan.platform.core.processor.event 
 * @author shugang
 * @date 2017 2017-5-24 下午4:15:30 
 * @version v1.0
 */
public interface ResultProcessorEvent {
	
	/**
	 * 当前线程处理器调用end方法的时候调用该方法
	 * @param clazz		执行该事件的具体实例类,为调用栈中的具体实现类
	 */
	public void onProcessorEnd(Class<?> clazz);
	
	/**
	 * 当前线程处理器执行过程中如果出现异常,调用的方法
	 * @param clazz		执行该事件的具体实例类,为调用栈中的具体实现类
	 */
	public void onPorcessorException(Class<?> clazz);

}
