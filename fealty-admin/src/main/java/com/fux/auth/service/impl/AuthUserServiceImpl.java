package com.fux.auth.service.impl;

import com.fux.auth.dao.AuthUserDao;
import com.fux.auth.entity.AuthPermission;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.entity.AuthUser;
import com.fux.auth.service.AuthUserService;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxiaoj on 2018/03/27 11:25
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Resource
    private AuthUserDao userDao;

    @Override
    public AuthUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public TableSplitResult list(SearchVo search) {
        Pageable pageable = new PageRequest(search.getPageNumber(), search.getPageSize());
        return new TableSplitResult(userDao.findAll(pageable));
    }

    @Override
    public AuthUser findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public ResultVo saveOrUpdate(AuthUser user) {
        try {
            AuthUser authUser = userDao.findByUsername(user.getUsername());
            if(authUser.getId() == null && authUser != null) {
                return ResultVo.failedInfo("用户名已存在，无法重复新增！");
            } else{
                if(!authUser.getId().equals(authUser.getId())) {
                    return ResultVo.failedInfo("用户名已存在！");
                }
            }
            userDao.save(user);
            return ResultVo.successInfo("保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("保存异常");
        }
    }

    @Override
    public ResultVo delete(Long id) {
        try {
            userDao.deleteById(id);
            return ResultVo.successInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("删除异常");
        }
    }
}
