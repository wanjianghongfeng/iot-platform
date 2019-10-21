package com.litchi.cloud.iot.system.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litchi.cloud.iot.system.domain.User;
import com.litchi.cloud.iot.system.vo.UserVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

/** 
 * 服务类
 * @author: wjhf-litchi
 * @date: 2019年8月11日 下午4:55:27
 * @vesion: 0.0.1
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
	PageResult<UserVO> getPageList(MyPage search);

	/** 
	 * 授权
	 * @param userId
	 * @param roleIdSet 
	 * 返回类型  void 
	 */
	void grant(Integer userId, Set<Integer> roleIdSet);

	/** 
	 * 获取用户角色
	 * @param userId
	 * @return 
	 * 返回类型  List<Integer> 
	 */
	List<Integer> getUserRoles(Integer userId);

	/** 
	 * 修改密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> updatePwd(Integer userId, String oldPwd, String newPwd);

	/** 
	 * 重置密码
	 * @param userId
	 * @return 
	 * 返回类型  Result<String> 
	 */
	Result<String> resetPwd(Integer userId);

}
