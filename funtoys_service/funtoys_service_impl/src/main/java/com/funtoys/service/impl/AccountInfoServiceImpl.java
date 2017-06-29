package com.funtoys.service.impl;

import com.funtoys.service.dao.defined.AccountInfoDao;
import com.funtoys.service.dao.generation.AccountInfoMapper;
import com.funtoys.service.domain.defined.Paging;
import com.funtoys.service.domain.generation.AccountInfo;
import com.funtoys.service.inter.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by hejun on 2017/6/29.
 */
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Override
    public int countByCondition(Map<String, Object> record) {
        return accountInfoDao.countByCondition(record);
    }

    @Override
    public List<AccountInfo> selectPageByCondition(Map<String, Object> record, Paging paging) {
        return accountInfoDao.selectPageByCondition(record, paging);
    }

    @Override
    public boolean updateByConditionSelective(AccountInfo record, AccountInfo example) {
        return accountInfoDao.updateByConditionSelective(record, example) > 0;
    }

    @Override
    public List<AccountInfo> selectPageByConditionNoOrderBy(Map<String, Object> record, Paging paging) {
        return accountInfoDao.selectPageByConditionNoOrderBy(record, paging);
    }

    @Override
    public boolean insertSelective(AccountInfo record) {
        return accountInfoMapper.insertSelective(record) > 0;
    }

    @Override
    public AccountInfo selectByPrimaryKey(Integer id) {
        return accountInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(AccountInfo record) {
        return accountInfoMapper.updateByPrimaryKeySelective(record) > 0;
    }
}
