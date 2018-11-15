package com.ttps.reservasya.transformers;

import com.ttps.reservasya.models.users.User;
import com.ttps.reservasya.models.users.dto.UserDTO;

public class UserTransformer {

    private UserTransformer(){}

    public static User toUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
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
