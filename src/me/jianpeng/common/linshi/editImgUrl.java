package me.jianpeng.common.linshi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editImgUrl {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = getConn();
		PreparedStatement prepareStatement = conn.prepareStatement("select id,body from logcontent");
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next()) {
			String id = rs.getString("id");//src="http://localhost:8080/myLog/images/mysql/20170801185146233.png"
			String body = rs.getString("body").replace("myLog/images", "myLog/images/log");
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
	    String url = "jdbc:mysql://localhost:3306/blogdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	    String username = "root";
	    String password = "root";
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
