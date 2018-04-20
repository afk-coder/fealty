package com.fux.auth.service.impl;

import com.fux.auth.dao.AuthPermissionDao;
import com.fux.auth.entity.AuthPermission;
import com.fux.auth.service.AuthPermissionService;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;
import org.hibernate.criterion.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxiaoj on 2018/04/04 14:21
 */
@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Resource
    private AuthPermissionDao authPermissionDao;

    @Override
    public List<AuthPermission> findPermissionByUserId(Long userId) {
        return authPermissionDao.findByUserId(userId);
    }

    @Override
    public List<AuthPermission> findPermissionByRoleId(Long roleId) {
        return authPermissionDao.findByRoleId(roleId);
    }

    @Override
    public List<AuthPermission> findAll(SearchVo search) {
        Sort sort = new Sort(Sort.Direction.ASC,"reorder");
        return authPermissionDao.findAll(sort);
    }

    @Override
    public ResultVo saveOrUpdate(AuthPermission permission) {
        try {
            //判断菜单名是否重复
            authPermissionDao.save(permission);
            return ResultVo.successInfo("操作成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("操作异常!");
        }
    }

    @Override
    public ResultVo delete(Long id) {
        List<AuthPermission> list = authPermissionDao.findByRoleId(id);
        if(null != list && list.size() > 0) {
            return ResultVo.failedInfo("已授权，无法删除");
        }
        try {
            authPermissionDao.deleteById(id);
            return ResultVo.successInfo("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("删除异常！");
        }
    }

    @Override
    public AuthPermission findPermissionById(Long id) {
        return authPermissionDao.findById(id).get();
    }
}
