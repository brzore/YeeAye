package com.theta.poolman;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.frameworkset.common.poolman.util.SQLManager;
/**
 * @Title:  ��YeeAye
 * @Description:  ��ʼ�����ӳ�
 * @Author: brzore 
 * @Since:2012-12-22  
 * @Version:1.1.0
 */
public class SysmanagerInit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(SysmanagerInit.class);
	
	public void init() throws SQLException {
		LOG.info("��ʼ�����ݿ����ӳ�.");
		SQLManager.getInstance().returnConnection(SQLManager.getInstance().requestConnection());
		LOG.info("��ʼ�����ݿ����ӳ����.");
	}

	public void destroy() {

	}

}
