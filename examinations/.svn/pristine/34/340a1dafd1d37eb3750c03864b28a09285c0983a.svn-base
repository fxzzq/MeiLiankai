package com.minlength.platform.core.security.manager;

import com.minlength.platform.core.security.vo.Ticket;

/**
 * 票据管理器
 * @author shugang
 *
 */
public interface TicketManager {
	
	//根据token获取票据
	public Ticket getTicketByToken(String token);
	
	/**
	 * 登记票据到缓存中
	 * @param ticket
	 */
	public void registerTicket(Ticket ticket);
	
	/**
	 * 废除一个通信凭证
	 * @param token
	 */
	public Ticket destroyTicket(String token);
	
}
