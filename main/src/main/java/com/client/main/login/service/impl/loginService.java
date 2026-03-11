package com.client.main.login.service.impl;

import com.blog.common.domain.Response;
import com.client.main.login.domain.User;
import com.client.main.login.service.IloginClient;
import com.client.main.login.service.IloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class loginService implements IloginService {
    @Autowired
    private IloginClient loginClient;
    /**
     * 调用登陆微服务
     * @param username
     * @param passward
     * @return
     */
    @Override
    public String login(String username, String passward) {
       Response response= loginClient.login(username,passward);
        return response.getData().toString();
    }

    @Override
    public Response listUsers(String username, Boolean inuse) {
        return loginClient.listUsers(username, inuse);
    }

    @Override
    public Response getUserById(Integer id) {
        return loginClient.getUserById(id);
    }

    @Override
    public Response createUser(User user) {
        return loginClient.createUser(user);
    }

    @Override
    public Response updateUser(Integer id, User user) {
        return loginClient.updateUser(id, user);
    }

    @Override
    public Response deleteUser(Integer id) {
        return loginClient.deleteUser(id);
    }

}
