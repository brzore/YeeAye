package com.theta.poolman;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.frameworkset.common.poolman.util.SQLManager;
/**
 * @Title:  　YeeAye
 * @Description:  初始化连接池
 * @Author: brzore 
 * @Since:2012-12-22  
 * @Version:1.1.0
 */
public class SysmanagerInit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(SysmanagerInit.class);
	
	public void init() throws SQLException {
		LOG.info("初始化数据库链接池.");
		SQLManager.getInstance().returnConnection(SQLManager.getInstance().requestConnection());
		LOG.info("初始化数据库链接池完毕.");
	}

	public void destroy() {

	}

}
