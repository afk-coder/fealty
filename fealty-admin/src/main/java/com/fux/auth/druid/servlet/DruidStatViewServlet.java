package com.fux.auth.druid.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by fuxiaoj on 2018/04/18 16:18
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {@WebInitParam(name = "loginUsername", value = "admin"), @WebInitParam(name = "loginPassword", value = "test"), @WebInitParam(name = "resetEnable", value = "false")})
public class DruidStatViewServlet extends StatViewServlet {
}
