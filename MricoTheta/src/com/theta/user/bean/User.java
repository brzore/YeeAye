/**  
 *@Copyright:Copyright (c) 2008 - 2012  
 *@Company:YeeAye
 */  
package com.theta.user.bean;  

import java.util.Date;
  
  
/**  
 * @Title:  YeeAyeƽ̨
 * @Description:  �û�PO����
 * @Author: brzore 
 * @Since:2013-1-7  
 * @Version:1.1.0  
 */
public class User extends UserLoginBean {
	
	private int usercode;//	�û����
	private int groupid;//	�û�����
	private String nickname;//	�ǳ�
	
	private Date registertime;//	ע��ʱ��
	private Date lastlogintime;//	���һ�ε�½ʱ��
	private int status;//	״̬
	
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
