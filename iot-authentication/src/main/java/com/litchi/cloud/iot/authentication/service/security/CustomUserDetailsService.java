package com.litchi.cloud.iot.authentication.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.litchi.cloud.iot.authentication.domain.Organ;
import com.litchi.cloud.iot.authentication.domain.User;
import com.litchi.cloud.iot.authentication.mapper.OrganMapper;
import com.litchi.cloud.iot.authentication.mapper.UserMapper;
import com.litchi.iot.common.beans.LoginUser;
import com.litchi.iot.common.result.Result;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrganMapper organMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.selectByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		Organ organ = organMapper.selectById(user.getOrganId());
		if (organ != null) {
			user.setOrganCode(organ.getOrganCode());
			user.setOrganPath(organ.getPath());
		}
		return user;
	}

	public Result<LoginUser> getUserByLoginName(String username, Integer userType) {
		LoginUser user = getUserByLoginName(username);
		if (user.getId() == null) {
			return Result.error("用户名或密码错误");
		}
		/*if (user.getUserType() != userType) {
			return Result.error("用户登录类型不匹配无法登录");
		}*/
		// web端验证权限
		if (StringUtils.isEmpty(user.getPurviews())) {
			return Result.error("用户未分配任何权限无法登录");
		}
		user.setPassword(null);// 返回密码处理
		return Result.ok(user);
	}

	private LoginUser getUserByLoginName(String username) {
		User user = userMapper.selectByUsername(username);
		LoginUser loginUser = new LoginUser();
		loginUser.setId(user.getId());
		loginUser.setLoginName(user.getUsername());
		loginUser.setName(user.getName());
		loginUser.setPassword(user.getPassword());
		loginUser.setPurviews(user.getPurviews());
		loginUser.setOrganId(user.getOrganId());
		loginUser.setOrganCode(user.getOrganCode());
		loginUser.setOrganPath(user.getOrganPath());
		loginUser.setPurviews(user.getPurviews());
		return loginUser;
	}
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}