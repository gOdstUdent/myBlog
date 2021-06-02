package com.client.main.login.service.impl;

import com.blog.common.domain.Response;
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

}
