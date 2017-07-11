package com.funtoys.service.impl;

import com.funtoys.service.dao.defined.BrandDao;
import com.funtoys.service.dao.generation.BrandMapper;
import com.funtoys.service.domain.defined.Paging;
import com.funtoys.service.domain.generation.Brand;
import com.funtoys.service.inter.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by hejun on 2017/7/11.
 */
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandDao brandDao;

    @Override
    public int countByCondition(Map<String, Object> record) {
        return brandDao.countByCondition(record);
    }

    @Override
    public List<Brand> selectPageByCondition(Map<String, Object> record, Paging paging) {
        return brandDao.selectPageByCondition(record, paging);
    }

    @Override
    public boolean updateByConditionSelective(Brand record, Brand example) {
        return brandDao.updateByConditionSelective(record, example) > 0;
    }

    @Override
    public List<Brand> selectPageByConditionNoOrderBy(Map<String, Object> record, Paging paging) {
        return brandDao.selectPageByConditionNoOrderBy(record, paging);
    }

    @Override
    public boolean insertSelective(Brand record) {
        return brandMapper.insertSelective(record) > 0;
    }

    @Override
    public Brand selectByPrimaryKey(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Brand record) {
        return brandMapper.updateByPrimaryKeySelective(record) > 0;
    }
}
