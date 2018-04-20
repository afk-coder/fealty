package com.fux.auth.controller;

import com.fux.auth.entity.AuthPermission;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.entity.AuthUser;
import com.fux.auth.exception.CaptchaException;
import com.fux.auth.service.AuthPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxiaoj on 2018/03/27 11:52
 */
@Controller
public class HomeController {

    @Resource
    private AuthPermissionService authPermissionService;

    @RequestMapping({"/", "/index"})
    public String home(Model model) {
        System.out.println("HomeController.home");
        Subject subject = SecurityUtils.getSubject();
        AuthUser user = (AuthUser) subject.getPrincipal();
        List<AuthPermission> authPermissions = authPermissionService.findPermissionByUserId(user.getId());
        model.addAttribute("list", authPermissions);
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String username = request.getParameter("username");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "密码不正确";
            } else if (CaptchaException.class.getName().equals(exception)) {
                msg = "验证码错误";
            } else {
                msg = "请重试！";
            }
        }
        map.put("msg", msg);
        map.put("username", username);
        return "/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------没有权限-------");
        return "403";
    }
}
