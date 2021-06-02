package com.myblog.login.controller;

import com.blog.common.domain.Response;
import com.blog.common.domain.ResponseStatus;
import com.myblog.login.domain.User;
import com.myblog.login.service.IloginService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class loginController {
    @Autowired
    private IloginService iloginService;
    @Value("${server.port}")
    private String port;;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam String username, @RequestParam String password) {
        return new Response(ResponseStatus.SUCCESS, "200", "登陆成功",port);
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
