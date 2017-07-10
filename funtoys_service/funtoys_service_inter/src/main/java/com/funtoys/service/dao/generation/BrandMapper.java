package com.funtoys.service.dao.generation;

import com.funtoys.service.domain.generation.Brand;
import com.funtoys.service.domain.generation.BrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandMapper {
    int countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(@Param("record") Brand record);

    int updateByPrimaryKey(Brand record);
}