package com.ttps.reservasya.validator;

import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.users.User;
import com.ttps.reservasya.models.users.dto.UserDTO;
import com.ttps.reservasya.services.modelcrud.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass){
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {

        UserDTO user = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        try {
            userService.findByUserName(user.getUsername());
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        catch(UserNotFoundException ex) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
            if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password");
            }

            if (!user.getPasswordConfirm().equals(user.getPassword())) {
                errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
            }
        }
    }

}
