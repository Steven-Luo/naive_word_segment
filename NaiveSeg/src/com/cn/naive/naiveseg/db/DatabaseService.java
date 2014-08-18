package com.cn.naive.naiveseg.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 提供访问数据库最基本的操作
 */
public class DatabaseService {
	private static final String URL;
	private static final String USER_NAME;
	private static final String PASSWORD;

	static {
		Properties prop = new Properties();
		try {
			prop.load(DatabaseService.class.getClassLoader()
					.getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = prop.getProperty("url");
		USER_NAME = prop.getProperty("user");
		PASSWORD = prop.getProperty("password");
	}

	private static Connection conn;

	public static Connection getConnection() {
		return conn;
	}

	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean query(String table, String column, String value) {
		boolean result = false;
		String sql = MessageFormat.format(
				"select * from {0} where {1}=\"{2}\"", table, column, value);

		try {
			Statement stat = DatabaseService.getConnection().createStatement();
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				result = true;
				break;
			}
			resultSet.close();
			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void close() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
	}
}
