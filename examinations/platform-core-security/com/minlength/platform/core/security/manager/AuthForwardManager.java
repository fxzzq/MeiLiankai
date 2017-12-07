package com.minlength.platform.core.security.manager;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minlength.platform.core.security.vo.Ticket;

/**
 * 认证完成后的跳转管理器
 * @author shugang
 *
 */
public interface AuthForwardManager {
	
	/**
	 * 根据认证后的票据
	 * @param ticket
	 */
	public void doForward(Ticket ticket, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
