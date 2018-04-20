package com.fux.auth.controller;

import com.fux.auth.entity.AuthJob;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.service.AuthJobService;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fuxiaoj on 2018/04/16 10:55
 */
@Controller
@RequestMapping("/job")
public class AuthJobController {

    @Resource
    private AuthJobService authJobService;

    @RequestMapping("list")
    @RequiresPermissions("job:list")
    public ModelAndView listView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/job/list");
        return model;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("job:list")
    public TableSplitResult list(SearchVo search) {
        return authJobService.list(search);
    }


    @RequestMapping("add")
    @RequiresPermissions("job:add")
    public ModelAndView addView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/job/add");
        String id = request.getParameter("id");
        AuthJob job;
        if(null != id && !id.equals("")) {
            job = authJobService.findById(Long.valueOf(id));
        } else {
            job = new AuthJob();
        }
        model.addObject("job", job);
        return model;
    }

    @RequiresPermissions("job:add")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo saveOrUpdate(AuthJob job) {
        return authJobService.saveOrUpdate(job);
    }

    @RequiresPermissions("job:tigger")
    @RequestMapping(value = "tigger", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo trigger(HttpServletRequest request) {
        String id = request.getParameter("id");
        return authJobService.trigger(Long.valueOf(id));
    }

    @RequiresPermissions("job:pause")
    @RequestMapping(value = "pause", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo pause(HttpServletRequest request) {
        String id = request.getParameter("id");
        return authJobService.pause(Long.valueOf(id));
    }

    @RequiresPermissions("job:resume")
    @RequestMapping(value = "resume", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo resume(HttpServletRequest request) {
        String id = request.getParameter("id");
        return authJobService.resume(Long.valueOf(id));
    }

    @RequiresPermissions("job:delete")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        return authJobService.delete(Long.valueOf(id));
    }
}
