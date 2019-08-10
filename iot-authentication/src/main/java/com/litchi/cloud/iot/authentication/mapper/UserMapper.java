package com.litchi.cloud.iot.authentication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.litchi.cloud.iot.authentication.domain.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjhf
 * @since 2019-08-05
 */
public interface UserMapper extends BaseMapper<User> {

	User selectByUsername(String username);
}
