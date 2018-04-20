package com.fux.auth.controller;

import com.alibaba.fastjson.JSONArray;
import com.fux.auth.entity.AuthPermission;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.service.AuthPermissionService;
import com.fux.auth.service.AuthRoleService;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.util.ZTreeDataJson;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxiaoj on 2018/03/29 16:37
 */
@Controller
@RequestMapping("/role")
public class AuthRoleController {

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthPermissionService authPermissionService;

    @RequestMapping("list")
    @RequiresPermissions("role:list")
    public ModelAndView listView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/role/list");
        return model;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:list")
    public TableSplitResult list(SearchVo search, HttpServletRequest request) {
        return authRoleService.list(search);
    }

    @RequestMapping("add")
    @RequiresPermissions("role:add")
    public ModelAndView addView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/role/add");
        String id = request.getParameter("id");
        AuthRole role;
        if(null != id && !id.equals("")) {
            role = authRoleService.findById(Long.valueOf(id));
        } else {
            role = new AuthRole();
        }
        model.addObject("role", role);
        return model;
    }

    @RequiresPermissions("role:add")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo saveOrUpdate(AuthRole role) {
        return authRoleService.saveOrUpdate(role);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:delete")
    public ResultVo delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        return authRoleService.delete(Long.valueOf(id));
    }

    @RequestMapping("grant")
    @RequiresPermissions("role:grant")
    public ModelAndView grantView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/role/grant");
        model.addObject("roleId", request.getParameter("roleId"));
        return model;
    }

    @RequestMapping(value = "grant", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:grant")
    public ResultVo grant(HttpServletRequest request) {
        String roleId = request.getParameter("roleId");
        JSONArray jsonArray = JSONArray.parseArray(request.getParameter("ids"));
        return authRoleService.saveGrant(Long.valueOf(roleId), jsonArray);
    }

    @RequestMapping(value = "getData", method = RequestMethod.POST)
    @ResponseBody
    public List<ZTreeDataJson> getZTreeDataJson(HttpServletRequest request, SearchVo search) {
        List<ZTreeDataJson> listJson = new ArrayList<>();
        Long roleId = Long.valueOf(request.getParameter("roleId"));
        List<AuthPermission> listRole = authPermissionService.findPermissionByRoleId(roleId);
        List<AuthPermission> list = authPermissionService.findAll(search);
        for (AuthPermission permission : list) {
            ZTreeDataJson dataJson = new ZTreeDataJson();
            dataJson.setId(permission.getId());
            dataJson.setpId(permission.getParentId());
            dataJson.setName(permission.getName());
            if(permission.getParentId() == 0) {
                dataJson.setOpen(true);
            }
            for (AuthPermission authPermission : listRole) {
                if(authPermission.getId() == permission.getId()) {
                    dataJson.setChecked(true);
                }
            }
            listJson.add(dataJson);
        }
        return listJson;
    }

}
