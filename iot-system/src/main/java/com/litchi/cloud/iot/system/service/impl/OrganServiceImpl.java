package com.litchi.cloud.iot.system.service.impl;

import com.litchi.cloud.iot.system.domain.Organ;
import com.litchi.cloud.iot.system.mapper.OrganMapper;
import com.litchi.cloud.iot.system.service.IOrganService;
import com.litchi.cloud.iot.system.vo.OrganVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
@Service
public class OrganServiceImpl extends ServiceImpl<OrganMapper, Organ> implements IOrganService {

	@Override
	public Result<String> save(OrganVO organVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<String> update(OrganVO organVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<String> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<OrganVO> getOrganById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<OrganVO> getPageList(MyPage search) {
		// TODO Auto-generated method stub
		return null;
	}

}
