package com.litchi.cloud.iot.system.service.impl;

import com.litchi.cloud.iot.system.domain.Organ;
import com.litchi.cloud.iot.system.mapper.OrganMapper;
import com.litchi.cloud.iot.system.service.IOrganService;
import com.litchi.cloud.iot.system.vo.OrganVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	
	private static final int NAME_REPEAT = 1;
	private static final int CODE_REPEAT = 2;
	
	@Resource
	private OrganMapper organMapper;

	@Override
	public Result<String> save(OrganVO organVO) {
		Organ organ = vO2Domain(organVO);
		int exist = exist(organ);
		if(exist == NAME_REPEAT) {
			return Result.error("名称重复");
		}
		if(exist == CODE_REPEAT) {
			return Result.error("编码重复");
		}
		Organ parent = organMapper.selectById(organ.getParentId());
		StringBuilder path = new StringBuilder(parent.getPath());
		organMapper.insert(organ);
		//生成path，更新path
		path.append("/").append(organ.getId());
		organ.setPath(path.toString());
		organMapper.updateById(organ);
		return Result.ok("新增成功");
	}

	@Override
	public Result<String> update(OrganVO organVO) {
		Organ organ = vO2Domain(organVO);
		int exist = exist(organ);
		if(exist == NAME_REPEAT) {
			return Result.error("名称重复");
		}
		if(exist == CODE_REPEAT) {
			return Result.error("编码重复");
		}
		organMapper.updateById(organ);
		return Result.ok("修改成功");
	}

	@Override
	public Result<String> delete(Integer id) {
		organMapper.deleteById(id);
		return Result.ok("删除成功");
	}

	@Override
	public Result<OrganVO> getOrganById(Integer id) {
		return Result.ok(domain2VO(organMapper.selectById(id)));
	}

	@Override
	public PageResult<OrganVO> getPageList(MyPage search) {
		Page<OrganVO> page = new Page<>(search.getPageNo(), search.getPageSize());
		IPage<Organ> iPage = organMapper.getPageList(page, search.getParam());
		List<OrganVO> organLst = new ArrayList<>();
		for(Organ u : iPage.getRecords()) {
			organLst.add(domain2VO(u));
		}
		return new PageResult<>(organLst, iPage.getTotal());
	}

	@Override
	public OrganVO getTreeList(Integer organId) {
		Organ organ = organMapper.selectById(organId);
		OrganVO rootOrgan = domain2VO(organ);
		QueryWrapper<Organ> wrapper = new QueryWrapper<>();
		wrapper.lambda().likeRight(Organ::getPath, organ.getPath());
		List<Organ> organLst = organMapper.selectList(wrapper);
		if (organLst != null && organLst.size() > 0) {
            // 递归便利数据
            treeSortNext(organLst, rootOrgan);
        }
		return rootOrgan;
	}
	
	private void treeSortNext(List<Organ> organList, OrganVO parent) {
        for (Organ organ : organList) {
            if (organ.getParentId().intValue() == parent.getId().intValue()) {
                OrganVO child = domain2VO(organ);
                if (parent.getChildren() == null) {
                    List<OrganVO> children = null;
                    children = new ArrayList<>();
                    children.add(child);
                    parent.setChildren(children);
                } else {
                    parent.getChildren().add(child);
                }
                treeSortNext(organList, child);
            }
        }
    }

	private Integer exist(Organ organ) {
		QueryWrapper<Organ> wrapperName = new QueryWrapper<>();
		wrapperName.lambda().eq(Organ::getName, organ.getName());
		if(!StringUtils.isEmpty(organ.getId())) {
			wrapperName.lambda().ne(Organ::getId, organ.getId());	
		}
		int count = organMapper.selectCount(wrapperName);
		if(count > 0) {
			return NAME_REPEAT;
		}
		QueryWrapper<Organ> wrapperCode = new QueryWrapper<>();
		wrapperCode.lambda().eq(Organ::getOrganCode, organ.getOrganCode());
		if(!StringUtils.isEmpty(organ.getId())) {
			wrapperCode.lambda().ne(Organ::getId, organ.getId());	
		}
		int count1 = organMapper.selectCount(wrapperCode);
		if(count1 > 0) {
			return CODE_REPEAT;
		}
		return 0;
	}

	private Organ vO2Domain(OrganVO organVO) {
		Organ organ = new Organ();
		organ.setId(organVO.getId());
		organ.setName(organVO.getName());
		organ.setOrganCode(organVO.getOrganCode());
		organ.setOrganType(organVO.getOrganType());
		organ.setParentId(organVO.getParentId());
		organ.setCreateTime(organVO.getCreateTime());
		organ.setCreateUserId(organVO.getCreateUserId());
		organ.setPath(organVO.getPath());
		organ.setUpdateTime(organVO.getUpdateTime());
		return organ;
	}
	
	private OrganVO domain2VO(Organ organ) {
		OrganVO organVO = new OrganVO();
		organVO.setId(organ.getId());
		organVO.setName(organ.getName());
		organVO.setOrganCode(organ.getOrganCode());
		organVO.setOrganType(organ.getOrganType());
		organVO.setParentId(organ.getParentId());
		organVO.setCreateTime(organ.getCreateTime());
		organVO.setCreateUserId(organ.getCreateUserId());
		organVO.setPath(organ.getPath());
		organVO.setUpdateTime(organ.getUpdateTime());
		return organVO;
	}
}
