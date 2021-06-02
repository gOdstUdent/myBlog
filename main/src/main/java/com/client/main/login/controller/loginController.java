package com.client.main.login.controller;

import com.client.main.login.service.IloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class loginController {
    @Autowired
    private IloginService iloginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password) {
        return iloginService.login(username,password);
    }
}
