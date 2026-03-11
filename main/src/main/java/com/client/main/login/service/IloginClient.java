package com.client.main.login.service;

import com.blog.common.domain.Response;
import com.client.main.login.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "login")
public interface IloginClient {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Response login(@RequestParam(value = "username") String username,
                   @RequestParam(value = "password") String password);

    @GetMapping("/users")
    Response listUsers(@RequestParam(value = "username", required = false) String username,
                       @RequestParam(value = "inuse", required = false) Boolean inuse);

    @GetMapping("/users/{id}")
    Response getUserById(@PathVariable("id") Integer id);

    @PostMapping("/users")
    Response createUser(@RequestBody User user);

    @PutMapping("/users/{id}")
    Response updateUser(@PathVariable("id") Integer id, @RequestBody User user);

    @DeleteMapping("/users/{id}")
    Response deleteUser(@PathVariable("id") Integer id);
}
