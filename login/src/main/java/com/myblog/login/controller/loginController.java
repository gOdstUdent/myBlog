package com.myblog.login.controller;

import com.blog.common.domain.Response;
import com.blog.common.domain.ResponseStatus;
import com.myblog.login.domain.User;
import com.myblog.login.service.IloginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class loginController {
    @Autowired
    private IloginService iloginService;

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

    @GetMapping("/users")
    public Response listUsers(@RequestParam(required = false) String username,
                              @RequestParam(required = false) Boolean inuse) {
        List<User> users = iloginService.listUsers(username, inuse)
                .stream()
                .map(this::hidePassword)
                .collect(Collectors.toList());
        return new Response(ResponseStatus.SUCCESS, "200", "查询成功", users);
    }

    @GetMapping("/users/{id}")
    public Response getUserById(@PathVariable Integer id) {
        User user = iloginService.getUserById(id);
        if (user == null) {
            return new Response(ResponseStatus.FAIL, "404", "用户不存在");
        }
        return new Response(ResponseStatus.SUCCESS, "200", "查询成功", hidePassword(user));
    }

    @PostMapping("/users")
    public Response createUser(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return new Response(ResponseStatus.FAIL, "400", "用户名和密码不能为空");
        }
        boolean created = iloginService.createUser(user);
        if (!created) {
            return new Response(ResponseStatus.ERROR, "500", "创建失败");
        }
        return new Response(ResponseStatus.SUCCESS, "200", "创建成功", hidePassword(user));
    }

    @PutMapping("/users/{id}")
    public Response updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getPassword()) && user.getInuse() == null) {
            return new Response(ResponseStatus.FAIL, "400", "至少提供一个要更新的字段");
        }
        boolean updated = iloginService.updateUser(id, user);
        if (!updated) {
            return new Response(ResponseStatus.FAIL, "404", "用户不存在或未更新");
        }
        User updatedUser = iloginService.getUserById(id);
        return new Response(ResponseStatus.SUCCESS, "200", "更新成功", hidePassword(updatedUser));
    }

    @DeleteMapping("/users/{id}")
    public Response deleteUser(@PathVariable Integer id) {
        boolean deleted = iloginService.deleteUser(id);
        if (!deleted) {
            return new Response(ResponseStatus.FAIL, "404", "用户不存在");
        }
        return new Response(ResponseStatus.SUCCESS, "200", "删除成功");
    }

    private User hidePassword(User user) {
        if (user == null) {
            return null;
        }
        user.setPassword(null);
        return user;
    }
}
