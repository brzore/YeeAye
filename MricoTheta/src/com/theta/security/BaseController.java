package com.theta.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.theta.user.bean.User;

/**
 * @Title:  
 * @Description:  ��½���ƻ���
 * @Author: brzore 
 * @Since:2013-1-10  
 * @Version:1.1.0
 */
public class BaseController {
	protected static final String ERROR_MSG_KEY = "errorMsg";

	/**
	 * @Description:  ��session�л�ȡ��ǰ��½���û����� 
	 * @param request
	 * @return   
	 * @Since:2013-1-10
	 */
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
   
	/**
	 * @Description:  ����½���û�����session��
	 * @param request
	 * @param user   
	 * @Since:2013-1-10
	 */
	protected void setSessionUser(HttpServletRequest request,User user) {
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT,user);
	}
	
	/**
	 * @Description:  ��ȡ�����еľ���·��
	 * @param request
	 * @param url
	 * @return   
	 * @Since:2013-1-10
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url����Ϊ��");
		Assert.isTrue(url.startsWith("/"), "������/��ͷ");
		return request.getContextPath() + url;
	}
}
