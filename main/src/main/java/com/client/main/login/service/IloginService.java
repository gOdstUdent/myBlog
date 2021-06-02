package com.client.main.login.service;

import org.springframework.stereotype.Service;

@Service
public interface IloginService {
     String login(String username,String passward);
}
