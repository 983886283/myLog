package me.jianpeng.common.linshi;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;

import me.jianpeng.common.Common;

public class articleAll {

	@SuppressWarnings("deprecation")
	public static void htmlStatic(String baseUrl) throws SQLException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = getConn();
		PreparedStatement prepareStatement = conn.prepareStatement
				("select a.id,a.title,a.body,c.username,a.createtime,b.typeval "
						+ "from logcontent a,logtype b,loguser c "
						+ "where a.typeid = b.id and a.userid = c.id");
		ResultSet rs = prepareStatement.executeQuery();
		
		while(rs.next()){
			File read = new File(baseUrl+"/demo.html");
			String readFileToString = FileUtils.readFileToString(read)
					.replace("{title}", rs.getString("title"))
					.replace("{title}", rs.getString("title"))
					.replace("{body}", rs.getString("body"))
					.replace("{username}", rs.getString("username"))
					.replace("{createtime}", sdf.format(rs.getDate("createtime")))
					.replace("{typeval}", rs.getString("typeval"))
					;
			FileUtils.writeStringToFile(Common.writeFile(baseUrl, rs.getString("id")), readFileToString);
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
