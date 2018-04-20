package com.fux.auth.controller;

import com.fux.auth.entity.AuthPermission;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.service.AuthPermissionService;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fuxiaoj on 2018/03/29 16:37
 */
@Controller
@RequestMapping("/permission")
public class AuthPermissionController {

    @Resource
    private AuthPermissionService authPermissionService;

    @RequestMapping("list")
    public ModelAndView listView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/permission/list");
        return model;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public List<AuthPermission> list(SearchVo search) {
        return authPermissionService.findAll(search);
    }

    @RequestMapping("add")
    public ModelAndView addView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String parentId = request.getParameter("parentId");
        String name;
        if(parentId.equals("0")) {
            name = "一级菜单";
        } else {
            AuthPermission p = authPermissionService.findPermissionById(Long.valueOf(parentId));
            name = p.getName();
        }
        model.addObject("parentId", parentId);
        model.addObject("name", name);
        String id = request.getParameter("id");
        AuthPermission permission;
        if(null != id && !id.equals("")) {
            permission = authPermissionService.findPermissionById(Long.valueOf(id));
        } else {
            permission = new AuthPermission();
            permission.setParentId(Long.valueOf(parentId));
        }
        model.addObject("permission", permission);
        model.setViewName("auth/permission/add");
        return model;
    }

    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo saveOrUpdate(AuthPermission permission) {
        return authPermissionService.saveOrUpdate(permission);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        return authPermissionService.delete(Long.valueOf(id));
    }

}
