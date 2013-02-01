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
	 * 获取非事物性连接
	 * 
	 * @return
	 * @throws java.sql.SQLException
	 */

	public Connection getConnection() throws java.sql.SQLException {
		return this.getPoolManConnection();
		//return this.getConn();
	}


	/**
	 * 从poolman中取连接对象
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
	 * 获取事务型数据库连接
	 * 
	 * @return Connection
	 */
	public Connection getTransactionConnection() throws SQLException {
		Connection conn = this.getPoolManConnection(); 
		conn.setAutoCommit(false);
		return conn;
	}

	/**
	 * 关闭事务型数据库连接
	 * 
	 * @return Connection
	 */
	public static void closeTransactionConnection(Connection conn,
			boolean abortTransaction) {
		// 判断数据源是否存在
		if (conn != null) {
			try {
				DatabaseMetaData metaData = conn.getMetaData();
				if (metaData.supportsTransactions()) {
					// 回滚/提交数据
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
	 * 关闭非事务性数据库连接
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
	 * 关闭连接语句
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
	 * 关闭连接语句
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
	 * 关闭查询结果集
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
	 * 关闭所有对象（普通的数据库连接）
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
	 * 关闭所有对象（普通的数据库连接）
	 * 
	 * @return Connection
	 */
	public static void closeResource(Connection conn, PreparedStatement pstmt) {
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}

	/**
	 * 关闭所有对象（带事务的数据库连接）
	 * 
	 * @return Connection
	 */
	public static void closeResource(Connection conn, PreparedStatement pstmt,
			boolean bool) {
		closePreparedStatement(pstmt);
		closeTransactionConnection(conn, bool);
	}

	/**
	 * 关闭JTA控制事务
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
	 * 关闭存储过程语句
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

