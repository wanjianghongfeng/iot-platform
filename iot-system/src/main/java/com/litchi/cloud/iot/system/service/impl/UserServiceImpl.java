package com.litchi.cloud.iot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.cloud.iot.system.domain.RoleResource;
import com.litchi.cloud.iot.system.domain.User;
import com.litchi.cloud.iot.system.domain.UserRole;
import com.litchi.cloud.iot.system.mapper.UserMapper;
import com.litchi.cloud.iot.system.mapper.UserRoleMapper;
import com.litchi.cloud.iot.system.service.IUserService;
import com.litchi.cloud.iot.system.vo.UserVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Resource
	private UserRoleMapper userRoleMapper;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    /** 
     * @Fields defaultPassword : 默认密码
     */ 
    @Value("${lmp.default-password}")
    private String defaultPassword;

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

    @Override
    public void grant(Integer userId, Set<Integer> roleIdSet) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            // 先删除
        	QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        	wrapper.lambda().eq(UserRole::getUserId, userId);
            userRoleMapper.delete(wrapper);
            // 批量保存
            userRoleMapper.batchSaveUserRole(userId, roleIdSet);
        }
    }

	@Override
	public List<Integer> getUserRoles(Integer userId) {
        return userRoleMapper.getUserRoles(userId);
	}

	@Override
    public Result<String> updatePwd(Integer userId, String oldPwd, String newPwd) {
        User oldUser = userMapper.selectById(userId);
        if (oldUser == null) {
            return Result.error("账号不存在");
        }
        // 这里进行base64解密
        // Base64
        // String decoderOldPwd = Base64Util.decoder(oldPwd);
        // String newPwdNecoder = Base64Util.decoder(newPwd);
        if (!encoder.matches(oldPwd, oldUser.getPassword())) {
            return Result.error("原密码不正确");
        }
        oldUser.setPassword(encoder.encode(newPwd));
        // 修改密码
        userMapper.updateById(oldUser);
        return Result.ok();
    }

    @Override
    public Result<String> resetPwd(Integer userId) {
        User oldUser = userMapper.selectById(userId);
        if (oldUser == null) {
            return Result.error("账号不存在");
        }
        oldUser.setPassword(encoder.encode(defaultPassword));
        // 修改密码
        userMapper.updateById(oldUser);
        return Result.ok();
    }
}
