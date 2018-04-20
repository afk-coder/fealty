package com.fux.auth.shiro;

import com.fux.auth.Constants;
import com.fux.auth.entity.AuthPermission;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.entity.AuthUser;
import com.fux.auth.exception.CaptchaException;
import com.fux.auth.service.AuthPermissionService;
import com.fux.auth.service.AuthRoleService;
import com.fux.auth.service.AuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户认证
 * Created by fuxiaoj on 2018/03/27 11:14
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthPermissionService authPermissionService;

    /**
     * 授权用户权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AuthUser authUser = (AuthUser) principalCollection.getPrimaryPrincipal();
        List<AuthRole> roles = authRoleService.findRoleByUserId(authUser.getId());
        for (AuthRole role : roles) {
            authorizationInfo.addRole(role.getRole());
        }
        List<AuthPermission> permissions = authPermissionService.findPermissionByUserId(authUser.getId());
        for (AuthPermission permission : permissions) {
            authorizationInfo.addStringPermission(permission.getPermission());
        }
        return authorizationInfo;
    }

    /**
     * 验证用户身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroToken token = (ShiroToken) authenticationToken;
        String severAuthCode = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.AUTH_CODE_SESSION_KEY);;
        String clientAuthCode = token.getAuthCode();
        if(StringUtils.isBlank(clientAuthCode)) {
            throw new CaptchaException("验证码不能为空！");
        }
        if(!clientAuthCode.equalsIgnoreCase(severAuthCode)) {
            throw new CaptchaException("验证码错误，请重新输入！");
        }
        //查询数据库是否存在
        AuthUser authUser = authUserService.findByUsername(token.getUsername());
        if(null != authUser) {
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(Constants.ADMIN_SESSION_KEY, authUser);
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    authUser, //用户名
                    authUser.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        } else {
            throw new UnknownAccountException("用户名不存在！"); //未找到账号
        }

    }
}
