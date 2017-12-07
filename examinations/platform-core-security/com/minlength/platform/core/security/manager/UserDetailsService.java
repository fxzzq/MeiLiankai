package com.minlength.platform.core.security.manager;

import com.minlength.platform.core.security.vo.UserDetails;

/**
 * 获取用户详细信息的接口
 * @author shugang
 *
 */
public interface UserDetailsService {
	
	/**
	 * 根据账号获取用户信息
	 * @param account
	 * @return
	 */
	public UserDetails loadUserByUsername(String account);

}
