package me.jianpeng.common.linshi;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class editImgUrl {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = getConn();
		PreparedStatement prepareStatement = conn.prepareStatement("select id,body from logcontent");
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next()) {
			System.out.println(1);
			String id = rs.getString("id");
			String body = rs.getString("body").replace("jianpeng.me", "localhost:8080/myLog");
			PreparedStatement prepareStatement2 = conn.prepareStatement("update logcontent set body = ? where id = ?");
			prepareStatement2.setString(1, body);
			prepareStatement2.setString(2, id);
			prepareStatement2.executeUpdate();
		}
		if(rs!=null) {
			rs.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}
	
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/blogdb?useUnicode=true&characterEncoding=utf8";
	    String username = "root";
	    String password = "123456";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
}
