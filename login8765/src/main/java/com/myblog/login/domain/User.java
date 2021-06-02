package com.myblog.login.domain;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public class User {
    private String username;
    private String passward;
    private String userEmail;
    private String code= UUID.randomUUID().toString();
    private Boolean inuse=false;
}
