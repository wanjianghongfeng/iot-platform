package com.litchi.cloud.iot.system.service;

import com.litchi.cloud.iot.system.domain.Organ;
import com.litchi.cloud.iot.system.vo.OrganVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
public interface IOrganService extends IService<Organ> {

	/** 
	 * 新增机构
	 * @param organVO
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> save(OrganVO organVO);

	/** 
	 * 修改编辑机构
	 * @param organVO
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> update(OrganVO organVO);

	/** 
	 * 通过id删除机构
	 * @param id
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> delete(Integer id);

	/** 
	 * 根据id获取机构
	 * @param id
	 * @return 
	 * 返回类型  Result<OrganVO> 
	 */
	Result<OrganVO> getOrganById(Integer id);

	/** 
	 * 分页获取机构列表
	 * @param search
	 * @return 
	 * 返回类型  PageResult<OrganVO> 
	 */
	PageResult<OrganVO> getPageList(MyPage search);

	/** 
	 * 获取机构树
	 * @param organId
	 * @return 
	 * 返回类型  OrganVO 
	 */
	OrganVO getTreeList(Integer organId);

}
