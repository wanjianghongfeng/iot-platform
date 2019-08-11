package com.litchi.cloud.iot.system.mapper;

import com.litchi.cloud.iot.system.domain.Role;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
public interface RoleMapper extends BaseMapper<Role> {

	/** 
	 * 获取分页列表
	 * @param page 分页信息
	 * @param param 查询条件参数
	 * @return 
	 * 返回类型  IPage<Role> 
	 */
	IPage<Role> getPageList(Page<Role> page, @Param("param") Map<String, Object> param);

}
