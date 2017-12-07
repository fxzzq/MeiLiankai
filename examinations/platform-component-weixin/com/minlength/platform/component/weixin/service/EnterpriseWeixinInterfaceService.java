package com.minlength.platform.component.weixin.service;

import java.util.List;
import java.util.Map;

import com.minlength.platform.core.utils.JSONUtil;
import com.minlength.platform.core.utils.net.HttpClientUtil;

/**
 * 跟企业微信相关的接口
 * @author shugang
 *
 */
public interface EnterpriseWeixinInterfaceService {
	
	/**
	 * 获取access_token字符串
	 * @return
	 */
	public String access_token();
	
	/**
	 * 获取企业微信通讯录的部门信息
	 * @param access_token
	 * @param id		部门的id
	 * @return
	 */
	public List<Map<String, Object>> getDepartments(String access_token, String id);
	
	/**
	 * 获取部门下面的用户信息
	 * @param access_token
	 * @param department_id		部门id
	 * @param fetch_child		是否转取下级部门的用户
	 * @return
	 */
	public List<Map<String, Object>> getUserListDetail(String access_token, int department_id, int fetch_child);
	
	/**
	 * 根据code获取用不的id
	 * @param code
	 * @param access_token
	 * @return
	 */
	public String getUserIdByRedirectCode(String code, String access_token);

}
