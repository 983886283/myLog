package me.jianpeng.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import me.jianpeng.entity.LogContent;

public class Common {

	/*
	 * @Description: Md5
	 * @author: 崔建鹏
	 * @date: 2018年9月28日 上午10:14:21
	 * @param userPw
	 * @param userSal
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws UnsupportedEncodingException
	 */
	public static String md5(String userPw, byte[] userSal)
			throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5", "SUN");
		String pwd = "";
		md.reset();
		md.update(userSal);
		md.update(userPw.getBytes("UTF-8"));
		byte[] bys = md.digest();
		pwd = pwd + (char) userSal[0];
		pwd = pwd + (char) userSal[1];
		pwd = pwd + new String(Base64.encodeBase64(bys), "utf-8");
		return pwd;
	}
	/*
	 * @Description: 生成笔记静态化文件
	 * @author: 崔建鹏
	 * @date: 2018年9月28日 上午10:13:29
	 * @param baseUrl
	 * @param content
	 */
	public static void saveDetailFile(String baseUrl, LogContent content) {
		try {
			FileUtils.writeStringToFile(writeFile(baseUrl, content.getContentid()), readFileSetData(baseUrl, content), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @Description: 对类型为Java的代码块进行批量替换
	 * @author: 崔建鹏
	 * @date: 2018年9月28日 上午10:25:41
	 * @param body
	 * @return
	 */
	public static void replaceCodeSnippet(LogContent logcontent) {
		//java
		if("JAVA".equalsIgnoreCase(logcontent.getTypeval())) {
			logcontent.setBody(logcontent.getBody().replaceAll("<code>", "<code class=\"language-java\">"));
		}
	}

	/*
	 * @Description: 对模板变量注值
	 * @author: 崔建鹏
	 * @date: 2018年9月28日 上午10:11:01
	 * @param baseUrl
	 * @param content
	 * @return
	 * @throws IOException
	 */
	private static String readFileSetData(String baseUrl, LogContent content) throws IOException {
		String readFileToString = FileUtils.readFileToString(new File(baseUrl+"/demo.html"), "utf-8")
				.replace("{title}", content.getTitle())
				.replace("{title}", content.getTitle())
				.replace("{body}", content.getBody())
				.replace("{username}", content.getUsername())
				.replace("{createtime}", new SimpleDateFormat("yyyy-MM-dd").format(content.getCreatetime()==null?new Date():content.getCreatetime()))
				.replace("{typeval}", content.getTypeval())
				;
		return readFileToString;
	}
	
	/*
	 * @Description: 输出文件不存在则新建，已存在则删除再新建
	 * @author: 崔建鹏
	 * @date: 2018年9月28日 上午10:12:07
	 * @param baseUrl
	 * @param contentid
	 * @return
	 */
	public static File writeFile(String baseUrl, String contentid) {
		File write = new File(baseUrl+"/"+contentid+".html");
		if(write.exists()) {
			write.delete();
		}
		return write;
	}
}
