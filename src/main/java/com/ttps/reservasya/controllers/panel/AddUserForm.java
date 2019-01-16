package com.ttps.reservasya.controllers.panel;

import com.ttps.reservasya.models.user.role.Role;

/**
 * @author nahuelbarrena on 15/01/19
 */
public class AddUserForm {

    private Long roleId;
    private String userName;
    private String password;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
