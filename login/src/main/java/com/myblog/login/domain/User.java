package com.myblog.login.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String userEmail;
    private String code= UUID.randomUUID().toString();
    private Boolean inuse=false;
    private List<Role> roles;
}

