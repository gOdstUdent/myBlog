package com.myblog.login.controller;

import com.blog.common.domain.Response;
import com.blog.common.domain.ResponseStatus;
import com.myblog.login.domain.User;
import com.myblog.login.service.IloginService;
import com.sun.imageio.plugins.common.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

@RestController
@Slf4j
public class loginController {
    @Autowired
    private IloginService iloginService;
    @Value("${server.port}")
    private String port;;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam String username, @RequestParam String password) {
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return  new Response(ResponseStatus.ERROR, "200", "请输入用户名和密码",iloginService.getUserByName(username));

        }
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
/*            log.error(String.valueOf(subject.isPermitted("add")));
            log.error(String.valueOf(subject.isPermitted("query")));*/
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e.getMessage());
            return  new Response(ResponseStatus.ERROR, "200", "用户名不存在",iloginService.getUserByName(username));

        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e.getMessage());
            return  new Response(ResponseStatus.ERROR, "200", "账号或密码错误",iloginService.getUserByName(username));

        } catch (AuthorizationException e) {
            log.error("没有权限！", e.getMessage()


            );
            return  new Response(ResponseStatus.ERROR, "200", "没有权限!",iloginService.getUserByName(username));
        }
        return new Response(ResponseStatus.SUCCESS, "200", "登陆成功",iloginService.getUserByName(username));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        return iloginService.register(user);
    }

    @RequestMapping(value = "/user/checkCode",method = RequestMethod.GET)
    public  String checkCode(@RequestParam String code){
        return code;
    }
}
