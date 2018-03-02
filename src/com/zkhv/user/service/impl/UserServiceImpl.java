package com.zkhv.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zkhv.common.BaseServiceImpl;
import com.zkhv.user.entity.User;
import com.zkhv.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	
	
	//根据用户名和密码查询
	public User findUserByUaccountAndPwd(User user) {
		return userMapper.findUserByUaccountAndPwd(user);
	}

	
	public int deleteByPrimaryKey(String uid) {
	
		return userMapper.deleteByPrimaryKey(uid);
	}


	public User selectByPrimaryKey(String uid) {
	
		return userMapper.selectByPrimaryKey(uid);
	}

	
	public int updateByPrimaryKey(User user) {
	
		return userMapper.updateByPrimaryKey(user);
	}

	
	public int checkUserByAccount(User user) {
	
		return userMapper.checkUserByAccount(user);
	}


	public List<User> findAll() {
		return userMapper.findAll();
	}

	
	
	
}
