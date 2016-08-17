package com.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ssm.bean.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{
	
	int vinsert(List<User> list);
	
    @Select("select id,username from user")
	List<Map<String, Object>> findAll();

    @Select("select * from user where username=#{name}")
	List<User> find(@Param("name")String name);
}