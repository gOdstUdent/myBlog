package com.myblog.login.domain;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class User {
    private String userId;
    private String username;
    private String password;
    private String userEmail;
    private String code= UUID.randomUUID().toString();
    private Boolean inuse=false;
    private List<Role> roles;
}

