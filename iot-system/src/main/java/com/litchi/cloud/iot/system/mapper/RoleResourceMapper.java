package com.litchi.cloud.iot.system.mapper;

import com.litchi.cloud.iot.system.domain.RoleResource;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjhf
 * @since 2019-10-21
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

	/** 
	 * 批量保存角色权限
	 * @param roleId
	 * @param resourceIdSet 
	 * 返回类型  void 
	 */
	void batchSaveRoleResource(@Param("roleId") Integer roleId, @Param("resourceIdSet") Set<Integer> resourceIdSet);

	/** 
	 *获取角色资源
	 * @param id
	 * @return 
	 * 返回类型  List<Integer> 
	 */
	List<Integer> getRoleResources(Integer roleId);

}
