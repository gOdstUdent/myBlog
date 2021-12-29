package com.myblog.login.config;

import com.myblog.login.domain.User;
import com.myblog.login.service.IloginService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @author yghadmin
 * 创建时间：2021/6/6 14:44
 * 创建说明：TODO
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private IloginService iloginService;

    /*
    * 验证用户权限
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=principalCollection.getPrimaryPrincipal().toString();
        return null;
    }

    /*
        * 验证用户登陆
        * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtils.isEmpty(authenticationToken.getPrincipal().toString())) {
            return null;
        }
        String username = authenticationToken.getPrincipal().toString();
        User user = iloginService.getUserByName(username);
        if (user == null) {
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
