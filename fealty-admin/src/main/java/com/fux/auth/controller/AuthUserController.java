package com.fux.auth.controller;

import com.fux.auth.entity.AuthRole;
import com.fux.auth.entity.AuthUser;
import com.fux.auth.service.AuthUserService;
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
 * Created by fuxiaoj on 2018/03/29 16:56
 */
@Controller
@RequestMapping("/user")
public class AuthUserController {

    @Resource
    private AuthUserService authUserService;

    @RequestMapping("list")
    @RequiresPermissions("user:list")
    public ModelAndView listView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/user/list");
        return model;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:list")
    public TableSplitResult list(SearchVo search, HttpServletRequest request) {
        return authUserService.list(search);
    }

    @RequestMapping("add")
    @RequiresPermissions("user:add")
    public ModelAndView addView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/user/add");
        String id = request.getParameter("id");
        AuthUser user;
        if(null != id && !id.equals("")) {
            user = authUserService.findById(Long.valueOf(id));
        } else {
            user = new AuthUser();
        }
        model.addObject("user", user);
        return model;
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo saveOrUpdate(AuthUser user) {
        return authUserService.saveOrUpdate(user);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:delete")
    public ResultVo delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        return authUserService.delete(Long.valueOf(id));
    }
}
