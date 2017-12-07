package com.minlength.platform.core.security.manager;

import com.minlength.platform.core.security.vo.AuthClient;
import com.minlength.platform.core.security.vo.Ticket;

/**
 * 具体的认证处理实体类
 * @author shugang
 *
 */
public interface AuthProvider {
	
	public Ticket auth(AuthClient client);
	
	public Ticket checkTicket(String token, AuthClient client);
	
	/**
	 * 退出用户登录
	 * @param token	用户的登录凭证
	 * @param client	客户端信息
	 */
	public Ticket logout(String token, AuthClient client);
	
}
