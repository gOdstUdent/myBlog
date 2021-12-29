package com.myblog.login.service;

import com.myblog.login.domain.User;

public interface IloginService {

    String register(User user);

    User getUserByName(String name);
}
