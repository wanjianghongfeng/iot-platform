package com.litchi.cloud.iot.system.service.impl;

import com.litchi.cloud.iot.system.domain.Resource;
import com.litchi.cloud.iot.system.mapper.ResourceMapper;
import com.litchi.cloud.iot.system.service.IResourceService;
import com.litchi.cloud.iot.system.vo.ResourceVO;
import com.litchi.iot.common.result.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public Result<List<ResourceVO>> getResourceListByUserId(Integer userId) {
		List<Resource> resourceList = resourceMapper.getResourceListByUserId(userId);
		List<ResourceVO> rootList = new ArrayList<>(16);
        // 拆分顶级资源
        splitRootResourceList(resourceList, rootList);
        for (ResourceVO root : rootList) {
            treeSortNext(resourceList, root);
        }
        return Result.ok(rootList);
	}

	 /**
     * 拆分顶级资源和其他资源
     *
     * @param resourceList
     * @param rootResourceList 顶级资源
     * @return
     */
    public void splitRootResourceList(List<Resource> resourceList, List<ResourceVO> rootResourceList) {
        for (Resource resource : resourceList) {
            if (resource.getPid() == 0) {
                rootResourceList.add(domain2VO(resource));
            }
        }
    }

    /**
     * 递归遍历资源
     *
     * @param resourceList
     * @param parent
     */
    private void treeSortNext(List<Resource> resourceList, ResourceVO parent) {
        for (Resource resource : resourceList) {
            if (parent.getId().intValue() == resource.getPid().intValue()) {
                ResourceVO child = domain2VO(resource);
                if (parent.getChil() == null) {
                    List<ResourceVO> children = null;
                    children = new ArrayList<>();
                    children.add(child);
                    parent.setChil(children);
                } else {
                    parent.getChil().add(child);
                }
                treeSortNext(resourceList, child);
            }
        }
    }

	private ResourceVO domain2VO(Resource resource) {
		ResourceVO resourceVO = new ResourceVO();
		resourceVO.setId(resource.getId());
		resourceVO.setMethod(resource.getMethod());
		resourceVO.setName(resource.getName());
		resourceVO.setPageUrl(resource.getPageUrl());
		resourceVO.setPid(resource.getPid());
		resourceVO.setRemark(resource.getRemark());
		resourceVO.setSeq(resource.getSeq());
		resourceVO.setType(resource.getType());
		resourceVO.setUrl(resource.getUrl());
		resourceVO.setIcon(resource.getIcon());
		return resourceVO;
	}
}
