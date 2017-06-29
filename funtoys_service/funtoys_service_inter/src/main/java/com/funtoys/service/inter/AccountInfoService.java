package com.funtoys.service.inter;

import com.funtoys.service.domain.defined.Paging;
import com.funtoys.service.domain.generation.AccountInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by hejun on 2017/6/29.
 */
public interface AccountInfoService {
    /**
     * 通过条件查询总数
     *
     * @param record
     * @return 记录总数
     */
    int countByCondition(Map<String, Object> record);

    /**
     * 通过条件查询账户信息
     *
     * @param record
     * @param paging
     * @return 账户信息
     */
    List<AccountInfo> selectPageByCondition(Map<String, Object> record, Paging paging);

    /**
     * 修改账户信息（自定义修改字段和修改条件）
     *
     * @param record
     * @param example
     * @return 是否成功
     */
    boolean updateByConditionSelective(AccountInfo record, AccountInfo example);

    /**
     * 通过条件查询账户信息(不排序)
     *
     * @param record
     * @param paging
     * @return 账户信息
     */
    List<AccountInfo> selectPageByConditionNoOrderBy(Map<String, Object> record, Paging paging);

    /**
     * 添加账户信息
     *
     * @param record
     * @return 是否成功
     */
    boolean insertSelective(AccountInfo record);

    /**
     * 通过主键查询账户信息
     *
     * @param id
     * @return 账户信息
     */
    AccountInfo selectByPrimaryKey(Integer id);

    /**
     * 通过主键修改账户信息
     *
     * @param record
     * @return 是否成功
     */
    boolean updateByPrimaryKeySelective(AccountInfo record);
}
