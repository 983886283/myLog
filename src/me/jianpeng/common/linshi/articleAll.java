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

public class articleAll {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = getConn();
		PreparedStatement prepareStatement = conn.prepareStatement
				("select a.id,a.title,a.body,c.username,a.createtime,b.typeval "
						+ "from logcontent a,logtype b,loguser c "
						+ "where a.typeid = b.id and a.userid = c.id and a.id = '23db65a7e84f4cf7b7d38ce564f0ccf6'");
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next()){
			try {
				File read = new File("E:/apache-tomcat-9.0.0.M21/webapps/myLog/detail/demo.html");
				String readFileToString = FileUtils.readFileToString(read)
						.replace("{title}", rs.getString("title"))
						.replace("{title}", rs.getString("title"))
						.replace("{body}", rs.getString("body"))
						.replace("{username}", rs.getString("username"))
						.replace("{createtime}", sdf.format(rs.getDate("createtime")))
						.replace("{typeval}", rs.getString("typeval"))
						;
								
				File write = new File("E:/myLog/WebContent/detail/"+rs.getString("id")+".html");
				if(write.exists()) {
					write.delete();
				}
				FileUtils.writeStringToFile(write, readFileToString);
				File writeLinShi = new File("E:/myLog/WebContent/detail/"+rs.getString("id")+".html");
				if(writeLinShi.exists()) {
					writeLinShi.delete();
				}
				FileUtils.writeStringToFile(writeLinShi, readFileToString);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
