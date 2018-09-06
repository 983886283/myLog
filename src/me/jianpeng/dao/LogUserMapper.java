package me.jianpeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.jianpeng.entity.LogUser;

public interface LogUserMapper {
	
	int insert(LogUser logUser);

	int deleteByPrimaryKey(String userid);

	int updateByPrimaryKey(LogUser logUser);

    List<LogUser> selectAll(@Param("offset") int offset, @Param("limit") int limit);
    
    int selectCount();
    
    LogUser selectByName(@Param("username") String username);
}