package me.jianpeng.service;

import java.util.List;

import me.jianpeng.entity.LogContent;

public interface LogFrontService {

	int insert(LogContent logContent);
	
	int deleteByPrimaryKey(String contentid);
	
	int updateByPrimaryKey(LogContent logContent);

    List<LogContent> getData(int pageno);
    
	int addClick(String contentid);
	
    int selectCount();
}
