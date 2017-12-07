package com.minlength.platform.core.web.processor;

import com.minlength.platform.core.processor.ResultProcessor;

/**
 * 南向接口调用处理器
 * @author shugang
 *
 */
public interface SouthInterfaceProcessor extends ResultProcessor {
	
	/**
	 * 发送httppost请求,以application/json形式发送
	 * @param httpUrl	请求地址
	 * @param params	请求参数
	 * @return
	 */
    public SouthInterfaceProcessor sendHttpPostApplicationJson(String httpUrl, String params);
    
    /**
	 * 发送httppost请求,以application/json形式发送
	 * @param name      运行结果参数保存名称
	 * @param httpUrl	请求地址
	 * @param params	请求参数
	 * @return
	 */
    public SouthInterfaceProcessor sendHttpPostApplicationJson(String name, String httpUrl, String params);
    
    /**
     * 发送httpput请求,以application/json形式发送
     * @param httpUrl
     * @param params
     * @return
     */
    public SouthInterfaceProcessor sendHttpPutApplicationJson(String httpUrl, String params);
    
    /**
     * 发送httpput请求,以application/json形式发送
     * @param name      运行结果参数保存名称
     * @param httpUrl
     * @param params
     * @return
     */
    public SouthInterfaceProcessor sendHttpPutApplicationJson(String name, String httpUrl, String params);
    
    /**
     * 发送httpdelete请求,以application/json形式发送
     * @param httpUrl
     * @param params
     * @return
     */
    public SouthInterfaceProcessor sendHttpDeleteApplicationJson(String httpUrl, String params);
    
    /**
     * 发送httpdelete请求,以application/json形式发送
     * @param name      运行结果参数保存名称
     * @param httpUrl
     * @param params
     * @return
     */
    public SouthInterfaceProcessor sendHttpDeleteApplicationJson(String name, String httpUrl, String params);
    
    /**
     * 发送httpget请求
     * @param httpUrl
     * @return
     */
    public SouthInterfaceProcessor sendHttpGet(String httpUrl); 
    
    /**
     * 发送httpget请求
     * @param name      运行结果参数保存名称
     * @param httpUrl
     * @return
     */
    public SouthInterfaceProcessor sendHttpGet(String name, String httpUrl); 
    
    /**
     * 发送httpget请求
     * @param httpUrl
     * @return
     */
    public SouthInterfaceProcessor sendHttpsGet(String httpUrl); 
    
    /**
     * 发送httpget请求
     * @param name      运行结果参数保存名称
     * @param httpUrl
     * @return
     */
    public SouthInterfaceProcessor sendHttpsGet(String name, String httpUrl); 

}
