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
 * @Title:  YeeAye������
 * @Description:  �Զ�ץȡ���ϵĵ�����
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
	 * @Description:  ִ��ץȡ����
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
	 * @Description:  ��ȡָ����վ�����ݣ�����ͨ��������ʽƥ���ã�
	 * @param url	ָ����վ������ַ
	 * @param regex ����������ʽ
	 * @return   
	 * @Since:2012-12-30
	 */
	List<String> getContentFormULR(String urlpath , String regex){
		BufferedReader reader=null;
		List<String> content=new ArrayList<String>();
		
		try {
			URL url = new URL(urlpath);			
			reader = new BufferedReader(new InputStreamReader(url.openStream()));	
			
			//��ʼ��ȡ�����ݽ���
			String readString = reader.readLine();			
			while (readString != null) {
				content.addAll(findContent(readString , regex));
				
				readString = reader.readLine();
			}
			
			//�������
			saveTepm(content);
			
		} catch (MalformedURLException e) {
			
			log.info("��������ץȡ���ݳ���");
			log.info("��ַ��"+urlpath+"��������");
			log.error(e.getMessage());
			
			e.printStackTrace();
		} catch (IOException e) {
			
			log.info("��������ץȡ���ݳ���");
			log.info("��ַ��"+urlpath+"��ȡ���ݳ���");
			log.error(e.getMessage());
			
			e.printStackTrace();
		}finally{
			
			if(reader!=null){
				try {
					
					reader.close();
					
				} catch (IOException e) {
					
					log.info("��������ץȡ���ݳ���");
					log.info("��ַ��"+urlpath+"�ر�������");
					log.error(e.getMessage());
					
					e.printStackTrace();
				}
			}
			
		}
		
		return content;
	}

	/**
	 * @Description:  ����������ʽƥ������
	 * @param readString Դ�ַ���
	 * @param regex  ������ʽ
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
	 * @Description:  ץȡ���ݺ󱣴�����ʱ��
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
			log.info("����ʱ��¼���浽���ݿ���ʱ����");
			log.error(e.getMessage());
			
			e.printStackTrace();
		}
		
	}
}
