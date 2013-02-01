package com.theta.security;

/**
 * @Title:  
 * @Description:  整个应用通用的常量
 * @Author: brzore 
 * @Since:2013-1-10  
 * @Version:1.1.0
 */
public class CommonConstant
{
   /**
    * 用户对象放到Session中的键名称
    */
   public static final String USER_CONTEXT = "USER_CONTEXT";
   
   /**
    * 将登录前的URL放到Session中的键名称
    */
   public static final String LOGIN_TO_URL = "toUrl";
   
   /**
    * 每页的记录数
    */
   public static final int PAGE_SIZE = 10;
}
