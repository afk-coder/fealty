package com.fux.auth.druid.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by fuxiaoj on 2018/04/18 16:16
 */
@WebFilter(urlPatterns = "/demo1/*", initParams = {@WebInitParam(name = "exclusions",value = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")})
public class DruidStatFilter extends WebStatFilter {
}
