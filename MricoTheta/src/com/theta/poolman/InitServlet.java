package com.theta.poolman;

import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;

/**
 * @Title:  YeeAye
 * @Description:  数据库连接初始化
 * @Author: brzore 
 * @Since:2012-12-22  
 * @Version:1.1.0
 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger LOG = Logger.getLogger(InitServlet.class);
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SysmanagerInit SysmanagerInit = new SysmanagerInit();
		try {
			SysmanagerInit.init();
		} catch (SQLException e) {
			LOG.error("数据库连接失败"+e);
		}
	}

}
