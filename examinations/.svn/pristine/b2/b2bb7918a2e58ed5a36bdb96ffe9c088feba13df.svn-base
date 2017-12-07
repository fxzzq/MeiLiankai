package com.minlength.platform.core.processor;

import java.util.Map;

import com.minlength.platform.core.processor.context.RunnableProcessorContext;

/**
 * 
 * <p>定义可启动执行的处理器接口</p>
 * <br/>
 * @Title: RunnableProcessor.java 
 * @Package com.meloclan.platform.core.processor 
 * @author shugang
 * @date 2017 2017-5-12 下午6:44:37 
 * @version v1.0
 */
public interface RunnableProcessor extends ResultProcessor, RunnableProcessorContext {	
		
	/**
	 * 在线程中启动流程处理器，并且将初始参数传入到流程处理器当前线程中
	 * @param input		初始参数
	 * @return			
	 */
	public RunnableProcessor start(Map<String, Object> input);
	
	public RunnableProcessor start();
	
	/**
	 * 结束当前线程的处理器
	 * @return
	 */
	public RunnableProcessor end();
	
	/**
	 * 结束当前线程的处理器,发生异常调用此方法
	 * @param isException	true：发生异常，false为正常退出
	 * @param t				发生的具体异常
	 * @return
	 */
	public RunnableProcessor end(boolean isException, Throwable t);
	
	/**
	 * 处理器在当前线程的状态
	 * @return
	 */
	public ProcState getState();
	
	public static enum ProcState{
		NOTSTARTED(0, "NOTSTARTED"),		
		RUNNING(1 << 4, "RUNNING"),
		COMPLETE(1 << 8, "COMPLETE")
		;
		private int value;
		private String name;
		private ProcState(int value, String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
		
	}
	
}
