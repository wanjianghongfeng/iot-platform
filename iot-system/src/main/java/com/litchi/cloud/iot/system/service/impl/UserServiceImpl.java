package com.litchi.cloud.iot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.cloud.iot.system.domain.User;
import com.litchi.cloud.iot.system.mapper.UserMapper;
import com.litchi.cloud.iot.system.service.IUserService;
import com.litchi.cloud.iot.system.vo.UserVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/** 
 * 用户操作服务类
 * @author: tievd(wjhf) 
 * @date: 2019年8月10日 下午10:26:14
 * @vesion: 0.0.1
 */ 
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public Result<String> save(UserVO userVO) {
		User user = vO2Domain(userVO);
		user.setCreateTime(new Date());
		if(exist(user)) {
			return Result.error("该用户已存在");
		}
		userMapper.insert(user);
		return Result.ok("新增成功");
	}

	@Override
	public Result<String> update(UserVO userVO) {
		User user = vO2Domain(userVO);
		if(exist(user)) {
			return Result.error("该用户已存在");
		}
		user.setCreateTime(new Date());
		userMapper.updateById(user);
		return Result.ok("更新成功");
	}

	@Override
	public Result<String> delete(Integer id) {
		userMapper.deleteById(id);
		return Result.ok("删除成功");
	}

	@Override
	public Result<UserVO> getUserById(Integer id) {
		User user = userMapper.selectById(id);
		UserVO userVO = domain2VO(user);
		return Result.ok(userVO);
	}

	@Override
	public PageResult<UserVO> getPageList(MyPage search) {
		Page<UserVO> page = new Page<>(search.getPageNo(), search.getPageSize());
		IPage<User> iPage = userMapper.getPageList(page, search.getParam());
		List<UserVO> userLst = new ArrayList<>();
		for(User u : iPage.getRecords()) {
			userLst.add(domain2VO(u));
		}
		return new PageResult<>(userLst, iPage.getTotal());
	}
	
	private boolean exist(User user) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(User::getUsername, user.getUsername());
		if(!StringUtils.isEmpty(user.getId())) {
			wrapper.lambda().ne(User::getId, user.getId());	
		}
		int count = userMapper.selectCount(wrapper);
		if(count > 0) {
			return true;
		}else return false;
	}

	private User vO2Domain(UserVO userVO) {
		User user = new User();
		user.setId(userVO.getId());
		user.setCreateTime(userVO.getCreateTime());
		user.setModifyTime(userVO.getModifyTime());
		user.setName(userVO.getName());
		user.setUsername(userVO.getUsername());
		user.setOrganId(userVO.getOrganId());
		user.setPassword(userVO.getPassword());
		user.setPurviews(userVO.getPassword());
		return user;
	}
	
	private UserVO domain2VO(User userVO) {
		UserVO user = new UserVO();
		user.setId(userVO.getId());
		user.setCreateTime(userVO.getCreateTime());
		user.setModifyTime(userVO.getModifyTime());
		user.setName(userVO.getName());
		user.setUsername(userVO.getUsername());
		user.setOrganId(userVO.getOrganId());
		user.setPassword(userVO.getPassword());
		user.setPurviews(userVO.getPurviews());
		return user;
	}
}
