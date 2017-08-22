package com.ssm.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
//	private int n=0;
	private static List<User> members = new ArrayList<User>();
	
	static {
		for(int i=0;i<5000;i++){
			User u = new User();
			u.setId(UUID.randomUUID().toString().replace("-", ""));
			u.setUsername("lkz");
			members.add(u);
		}
	}
	
	@Scheduled(cron="0/5 * * * * ?")
	public void run() {
//		n++;
//		LOGGER.info("run ...");
		// test transaction
//		if(n==1) txService.tx();
		
		// test select
//		List<User> users = userMapper.find("lkz");
//		LOGGER.info(JSON.toJSONString(users));
//		LOGGER.info("count ... "+userMapper.selectCount(new User()));
		
		//test insert batch
//		if(n==3) txService.insertBatch(members, "com.ssm.dao.UserMapper.insertBatch");
	}
}