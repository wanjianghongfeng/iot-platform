package com.litchi.cloud.iot.system.mapper;

import com.litchi.cloud.iot.system.domain.UserRole;

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
public interface UserRoleMapper extends BaseMapper<UserRole> {

	/** 
	 * 批量保存用户角色
	 * @param userId
	 * @param roleIdSet 
	 * 返回类型  void 
	 */
	void batchSaveUserRole(@Param("userId") Integer userId, @Param("roleIdSet") Set<Integer> roleIdSet);

	List<Integer> getUserRoles(Integer userId);

}
