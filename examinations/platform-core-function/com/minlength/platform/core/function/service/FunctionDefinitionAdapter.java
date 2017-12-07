package com.minlength.platform.core.function.service;

/**
 * 功能定义适配器，用于适配老版本的功能定义，并且将老按本的功能定义输出为新版本定义文件
 * @author shugang
 *
 */
public interface FunctionDefinitionAdapter {
	
	/**
	 * 执行适配功能
	 * @param clazz
	 * @param objects
	 * @return
	 */
	public void execute(FunctionManagerService functionManagerService);

}
