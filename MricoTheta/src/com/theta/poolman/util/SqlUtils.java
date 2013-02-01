package com.theta.poolman.util;

import java.util.Map;

import com.frameworkset.common.poolman.DBUtil;

/**
 * 
 * <p>Description: sql字符处理工具类</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: chinacreator</p>
 * @author qingtan.long
 * @version v1.0
 * 2011.05.18
 *
 */
public class SqlUtils {
	/**
	 * @Description 转义LIKE模糊查询中的SQL特殊字符,只可用于字符串拼接的动态SQL
	 * @param source 需转换的字符串
	 * @param escapeChar 转义符号
	 * @return 转义后的字符串　
	 * @exception 
	 */
	public static String escapeForSqlLike(String source, String escapeChar) {
		String escapedStr = source.replaceAll("'", "''")
			.replaceAll("&", "'||chr(38)||'")
			.replaceAll(escapeChar, escapeChar + escapeChar)
			.replaceAll("%", escapeChar + "%")
			.replaceAll("_", escapeChar + "_");
		return escapedStr;
}
	/**
	 * @Description 转义LIKE模糊查询中的SQL特殊字符,只可用于预编译的SQL,预编译会自动处理单引号的问题
	 * @param source 需转换的字符串
	 * @param escapeChar 转义符号
	 * @return 转义后的字符串　
	 * @exception 
	 */
	public static String escapeForPreparedSqlLike(String source, String escapeChar) {
		String escapedStr=source.replaceAll(escapeChar, escapeChar + escapeChar)
			.replaceAll("%", escapeChar + "%")
			.replaceAll("_", escapeChar + "_");
		return escapedStr;
	}
	/**
	 * @Description 转义SQL中的特殊字符,只可用于字符串拼接的动态SQL
	 * @param source 需转换的字符串
	 * @param escapeChar 转义符号
	 * @return 转义后的字符串
	 * @exception 
	 */
	public static String escapeForSql(String source) {
		String escapedStr=source.replaceAll("'", "''").replaceAll("&", "'||chr(38)||'");
		return escapedStr;
	}
	/**
	 * 获取seq值
	 * @param seqName
	 * @return
	 */
	public static String getSeq(String seqName){
		try{
			DBUtil db = new DBUtil () ;
			String sql ="select "+seqName+".nextval  from dual  " ; 
			db.executeSelect(sql) ; 
			return db.getString(0, 0) ; 
		}catch(Exception e){
			return "" ; 
		}
	}
	
	/**
	 * @Description 计算LIST分页
	 * @author brzore
	 * @date  2012-8-22 下午5:55:05
	 * @return Map<String,String>
	 */
	public static Map<String,String> getPageSize(String page,String pageSize,Map<String, String> param){
		if(page!=null&&pageSize!=null){
			Integer intPage=Integer.parseInt(page);
			Integer intPageSize=Integer.parseInt(pageSize);
			String endNumber=""+intPage*intPageSize;
			String startNumber=""+(intPage-1)*intPageSize;
			param.put("startNumber", startNumber);
			param.put("endNumber", endNumber);
		}
		return param;
	}
}
