package com.myblog.login.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data

public class Role {

    private String roleId;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private List<Permissions> permissions;

}
