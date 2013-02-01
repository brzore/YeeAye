/**  
 *@Copyright:Copyright (c) 2008 - 2012  
 *@Company:YeeAye
 */  
package com.theta.user.bean;  

import java.util.Date;
  
  
/**  
 * @Title:  YeeAye平台
 * @Description:  用户PO对象
 * @Author: brzore 
 * @Since:2013-1-7  
 * @Version:1.1.0  
 */
public class User extends UserLoginBean {
	
	private int usercode;//	用户编号
	private int groupid;//	用户组编号
	private String nickname;//	昵称
	
	private Date registertime;//	注册时间
	private Date lastlogintime;//	最后一次登陆时间
	private int status;//	状态
	
	public int getUsercode() {
		return usercode;
	}
	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
