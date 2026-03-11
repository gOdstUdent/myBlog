package com.myblog.login.service;

import com.myblog.login.domain.User;

import java.util.List;

public interface IloginService {

    String register(User user);

    User getUserByName(String name);

    List<User> listUsers(String username, Boolean inuse);

    User getUserById(Integer id);

    boolean createUser(User user);

    boolean updateUser(Integer id, User user);

    boolean deleteUser(Integer id);
}
