package com.client.main.login.service;

import com.blog.common.domain.Response;
import com.client.main.login.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface IloginService {
     String login(String username,String passward);

     Response listUsers(String username, Boolean inuse);

     Response getUserById(Integer id);

     Response createUser(User user);

     Response updateUser(Integer id, User user);

     Response deleteUser(Integer id);
}
