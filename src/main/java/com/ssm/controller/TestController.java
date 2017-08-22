package com.ssm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.Rest;
import com.ssm.bean.User;
import com.ssm.dao.UserMapper;

@Controller
@RequestMapping("/")
public class TestController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UserMapper userMapper;
	
//	@RequestMapping(value="/get",method=RequestMethod.GET)
//	public void get(User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
//		System.out.println(JSONObject.toJSONString(user));
//		List<Map<String, Object>> list = userMapper.findAll();
//		response.getWriter().write(JSONObject.toJSONString(list));
//	}
	
	@ResponseBody
	@RequestMapping(value="/find/{name}",method=RequestMethod.GET)
	public Rest<?> find(@PathVariable String name,HttpServletRequest request,HttpServletResponse response) throws IOException{
		PageHelper.startPage(2, 5).setOrderBy("id desc");
		List<User> users = userMapper.find(name);
		PageInfo<User> page = new PageInfo<User>(users);
		return Rest.ok(page);
	}
	
	@ResponseBody
	@RequestMapping("login")
	public Rest<?> login(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request,HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("now", new Date());
		map.put("_now", System.currentTimeMillis());
		map.put("code", 0);
		map.put("username", username);
		map.put("password", password);
		map.put("msg", "字符");
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        String msg;
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                request.getSession().setAttribute("user",map);
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                // 获取保存的URL
                if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                	logger.info("admin/home");
                } else {
                	logger.info("forward:{}", savedRequest.getRequestUrl());
                }
            } else {
            	logger.info("login");
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            logger.info(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            logger.info(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            logger.info(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            logger.info(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            logger.info(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            logger.info(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            logger.info(msg);
        }
		return Rest.ok(map);
	}
	
	@RequiresPermissions({"user:view"})
	@ResponseBody
	@RequestMapping("admin/index")
	public Rest<?> index(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("Name", "rest");
		return Rest.ok(data);
	}
	
	@RequiresPermissions({"user:create"})
	@ResponseBody
	@RequestMapping("admin/create")
	public Rest<?> create(){
		return Rest.ok("create action");
	}
	
	@ResponseBody
	@RequestMapping("unauthorized")
	public Rest<?> unauthorized(){
		return Rest.forbidden();
	}
}
