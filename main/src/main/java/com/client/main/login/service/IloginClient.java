package com.client.main.login.service;

import com.blog.common.domain.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "login")
public interface IloginClient {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Response login(@RequestParam(value = "username") String username,
                   @RequestParam(value = "password") String password);
}
