package com.litchi.cloud.iot.system.service.impl;

import com.litchi.cloud.iot.system.domain.Role;
import com.litchi.cloud.iot.system.mapper.RoleMapper;
import com.litchi.cloud.iot.system.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
