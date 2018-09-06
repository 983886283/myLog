package me.jianpeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.jianpeng.entity.LogType;

public interface LogTypeMapper {
	
	int insert(LogType logType);

	int deleteByPrimaryKey(String typerid);

	int updateByPrimaryKey(LogType logType);

    List<LogType> selectAll(@Param("offset") int offset, @Param("limit") int limit);
    
    int selectCount();
}