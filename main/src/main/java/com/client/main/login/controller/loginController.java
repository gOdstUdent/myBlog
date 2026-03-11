package com.client.main.login.controller;

import com.blog.common.domain.Response;
import com.client.main.login.domain.User;
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

    @GetMapping("/users")
    public Response listUsers(@RequestParam(required = false) String username,
                              @RequestParam(required = false) Boolean inuse) {
        return iloginService.listUsers(username, inuse);
    }

    @GetMapping("/users/{id}")
    public Response getUserById(@PathVariable Integer id) {
        return iloginService.getUserById(id);
    }

    @PostMapping("/users")
    public Response createUser(@RequestBody User user) {
        return iloginService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public Response updateUser(@PathVariable Integer id, @RequestBody User user) {
        return iloginService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public Response deleteUser(@PathVariable Integer id) {
        return iloginService.deleteUser(id);
    }
}
