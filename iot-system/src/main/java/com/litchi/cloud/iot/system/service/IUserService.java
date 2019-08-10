package com.litchi.cloud.iot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.cloud.iot.system.domain.User;
import com.litchi.cloud.iot.system.vo.UserVO;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjhf
 * @since 2019-08-08
 */
public interface IUserService extends IService<User> {

	/** 
	 * 新增用户
	 * @param userVO
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> save(UserVO userVO);

	/** 
	 * 编辑修改用户
	 * @param userVO
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> update(UserVO userVO);

	/** 
	 * 删除用户
	 * @param id
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> delete(Integer id);

	/** 
	 * 通过id获取用户信息
	 * @param id
	 * @return 
	 * 返回类型  Result<UserVO> 
	 */
	Result<UserVO> getUserById(Integer id);

	/** 
	 * 分页获取用户信息
	 * @param userVO
	 * @return 
	 * 返回类型  PageResult<UserVO> 
	 */
	PageResult<UserVO> getPageList(UserVO userVO);

}
