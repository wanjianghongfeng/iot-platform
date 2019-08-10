package com.litchi.cloud.iot.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.litchi.cloud.iot.authentication.domain.User;
import com.litchi.cloud.iot.authentication.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserMapper userMapper;

	@Override
	public void create(User user) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(User::getUsername, user.getUsername());
		User existing = userMapper.selectOne(queryWrapper);
		Assert.isNull(existing, "user already exists: " + user.getUsername());
		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);
		userMapper.updateById(user);
		log.info("new user has been created: {}", user.getUsername());
	}

	@Override
	@Transactional(readOnly = false)
	public void update(User user) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(User::getUsername, user.getUsername());
		User existing = userMapper.selectOne(queryWrapper);
		if (existing != null) {
			existing.setPurviews(user.getPurviews());
		}
	}
	
}
