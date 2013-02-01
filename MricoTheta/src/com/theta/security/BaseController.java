package com.theta.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.theta.user.bean.User;

/**
 * @Title:  
 * @Description:  登陆控制基类
 * @Author: brzore 
 * @Since:2013-1-10  
 * @Version:1.1.0
 */
public class BaseController {
	protected static final String ERROR_MSG_KEY = "errorMsg";

	/**
	 * @Description:  从session中获取当前登陆的用户对象 
	 * @param request
	 * @return   
	 * @Since:2013-1-10
	 */
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
   
	/**
	 * @Description:  将登陆的用户放入session中
	 * @param request
	 * @param user   
	 * @Since:2013-1-10
	 */
	protected void setSessionUser(HttpServletRequest request,User user) {
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT,user);
	}
	
	/**
	 * @Description:  获取请求中的绝对路径
	 * @param request
	 * @param url
	 * @return   
	 * @Since:2013-1-10
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
}
