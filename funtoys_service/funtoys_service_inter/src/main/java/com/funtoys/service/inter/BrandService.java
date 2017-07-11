package com.funtoys.service.inter;

import com.funtoys.service.domain.defined.Paging;
import com.funtoys.service.domain.generation.Brand;

import java.util.List;
import java.util.Map;

/**
 * Created by hejun on 2017/7/11.
 */
public interface BrandService {
    /**
     * 通过条件查询总数
     *
     * @param record 查询条件
     * @return 记录总数
     */
    int countByCondition(Map<String, Object> record);

    /**
     * 通过条件查询品牌信息
     *
     * @param record 查询条件
     * @param paging 分页条件
     * @return 品牌信息
     */
    List<Brand> selectPageByCondition(Map<String, Object> record, Paging paging);

    /**
     * 修改品牌信息（自定义修改字段和修改条件）
     *
     * @param record  修改字段
     * @param example 修改条件
     * @return 是否成功
     */
    boolean updateByConditionSelective(Brand record, Brand example);

    /**
     * 通过条件查询品牌信息(不排序)
     *
     * @param record 查询条件
     * @param paging 分页条件
     * @return 品牌信息
     */
    List<Brand> selectPageByConditionNoOrderBy(Map<String, Object> record, Paging paging);

    /**
     * 添加品牌信息
     *
     * @param record 插入字段
     * @return 是否成功
     */
    boolean insertSelective(Brand record);

    /**
     * 通过主键查询品牌信息
     *
     * @param id 主键ID
     * @return 品牌信息
     */
    Brand selectByPrimaryKey(Integer id);

    /**
     * 通过主键修改品牌信息
     *
     * @param record 修改字段
     * @return 是否成功
     */
    boolean updateByPrimaryKeySelective(Brand record);
}
