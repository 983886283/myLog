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
	
	@SuppressWarnings("deprecation")
	public static void saveDetailFile(String baseUrl, LogContent content) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			File read = new File(baseUrl+"/demo.html");
			String readFileToString = FileUtils.readFileToString(read)
					.replace("{title}", content.getTitle())
					.replace("{title}", content.getTitle())
					.replace("{body}", content.getBody())
					.replace("{username}", content.getUsername())
					.replace("{createtime}", sdf.format(content.getCreatetime()==null?new Date():content.getCreatetime()))
					.replace("{typeval}", content.getTypeval())
					;
							
			File write = fileExists(baseUrl, content.getContentid());
			FileUtils.writeStringToFile(write, readFileToString);
			File writeLinShi = new File("E:/myLog/WebContent/detail/"+content.getContentid()+".html");
			if(writeLinShi.exists()) {
				writeLinShi.delete();
			}
			FileUtils.writeStringToFile(writeLinShi, readFileToString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File fileExists(String baseUrl, String contentid) {
		File write = new File(baseUrl+"/"+contentid+".html");
		if(write.exists()) {
			write.delete();
		}
		File writeLinShi = new File("E:/myLog/WebContent/detail/"+contentid+".html");
		if(writeLinShi.exists()) {
			writeLinShi.delete();
		}
		return write;
	}
}
