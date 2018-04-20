package com.fux.auth.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.fux.auth.dao.AuthRoleDao;
import com.fux.auth.dao.AuthRolePermissionDao;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.entity.AuthRolePermission;
import com.fux.auth.service.AuthRoleService;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxiaoj on 2018/03/30 15:39
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Resource
    private AuthRoleDao authRoleDao;

    @Resource
    private AuthRolePermissionDao authRolePermissionDao;

    @Override
    public TableSplitResult list(SearchVo search) {
        Pageable pageable = new PageRequest(search.getPageNumber(), search.getPageSize());
        return new TableSplitResult(authRoleDao.findAll(pageable));
    }

    @Override
    public ResultVo saveOrUpdate(AuthRole role) {
        try {
            AuthRole authRole = authRoleDao.findAllByRole(role.getRole());
            if(role.getId() == null && authRole != null) {
                return ResultVo.failedInfo("角色名已存在，无法重复新增！");
            } else{
                if(!role.getId().equals(authRole.getId())) {
                    return ResultVo.failedInfo("角色名已存在！");
                }
            }
            authRoleDao.save(role);
            return ResultVo.successInfo("保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("保存异常");
        }
    }

    @Override
    public ResultVo delete(Long id) {
        try {
            authRoleDao.deleteById(id);
            return ResultVo.successInfo("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("删除异常");
        }
    }

    @Override
    public AuthRole findById(Long id) {
        return authRoleDao.findById(id).get();
    }

    @Override
    public List<AuthRole> findRoleByUserId(Long userId) {
        return authRoleDao.findByUserId(userId);
    }

    @Override
    public ResultVo saveGrant(Long roleId, JSONArray jsonArray) {
        try {
            //删除当前角色权限
            List<AuthRolePermission> rolePermissions = authRolePermissionDao.findByRoleId(roleId);
            authRolePermissionDao.deleteAll(rolePermissions);
            List<AuthRolePermission> list = new ArrayList<>();
            for (Object o : jsonArray) {
                AuthRolePermission rolePermission = new AuthRolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(Long.valueOf(o.toString()));
                list.add(rolePermission);
            }
            authRolePermissionDao.saveAll(list);
            return ResultVo.successInfo("授权成功！");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResultVo.failedInfo("授权失败！");
        }
    }
}
