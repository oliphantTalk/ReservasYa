package com.ttps.reservasya.controllers.panel.form;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nahuelbarrena on 15/01/19
 */
@Getter @Setter @NoArgsConstructor
public class ABMUserForm {

    private Long userId;
    private Long deleteUserId;
    private Long roleId;
    private String userName;
    private String password;
    private String editPassword;
    private int addPoints;
    private int editPoints;
}
