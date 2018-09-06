package me.jianpeng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import me.jianpeng.entity.LogContent;

public interface LogContentMapper {
	
	int insert(LogContent logContent);

	int deleteByPrimaryKey(@Param("contentid") String contentid);

	int updateByPrimaryKey(LogContent logContent);
	
	int addClick(@Param("contentid") String contentid);

    List<LogContent> selectAll(@Param("offset") int offset, @Param("limit") int limit);
    
    LogContent selectOne(@Param("contentid") String contentid);
    
    int selectCount();
    
    List<Map<Object,Object>> selectCountByTypevalLimit5();

    List<Map<Object,Object>> selectCountByDate();
}