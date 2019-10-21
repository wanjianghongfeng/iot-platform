package com.litchi.cloud.iot.system.service;

import com.litchi.cloud.iot.system.domain.Role;
import com.litchi.cloud.iot.system.vo.RoleVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
public interface IRoleService extends IService<Role> {

	/** 
	 * 新增角色
	 * @param roleVO
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> save(RoleVO roleVO);

	/** 
	 * 修改编辑角色
	 * @param roleVO
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> update(RoleVO roleVO);

	/** 
	 * 删除角色
	 * @param id
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> delete(Integer id);

	/** 
	 * 根据id获取角色
	 * @param id
	 * @return 
	 * 返回类型  Result<RoleVO> 
	 */
	Result<RoleVO> getRoleById(Integer id);

	/** 
	 * 分页获取角色列表
	 * @param search
	 * @return 
	 * 返回类型  PageResult<RoleVO> 
	 */
	PageResult<RoleVO> getPageList(MyPage search);

	/** 
	 * 授权
	 * @param roleId
	 * @param resourceIdSet 
	 * 返回类型  void 
	 */
	void grant(Integer roleId, Set<Integer> resourceIdSet);

	/** 
	 * 获取角色资源
	 * @param roleId
	 * @return 
	 * 返回类型  List<Integer> 
	 */
	List<Integer> getRoleResources(Integer roleId);

}
