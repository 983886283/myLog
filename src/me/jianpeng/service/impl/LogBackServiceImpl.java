package me.jianpeng.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.jianpeng.common.Common;
import me.jianpeng.dao.LogContentMapper;
import me.jianpeng.dao.LogTypeMapper;
import me.jianpeng.dao.LogUserMapper;
import me.jianpeng.entity.LogContent;
import me.jianpeng.entity.LogType;
import me.jianpeng.entity.LogUser;
import me.jianpeng.service.LogBackService;

@Service
public class LogBackServiceImpl implements LogBackService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private LogUserMapper logUserMapper;
	
	@Autowired
	private LogContentMapper logContentMapper;
	
	@Autowired
	private LogTypeMapper logTypeMapper;

	@Override
	public LogUser login(String username, String password) {
		LogUser user = logUserMapper.selectByName(username);
		String usersalt = user.getUsersalt();
		String md5 = null;
		try {
			md5 = Common.md5(password, usersalt.getBytes("utf-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String userpassword = user.getUserpassword();
		if(StringUtils.isNotBlank(md5)&&userpassword.equals(md5)) {
			return user;
		}
		return null;
	}

	@Override
	public Map<Object,Object> getData(int pageno) {
		int offset = (pageno-1)*8;
		int limit = 8;
		if(pageno==-1) {
			offset = -1;
		}
		List<LogContent> allContent = logContentMapper.selectAll(offset, limit);
		
		List<LogType> allType = logTypeMapper.selectAll(-1, 8);
		
		int contentCount = logContentMapper.selectCount();
		
		Map<Object,Object> map = new HashMap<>();
		map.put("allData", allContent);
		map.put("allTypes", allType);
		Map<String,Integer> countmap = new HashMap<>();
		countmap.put("TOTAL", contentCount);
		map.put("PageTotal", countmap);
		return map;
	}

	@Override
	public Map<String, List<Object>> getDataForCharts() {
		Map<String,List<Object>> obj = new HashMap<>();
		List<Map<Object, Object>> countByTypevalLimit5 = logContentMapper.selectCountByTypevalLimit5();
		List<Object> typeValArray = new ArrayList<>();
		List<Object> totalArray = new ArrayList<>();
		List<Object> clickSumArray = new ArrayList<>();
		for (int i = 0; i < countByTypevalLimit5.size(); i++) {
			Map<Object, Object> map = countByTypevalLimit5.get(i);
			typeValArray.add(map.get("typeval"));
			totalArray.add(map.get("total"));
			clickSumArray.add(map.get("clicksum"));
		}
		obj.put("typeVal", typeValArray);
	    obj.put("total", totalArray);
	    obj.put("clickSum", clickSumArray);
	    List<Map<Object, Object>> countByDate = logContentMapper.selectCountByDate();
	    List<Object> dateAllArray = new ArrayList<>();
		List<Object> totalAllArray = new ArrayList<>();
		for (int i = 0; i < countByDate.size(); i++) {
			Map<Object, Object> map = countByDate.get(i);
			dateAllArray.add(map.get("date"));
			totalAllArray.add(map.get("total"));
		}
		obj.put("dateAll", dateAllArray);
		obj.put("dateCountAll", totalAllArray);
		return obj;
	}

	@Override
	public int delData(String id) {
		int rs = logContentMapper.deleteByPrimaryKey(id);
		return rs;
	}
	
	@Override
	public int saveData(LogContent logContent) {
		int rs = 0;
		if(StringUtils.isBlank(logContent.getContentid())) {
			logContent.setContentid(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		Common.replaceCodeSnippet(logContent);
		rs = logContentMapper.insert(logContent);
		return rs;
	}

	@Override
	public Map<Object, Object> getType(int pageno) {
		int offset = (pageno-1)*8;
		int limit = 8;
		if(pageno==-1) {
			offset = -1;
		}
		List<LogType> allType = logTypeMapper.selectAll(offset, limit);
		
		int typeCount = logTypeMapper.selectCount();
		
		Map<Object,Object> map = new HashMap<>();
		map.put("allType", allType);
		Map<String,Integer> countmap = new HashMap<>();
		countmap.put("TOTAL", typeCount);
		map.put("PageTotal", countmap);
		return map;
	}

	@Override
	public int delType(String id) {
		int rs = logTypeMapper.deleteByPrimaryKey(id);
		return rs;
	}

	@Override
	public int saveType(LogType logtype) {
		int rs = 0;
		if(StringUtils.isNotBlank(logtype.getTypeid())) {
			rs = logTypeMapper.updateByPrimaryKey(logtype);
		}else {
			logtype.setTypeid(UUID.randomUUID().toString().replaceAll("-", ""));
			rs = logTypeMapper.insert(logtype);
		}
		return rs;
	}
	
	@Override
	public Map<Object, Object> getUser(int pageno) {
		int offset = (pageno-1)*8;
		int limit = 8;
		if(pageno==-1) {
			offset = -1;
		}
		List<LogUser> allUser = logUserMapper.selectAll(offset, limit);
		
		int userCount = logUserMapper.selectCount();
		
		Map<Object,Object> map = new HashMap<>();
		map.put("allUser", allUser);
		Map<String,Integer> countmap = new HashMap<>();
		countmap.put("TOTAL", userCount);
		map.put("PageTotal", countmap);
		return map;
	}
	
	@Override
	public int delUser(String id) {
		int rs = logUserMapper.deleteByPrimaryKey(id);
		return rs;
	}
	
	@Override
	public int saveUser(LogUser loguser) {
		int rs = 0;
		try {
			if(StringUtils.isNotBlank(loguser.getUserid())) {
					String userpassword = loguser.getUserpassword();
					if(StringUtils.isNotBlank(userpassword)) {
						loguser.setUserpassword(Common.md5(userpassword,loguser.getUsersalt().getBytes("utf-8")));
					}
				rs = logUserMapper.updateByPrimaryKey(loguser);
			}else {
				loguser.setUserid(UUID.randomUUID().toString().replaceAll("-", ""));
				loguser.setUsersalt(UUID.randomUUID().toString().replaceAll("-", ""));
				loguser.setUserpassword(Common.md5(loguser.getUserpassword(),loguser.getUsersalt().getBytes("utf-8")));
				rs = logUserMapper.insert(loguser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public LogContent getOneData(String id) {
		LogContent selectOne = logContentMapper.selectOne(id);
		return selectOne;
	}
}
