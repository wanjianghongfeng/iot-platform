package com.litchi.cloud.iot.system.service.impl;

import com.litchi.cloud.iot.system.domain.Role;
import com.litchi.cloud.iot.system.mapper.RoleMapper;
import com.litchi.cloud.iot.system.service.IRoleService;
import com.litchi.cloud.iot.system.vo.RoleVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
	@Resource
	private RoleMapper roleMapper;

	@Override
	public Result<String> save(RoleVO roleVO) {
		Role role = vO2Domain(roleVO);
		if(exist(role)) {
			return Result.error("该角色已存在");
		}
		roleMapper.insert(role);
		return Result.ok("新增成功");
	}

	@Override
	public Result<String> update(RoleVO roleVO) {
		Role role = vO2Domain(roleVO);
		if(exist(role)) {
			return Result.error("该角色已存在");
		}
		roleMapper.updateById(role);
		return Result.ok("更新成功");
	}

	@Override
	public Result<String> delete(Integer id) {
		roleMapper.deleteById(id);
		return Result.ok("删除成功");
	}

	@Override
	public Result<RoleVO> getRoleById(Integer id) {
		RoleVO roleVO = domain2VO(roleMapper.selectById(id));
		return Result.ok(roleVO);
	}

	@Override
	public PageResult<RoleVO> getPageList(MyPage search) {
		Page<Role> page = new Page<>(search.getPageNo(), search.getPageSize());
		IPage<Role> result = roleMapper.getPageList(page, search.getParam());
		List<RoleVO> roleLst = new ArrayList<>();
		for(Role r : result.getRecords()) {
			roleLst.add(domain2VO(r));
		}
		return new PageResult<>(roleLst, result.getTotal());
	}
	
	private boolean exist(Role role) {
		QueryWrapper<Role> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Role::getName, role.getName());
		if(!StringUtils.isEmpty(role.getName())) {
			wrapper.lambda().eq(false, Role::getId, role.getId());
		}
		int count = roleMapper.selectCount(wrapper);
		if(count>0) {
			return true;
		}else return false;
	}

	private Role vO2Domain(RoleVO roleVO) {
		Role role = new Role();
		role.setId(roleVO.getId());
		role.setName(roleVO.getName());
		role.setRemark(roleVO.getRemark());
		role.setSeq(roleVO.getSeq());
		role.setPid(roleVO.getPid());
		return role;
	}

	private RoleVO domain2VO(Role role) {
		RoleVO roleVO = new RoleVO();
		roleVO.setId(role.getId());
		roleVO.setName(role.getName());
		roleVO.setRemark(role.getRemark());
		roleVO.setSeq(role.getSeq());
		roleVO.setPid(role.getPid());
		return roleVO;
	}
}
