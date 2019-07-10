package com.etc.blog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	// 查询（query） 增删改（update）
	// doQuery
	// doUpdate
	// getConn
	// doClose
	// doInsertGetPK

	public static final String CLASSPATH = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/blog";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
// 获得连接对象
	public  Connection getConn() {
		Connection conn = null;
		try {
			// 加载驱动
			Class.forName(CLASSPATH);
			// 创建连接
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

	// Object...args 类似于 数组 但比数组更灵活 实际参数，可以不写，可以任意赋值
	// public static boolean doUpdate(String sql,Object[] args) {
	public  boolean doUpdate(String sql, Object... args) {
		boolean flag = false;
		Connection conn = getConn();
		PreparedStatement psm = null;
		try {
			psm = conn.prepareStatement(sql);
			// 数据 从参数来的]
			if (args != null) {

				for (int i = 0; i < args.length; i++) {

					psm.setObject(i + 1, args[i]);// i+1 he i

				}

			}
			int count = psm.executeUpdate();
			if (count > 0)
				flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (psm != null)
					psm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return flag;

	}

	public  int doInsertGetPK(String sql, Object... args) {
		int key = -1;
		Connection conn = getConn();
		PreparedStatement psm = null;
		try {
			psm = conn.prepareStatement(sql);
			// 数据 从参数来的]
			if (args != null) {

				for (int i = 0; i < args.length; i++) {

					psm.setObject(i + 1, args[i]);// i+1 he i

				}

			}
			psm.executeUpdate();
			ResultSet rs = psm.getGeneratedKeys();
			while (rs.next()) {
				key = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (psm != null)
					psm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return key;

	}

	public  ResultSet doQuery(String sql, Object... args) {
		ResultSet rs = null;
		Connection conn = getConn();
		PreparedStatement psm = null;
		try {
			psm = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					psm.setObject(i + 1, args[i]);
				}
			}
			rs = psm.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;

	}

	public  void doClose(ResultSet rs) {

		try {
			Statement sm = rs.getStatement();
			Connection conn = sm.getConnection();

			if (rs != null)
				rs.close();
			if (sm != null)
				sm.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}// 通过rs.getStatement() 获得生成它的执行语句对象

	}

	
}
