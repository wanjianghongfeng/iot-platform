package com.litchi.cloud.iot.system.service.impl;

import com.litchi.cloud.iot.system.domain.Resource;
import com.litchi.cloud.iot.system.mapper.ResourceMapper;
import com.litchi.cloud.iot.system.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单资源 服务实现类
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
