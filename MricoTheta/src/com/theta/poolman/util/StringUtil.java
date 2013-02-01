package com.theta.poolman.util;

/**
 * @Title:  YeeAye
 * @Description:  �ַ���������
 * @Author: brzore 
 * @Since:2012-12-22  
 * @Version:1.1.0
 */
public class StringUtil {
	
	/**
	 * @Description:  ��֤�Ƿ�Ϊ��
	 * @param str
	 * @return   
	 * @Since:2012-12-22
	 */
	public static boolean checkString(String str){
		if((null != str) && (!"".equals(str))){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * @Description:  �ַ���ȥ��
	 * @param str
	 * @return   
	 * @Since:2012-12-22
	 */
	public static String defaultString(String str){
		if(null == str){
			return "";
		}
		return str;
	}
	
	/**
	 * @Description:  ����ȥ��
	 * @param obj
	 * @return   
	 * @Since:2012-12-22
	 */
	public static Object defaultString(Object obj){
		if(null == obj){
			return "";
		}
		return obj;
	}
	
}

