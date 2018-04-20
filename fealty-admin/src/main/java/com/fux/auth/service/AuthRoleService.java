package com.fux.auth.service;

import com.alibaba.fastjson.JSONArray;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;

import java.util.List;
import java.util.Map;

/**
 * Created by fuxiaoj on 2018/03/30 15:39
 */
public interface AuthRoleService {

    TableSplitResult list(SearchVo search);

    ResultVo saveOrUpdate(AuthRole role);

    ResultVo delete(Long id);

    AuthRole findById(Long id);

    List<AuthRole> findRoleByUserId(Long userId);

    ResultVo saveGrant(Long roleId, JSONArray jsonArray);
}
