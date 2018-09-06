package me.jianpeng.service;

import java.util.List;
import java.util.Map;

import me.jianpeng.entity.LogContent;
import me.jianpeng.entity.LogType;
import me.jianpeng.entity.LogUser;

public interface LogBackService {

	LogUser login(String username, String password);
	
	Map<Object,Object> getData(int pageno);

	Map<String, List<Object>> getDataForCharts();

	int delData(String id);

	int saveData(LogContent logcontent);

	Map<Object, Object> getType(int pageno);

	int delType(String contentid);

	int saveType(LogType logtype);

	Map<Object, Object> getUser(int pageno);

	int delUser(String id);

	int saveUser(LogUser user);
	
	LogContent getOneData(String contentid);
}
