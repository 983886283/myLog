package me.jianpeng.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.jianpeng.dao.LogContentMapper;
import me.jianpeng.entity.LogContent;
import me.jianpeng.service.LogFrontService;

@Service
public class LogFrontServiceImpl implements LogFrontService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private LogContentMapper logContentMapper;

	@Override
	@Transactional
	public int insert(LogContent logContent) {
		int insert = logContentMapper.insert(logContent);
		return insert;
	}

	@Override
	@Transactional
	public int deleteByPrimaryKey(String contentid) {
		int delete = logContentMapper.deleteByPrimaryKey(contentid);
		return delete;
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(LogContent logContent) {
		int update = logContentMapper.updateByPrimaryKey(logContent);
		return update;
	}

	@Override
	public List<LogContent> getData(int pageno) {
		int offset = (pageno-1)*8;
		int limit = 8;
		if(pageno==-1) {
			offset = -1;
		}
		List<LogContent> allContent = logContentMapper.selectAll(offset, limit);
		return allContent;
	}

	@Override
	public int selectCount() {
		int count = logContentMapper.selectCount();
		return count;
	}

	@Override
	public int addClick(String contentid) {
		int addClick = logContentMapper.addClick(contentid);
		return addClick;
	}
}
