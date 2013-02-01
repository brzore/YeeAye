package com.theta.poolman.util;

import java.util.Map;

import com.frameworkset.common.poolman.DBUtil;

/**
 * 
 * <p>Description: sql�ַ���������</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: chinacreator</p>
 * @author qingtan.long
 * @version v1.0
 * 2011.05.18
 *
 */
public class SqlUtils {
	/**
	 * @Description ת��LIKEģ����ѯ�е�SQL�����ַ�,ֻ�������ַ���ƴ�ӵĶ�̬SQL
	 * @param source ��ת�����ַ���
	 * @param escapeChar ת�����
	 * @return ת�����ַ�����
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
	 * @Description ת��LIKEģ����ѯ�е�SQL�����ַ�,ֻ������Ԥ�����SQL,Ԥ������Զ��������ŵ�����
	 * @param source ��ת�����ַ���
	 * @param escapeChar ת�����
	 * @return ת�����ַ�����
	 * @exception 
	 */
	public static String escapeForPreparedSqlLike(String source, String escapeChar) {
		String escapedStr=source.replaceAll(escapeChar, escapeChar + escapeChar)
			.replaceAll("%", escapeChar + "%")
			.replaceAll("_", escapeChar + "_");
		return escapedStr;
	}
	/**
	 * @Description ת��SQL�е������ַ�,ֻ�������ַ���ƴ�ӵĶ�̬SQL
	 * @param source ��ת�����ַ���
	 * @param escapeChar ת�����
	 * @return ת�����ַ���
	 * @exception 
	 */
	public static String escapeForSql(String source) {
		String escapedStr=source.replaceAll("'", "''").replaceAll("&", "'||chr(38)||'");
		return escapedStr;
	}
	/**
	 * ��ȡseqֵ
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
	 * @Description ����LIST��ҳ
	 * @author brzore
	 * @date  2012-8-22 ����5:55:05
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
