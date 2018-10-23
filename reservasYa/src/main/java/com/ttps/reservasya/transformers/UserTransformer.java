package com.ttps.reservasya.transformers;

import com.ttps.reservasya.models.User;
import com.ttps.reservasya.models.dto.UserDTO;

public class UserTransformer {

    private UserTransformer(){}

    public static User toUser(UserDTO userDTO){
        User user = new User();
        user.editName(userDTO.getName());
        user.editUserName(userDTO.getUsername());
        user.editEmail(userDTO.getEmail());
        user.editPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

}
