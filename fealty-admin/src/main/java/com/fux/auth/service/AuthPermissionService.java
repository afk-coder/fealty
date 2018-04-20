package com.fux.auth.service;

import com.fux.auth.entity.AuthPermission;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;

import java.util.List;

/**
 * Created by fuxiaoj on 2018/04/04 14:20
 */
public interface AuthPermissionService {

    List<AuthPermission> findPermissionByUserId(Long userId);

    List<AuthPermission> findPermissionByRoleId(Long roleId);

    List<AuthPermission> findAll(SearchVo search);

    ResultVo saveOrUpdate(AuthPermission permission);

    ResultVo delete(Long id);

    AuthPermission findPermissionById(Long id);
}
