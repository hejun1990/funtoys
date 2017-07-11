package com.funtoys.service.dao.defined;

import com.funtoys.service.domain.defined.Paging;
import com.funtoys.service.domain.generation.Brand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hejun on 2017/7/11.
 */
@Repository
public interface BrandDao {
    int countByCondition(@Param("record") Map<String, Object> record);

    List<Brand> selectPageByCondition(@Param("record") Map<String, Object> record, @Param("paging") Paging paging);

    int updateByConditionSelective(@Param("record") Brand record, @Param("example") Brand example);

    List<Brand> selectPageByConditionNoOrderBy(@Param("record") Map<String, Object> record, @Param("paging") Paging paging);
}