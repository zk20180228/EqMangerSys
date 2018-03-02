package com.zkhv.user.dao;

import java.util.List;

import com.zkhv.common.BaseMapper;
import com.zkhv.user.entity.User;


public interface UserMapper extends BaseMapper<User> {

    int deleteByPrimaryKey(String uid);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKey(User user);
    
    //根据用户名和密码查询
	User findUserByUaccountAndPwd(User user);
	
	// 根据账户名查询是否存在
	int checkUserByAccount(User user);
	//查询全部
	List<User> findAll();
	
	
}