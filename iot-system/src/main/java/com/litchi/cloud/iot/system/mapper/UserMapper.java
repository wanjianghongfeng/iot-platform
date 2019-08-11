package com.litchi.cloud.iot.system.mapper;

import com.litchi.cloud.iot.system.domain.User;
import com.litchi.cloud.iot.system.vo.UserVO;

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
public interface UserMapper extends BaseMapper<User> {

	/** 
	 * 分页获取用户列表
	 * @param page
	 * @param param
	 * @return 
	 * 返回类型  IPage<User> 
	 */
	IPage<User> getPageList(Page<UserVO> page, @Param("param") Map<String, Object> param);

}
