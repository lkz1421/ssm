package com.ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.User;
import com.ssm.dao.UserMapper;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource
	private UserMapper userMapper;
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public void get(User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println(JSONObject.toJSONString(user));
		List<Map<String, Object>> list = userMapper.findAll();
		response.getWriter().write(JSONObject.toJSONString(list));
	}
	
	@RequestMapping(value="/find/{name}",method=RequestMethod.GET)
	public void find(@PathVariable String name,HttpServletRequest request,HttpServletResponse response) throws IOException{
		PageHelper.startPage(2, 5).setOrderBy("id desc");
		List<User> users = userMapper.find(name);
		PageInfo<User> page = new PageInfo<User>(users);
		response.getWriter().write(JSONObject.toJSONString(page));
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setId("id0001");
		List<Object> list = new ArrayList<Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", new Date());
		map.put("user", user);
		JSONObject obj = JSON.parseObject(JSON.toJSONString(map));
		list.add(obj);
		System.out.println(obj instanceof Map);
		SerializeConfig config = new SerializeConfig();
		config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
		System.err.println(JSON.toJSONString(list,config));
	}
}
