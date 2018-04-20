package com.fux.auth.service;

import com.fux.auth.entity.AuthUser;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;

/**
 * Created by fuxiaoj on 2018/03/27 11:25
 */
public interface AuthUserService {

    AuthUser findByUsername(String username);

    TableSplitResult list(SearchVo search);

    AuthUser findById(Long id);

    ResultVo saveOrUpdate(AuthUser user);

    ResultVo delete(Long id);
}
