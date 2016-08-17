package com.ssm.timer;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ssm.bean.User;
import com.ssm.dao.UserMapper;
import com.ssm.service.TXService;

@Component
public class TestTimer {
	static final Logger LOGGER = LoggerFactory.getLogger(TestTimer.class);
	@Resource
	private UserMapper userMapper;
	@Resource
	private TXService txService;
	private int n=0;
	
	@Scheduled(cron="0/5 * * * * ?")
	public void run() {
		n++;
		// test transaction
		if(n==1) txService.tx();
		// test select
		LOGGER.info("run ...");
		List<User> users = userMapper.find("lkz");
		LOGGER.info(JSON.toJSONString(users));
		LOGGER.info("count ... "+userMapper.selectCount(new User()));
	}
}