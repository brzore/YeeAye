/**  
 *@Copyright:Copyright (c) 2008 - 2012  
 *@Company:YeeAye
 */  
package com.theta.quartzJob;  

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.frameworkset.common.poolman.PreparedDBUtil;
  
  
/**  
 * @Title:  YeeAye短文网
 * @Description:  自动抓取网上的的内容
 * @Author: brzore 
 * @Since:2012-12-30  
 * @Version:1.1.0  
 */
public class ReadOhterWebSite {
	
	private static final Logger log=Logger.getLogger(ReadOhterWebSite.class);
	
	public static void main(String ...strings) throws IOException {
		String urlpath = "http://10juhua.com";		
		String regex = "(?<=<div class=\"body\">)(.*)(?=<br/>)";
		ReadOhterWebSite webSite=new ReadOhterWebSite();
		webSite.getContentFormULR(urlpath, regex);
		
		for(String str : webSite.getContentFormULR(urlpath, regex)){
			System.out.println(str);
		}
	}
	
	/**
	 * @Description:  执行抓取方法
	 * @throws IOException   
	 * @Since:2013-1-9
	 */
	public void execute() throws IOException {
//		String urlpath = "http://10juhua.com";		
//		String regex = "(?<=<div class=\"body\">)(.*)(?=<br/>)";
//		ReadOhterWebSite webSite=new ReadOhterWebSite();
//		webSite.getContentFormULR(urlpath, regex);
		
	}
	
	/**
	 * @Description:  读取指定网站的内容（内容通过正则表达式匹配获得）
	 * @param url	指定网站内容网址
	 * @param regex 规则正则表达式
	 * @return   
	 * @Since:2012-12-30
	 */
	List<String> getContentFormULR(String urlpath , String regex){
		BufferedReader reader=null;
		List<String> content=new ArrayList<String>();
		
		try {
			URL url = new URL(urlpath);			
			reader = new BufferedReader(new InputStreamReader(url.openStream()));	
			
			//开始读取流内容解析
			String readString = reader.readLine();			
			while (readString != null) {
				content.addAll(findContent(readString , regex));
				
				readString = reader.readLine();
			}
			
			//保存入库
			saveTepm(content);
			
		} catch (MalformedURLException e) {
			
			log.info("从网络上抓取内容出错");
			log.info("网址："+urlpath+"解析出错");
			log.error(e.getMessage());
			
			e.printStackTrace();
		} catch (IOException e) {
			
			log.info("从网络上抓取内容出错");
			log.info("网址："+urlpath+"读取内容出错");
			log.error(e.getMessage());
			
			e.printStackTrace();
		}finally{
			
			if(reader!=null){
				try {
					
					reader.close();
					
				} catch (IOException e) {
					
					log.info("从网络上抓取内容出错");
					log.info("网址："+urlpath+"关闭流出错");
					log.error(e.getMessage());
					
					e.printStackTrace();
				}
			}
			
		}
		
		return content;
	}

	/**
	 * @Description:  根据正则表达式匹配内容
	 * @param readString 源字符串
	 * @param regex  正则表达式
	 * @return   
	 * @Since:2012-12-30
	 */
	private List<String> findContent(String readString , String regex){
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(readString);
		
		List<String> content=new ArrayList<String>();
		
		while (m.find()) {
			content.add(m.group());
		}
		
		return content;
	}
	
	/**
	 * @Description:  抓取内容后保存至临时表
	 * @param content   
	 * @Since:2012-12-30
	 */
	void saveTepm(List<String> content){
		PreparedDBUtil pdbUtil=new PreparedDBUtil();
		String sql="insert into TB_OTHERWEBSITE (CONTENT) values(?)";
		
		try {
			
			for(String str : content){
				int index=1;
				
				pdbUtil.preparedInsert(sql);
				pdbUtil.setString(index++, str);
				pdbUtil.executePrepared();
				
			}
			
		} catch (SQLException e) {
			log.info("将临时记录保存到数据库临时表中");
			log.error(e.getMessage());
			
			e.printStackTrace();
		}
		
	}
}
