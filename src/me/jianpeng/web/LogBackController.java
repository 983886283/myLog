package me.jianpeng.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import me.jianpeng.common.Common;
import me.jianpeng.common.linshi.articleAll;
import me.jianpeng.entity.LogContent;
import me.jianpeng.entity.LogType;
import me.jianpeng.entity.LogUser;
import me.jianpeng.service.LogBackService;

@Controller
@RequestMapping("/back")
public class LogBackController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogBackService logBackService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	private @ResponseBody String login(String username, String password, HttpSession session , Model model) {
		LogUser loginuser = logBackService.login(username, password);
		if(loginuser!=null) {
			session.setAttribute("username", loginuser.getUsername());
			session.setAttribute("userid", loginuser.getUserid());
			return "1";
		}
		return "0";
	}
	
	@RequestMapping(value = "/getData/{pageno}", method = RequestMethod.GET)
	private @ResponseBody Map<Object,Object> getData(@PathVariable("pageno") int pageno, Model model) {
		Map<Object,Object> map = logBackService.getData(pageno);
		return map;
	}

	@RequestMapping(value = "/getDataForCharts", method = RequestMethod.GET)
	private @ResponseBody Map<String, List<Object>> getDataForCharts(Model model) {
		Map<String, List<Object>> map = logBackService.getDataForCharts();
		return map;
	}
	
	@RequestMapping(value = "/delData/{id}", method = RequestMethod.GET)
	private @ResponseBody JSONObject delData(@PathVariable("id") String id, HttpSession session, Model model) {
		int result = logBackService.delData(id);
		if(result==1) {
			Common.writeFile(session.getServletContext().getRealPath("detail"), id);
		}
		JSONObject obj = new JSONObject();
		obj.put("data", result == 1 ? "success" : "失败");
		return obj;
	}
	
	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	private @ResponseBody JSONObject saveData(LogContent content, HttpSession session, Model model) {
		content.setUserid((String)session.getAttribute("userid"));
		int result = logBackService.saveData(content);
		if(result>0) {
			content = logBackService.getOneData(content.getContentid());
			Common.saveDetailFile(session.getServletContext().getRealPath("detail"), content);
		}
		JSONObject obj = new JSONObject();
		obj.put("data", result > 0 ? "success" : "失败");
		return obj;
	}
	
	@RequestMapping(value = "/getType/{pageno}", method = RequestMethod.GET)
	private @ResponseBody Map<Object,Object> getType(@PathVariable("pageno") int pageno, Model model) {
		Map<Object,Object> map = logBackService.getType(pageno);
		return map;
	}
	
	@RequestMapping(value = "/delType/{id}", method = RequestMethod.GET)
	private @ResponseBody JSONObject delType(@PathVariable("id") String id, Model model) {
		int result = logBackService.delType(id);
		JSONObject obj = new JSONObject();
		obj.put("data", result == 1 ? "success" : "失败");
		return obj;
	}
	
	@RequestMapping(value = "/saveType", method = RequestMethod.POST)
	private @ResponseBody JSONObject saveType(LogType type, HttpSession session, Model model) {
		int result = logBackService.saveType(type);
		if(result == 1) {
			
		}
		JSONObject obj = new JSONObject();
		obj.put("data", result == 1 ? "success" : "失败");
		return obj;
	}
	
	@RequestMapping(value = "/getUser/{pageno}", method = RequestMethod.GET)
	private @ResponseBody Map<Object,Object> getUser(@PathVariable("pageno") int pageno, Model model) {
		Map<Object,Object> map = logBackService.getUser(pageno);
		return map;
	}
	
	@RequestMapping(value = "/delUser/{id}", method = RequestMethod.GET)
	private @ResponseBody JSONObject delUser(@PathVariable("id") String id, Model model) {
		int result = logBackService.delUser(id);
		JSONObject obj = new JSONObject();
		obj.put("data", result == 1 ? "success" : "失败");
		return obj;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	private @ResponseBody JSONObject saveUser(LogUser user, HttpSession session, Model model) {
		int result = logBackService.saveUser(user);
		JSONObject obj = new JSONObject();
		obj.put("data", result == 1 ? "success" : "失败");
		return obj;
	}
	
	@RequestMapping(value = "/htmlStatic", method = RequestMethod.GET)
	private @ResponseBody JSONObject htmlStatic(HttpSession session, Model model) {
		JSONObject obj = new JSONObject();
		try {
			articleAll.htmlStatic(session.getServletContext().getRealPath("detail"));
			obj.put("data","success");
		} catch (SQLException | IOException e) {
			obj.put("data","失败");
		}
		return obj;
	}
}
