package com.theta.poolman.util;

/**
 * @Title:  YeeAye
 * @Description:  字符串处理类
 * @Author: brzore 
 * @Since:2012-12-22  
 * @Version:1.1.0
 */
public class StringUtil {
	
	/**
	 * @Description:  验证是否为空
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
	 * @Description:  字符串去空
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
	 * @Description:  对象去空
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

