package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssm.bean.User;
import com.ssm.dao.UserMapper;
import com.ssm.service.TXService;

@Service
public class TestTXService implements TXService {
	static final Logger LOGGER = LoggerFactory.getLogger(TestTXService.class);
	@Resource
	private UserMapper userMapper;
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	public void tx() {
		User record = new User();
		record.setId("tx01");
		record.setUsername("tx01_name");
		int inserted = userMapper.insert(record);
		LOGGER.info("insert:" + inserted);
		throw new RuntimeException(" insert error!");
	}

	public void insertBatch(List<?> members, String sqlId) {
		int result = 1;
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			int batchCount = 200; // 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			int len = members.size();
			for (int index = 0; index < len;) {
				if (batchLastIndex >= len) {
					batchLastIndex = len;
					result = result * session.insert(sqlId , members.subList(index, batchLastIndex));
					session.commit();
					LOGGER.info("index:" + index + " batchLastIndex:" + batchLastIndex);
					break;// 数据插入完毕，退出循环
				} else {
					result = result * session.insert(sqlId, members.subList(index, batchLastIndex));
					session.commit();
					LOGGER.info("index:" + index + " batchLastIndex:" + batchLastIndex);
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
			}
			session.commit();
		}catch(Exception e){
			LOGGER.error("insert batch fail ..." + e.getMessage(),e);
			throw new RuntimeException("insert batch fail ..." + e.getMessage());
		} finally {
			session.close();
		}
	}
}
