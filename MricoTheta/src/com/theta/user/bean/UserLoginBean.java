/**  
 *@Copyright:Copyright (c) 2008 - 2012  
 *@Company:YeeAye
 */  
package com.theta.user.bean;  
  
/**  
 * @Title:  YeeAye平台
 * @Description:  用户信息PO
 * @Author: brzore 
 * @Since:2013-1-3  
 * @Version:1.1.0  
 */
public class UserLoginBean {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		//加密算法
		this.password = password;
	}
}
