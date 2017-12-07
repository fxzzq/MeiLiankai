package com.minlength.platform.core.security.manager;

import com.minlength.platform.core.security.vo.AuthClient;
import com.minlength.platform.core.security.vo.Ticket;

/**
 * 系统的认证管理器
 * @author shugang
 *
 */
public interface AuthManager {

	//用户认证
	public Ticket auth(AuthClient client);
	
	//对票据凭证的验证
	public Ticket checkTicket(String token, AuthClient client);
	
	/**
	 * 退出用户登录
	 * @param token	用户的登录凭证
	 * @param client	客户端信息
	 */
	public Ticket logout(String token, AuthClient client);
	
}
