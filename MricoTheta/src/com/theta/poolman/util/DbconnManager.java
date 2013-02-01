package com.theta.poolman.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.transaction.UserTransaction;
import com.frameworkset.common.poolman.DBUtil;
public class DbconnManager {

	private static DbconnManager instance = null;

	protected DbconnManager() {
	}

	public static DbconnManager getInstance() {
		if (instance == null) {
			synchronized (com.theta.poolman.util.DbconnManager.class) {
				if (instance == null) {
					instance = new com.theta.poolman.util.DbconnManager();
				}
			}
		}
		return instance;
	}

	/**
	 * ��ȡ������������
	 * 
	 * @return
	 * @throws java.sql.SQLException
	 */

	public Connection getConnection() throws java.sql.SQLException {
		return this.getPoolManConnection();
		//return this.getConn();
	}


	/**
	 * ��poolman��ȡ���Ӷ���
	 * 
	 * @return Connection
	 * @throws SQLException
	 */

	public Connection getPoolManConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	

	/**
	 * ��ȡ���������ݿ�����
	 * 
	 * @return Connection
	 */
	public Connection getTransactionConnection() throws SQLException {
		Connection conn = this.getPoolManConnection(); 
		conn.setAutoCommit(false);
		return conn;
	}

	/**
	 * �ر����������ݿ�����
	 * 
	 * @return Connection
	 */
	public static void closeTransactionConnection(Connection conn,
			boolean abortTransaction) {
		// �ж�����Դ�Ƿ����
		if (conn != null) {
			try {
				DatabaseMetaData metaData = conn.getMetaData();
				if (metaData.supportsTransactions()) {
					// �ع�/�ύ����
					if (abortTransaction) {
						conn.commit();
					} else {
						conn.rollback();
					}
					conn.setAutoCommit(true);
				}
				conn.close();
				conn = null;
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * �رշ����������ݿ�����
	 * 
	 * @return Connection
	 */
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				if (!con.getAutoCommit()) {
					con.commit();
					con.setAutoCommit(true);
				}
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * �ر��������
	 * 
	 * @return Connection
	 */
	public static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * �ر��������
	 * 
	 * @return Connection
	 */
	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * �رղ�ѯ�����
	 * 
	 * @return Connection
	 */
	public static void closeResultSet(ResultSet rst) {
		try {
			if (rst != null) {
				rst.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * �ر����ж�����ͨ�����ݿ����ӣ�
	 * 
	 * @return Connection
	 */
	public static void closeResource(Connection conn, PreparedStatement pstmt,
			ResultSet rst) {
		closeResultSet(rst);
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}

	/**
	 * �ر����ж�����ͨ�����ݿ����ӣ�
	 * 
	 * @return Connection
	 */
	public static void closeResource(Connection conn, PreparedStatement pstmt) {
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}

	/**
	 * �ر����ж��󣨴���������ݿ����ӣ�
	 * 
	 * @return Connection
	 */
	public static void closeResource(Connection conn, PreparedStatement pstmt,
			boolean bool) {
		closePreparedStatement(pstmt);
		closeTransactionConnection(conn, bool);
	}

	/**
	 * �ر�JTA��������
	 * 
	 * @return UserTransaction
	 */
	public static void closeUserTransaction(UserTransaction tx, boolean bool) {
		try {
			if (tx != null) {
				if (bool) {
					tx.rollback();
				} else {
					tx.commit();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * �رմ洢�������
	 * 
	 * @param callableStatement
	 *            CallableStatement
	 */
	public static void closeCallableStatement(
			CallableStatement callableStatement) {
		try {
			if (callableStatement != null) {
				callableStatement.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}

