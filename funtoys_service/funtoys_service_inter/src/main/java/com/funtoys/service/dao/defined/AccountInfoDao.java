package com.funtoys.service.dao.defined;

import com.funtoys.service.domain.defined.Paging;
import com.funtoys.service.domain.generation.AccountInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hejun on 2017/6/29.
 */
@Repository
public interface AccountInfoDao {
    int countByCondition(@Param("record") Map<String, Object> record);

    List<AccountInfo> selectPageByCondition(@Param("record") Map<String, Object> record, @Param("paging") Paging paging);

    int updateByConditionSelective(@Param("record") AccountInfo record, @Param("example") AccountInfo example);

    List<AccountInfo> selectPageByConditionNoOrderBy(@Param("record") Map<String, Object> record, @Param("paging") Paging paging);
}
