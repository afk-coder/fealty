package com.fux.auth.service;

import com.fux.auth.entity.AuthJob;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;

import java.util.List;

/**
 * Created by fuxiaoj on 2018/04/16 10:45
 */
public interface AuthJobService {

    TableSplitResult list(SearchVo search);

    List<AuthJob> list();

    AuthJob findById(Long id);

    ResultVo saveOrUpdate(AuthJob job);

    ResultVo trigger(Long id); //触发任务

    ResultVo pause(Long id); //停止任务

    ResultVo resume(Long id); //恢复任务

    ResultVo delete(Long id); //删除任务
}
