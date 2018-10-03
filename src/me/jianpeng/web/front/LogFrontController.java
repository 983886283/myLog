package me.jianpeng.web.front;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.jianpeng.entity.LogContent;
import me.jianpeng.service.LogFrontService;

@Controller
@RequestMapping("/front") // url:/模块/资源/{id}/细分 /seckill/list
public class LogFrontController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogFrontService logFrontService;

	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	private @ResponseBody List<LogContent> getData(Model model) {
		List<LogContent> list = logFrontService.getData(-1);
		return list;
	}
	
	@RequestMapping(value = "/addClick/{contentid}", method = RequestMethod.GET)
	private @ResponseBody void addClick(@PathVariable("contentid") String contentid, Model model) {
		logFrontService.addClick(contentid);
	}
	
}
