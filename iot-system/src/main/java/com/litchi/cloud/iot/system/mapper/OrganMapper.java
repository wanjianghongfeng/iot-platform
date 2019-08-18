package com.litchi.cloud.iot.system.mapper;

import com.litchi.cloud.iot.system.domain.Organ;
import com.litchi.cloud.iot.system.vo.OrganVO;

import java.util.Map;

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
public interface OrganMapper extends BaseMapper<Organ> {

	/** 
	 * 获取分页列表
	 * @param page
	 * @param param
	 * @return 
	 * 返回类型  IPage<Organ> 
	 */
	IPage<Organ> getPageList(Page<OrganVO> page, Map<String, Object> param);

}
