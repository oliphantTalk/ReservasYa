package com.ttps.reservasya.controllers.user;

import com.ttps.reservasya.controllers.panel.form.ABMUserForm;
import com.ttps.reservasya.controllers.panel.form.ProfileForm;
import com.ttps.reservasya.error.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.models.user.dto.UserDTO;
import com.ttps.reservasya.services.user.UserService;
import com.ttps.reservasya.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable("id") Long id) {
        return new UserDTO(this.userService.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/users")
    @ResponseBody
    public List<UserDTO> getUsers(){
        return this.userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/users/email/{email}")
    @ResponseBody
    public UserDTO getUserByEmail(@PathVariable("email") @Email String email) {
        return new UserDTO(this.userService.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El usuario no se encontro en el sistema")
    @ExceptionHandler(UserNotFoundException.class)
    public void userExceptionHandler(){
        /**
         * sdfgsdfgsdfgsdfgsdfg
         * sdfg
         * sdf
         * gsd
         * fg
         */
    }

    @GetMapping(value = "/users/userName/{userName}")
    @ResponseBody
    public UserDTO getUserByUserName(@PathVariable("userName") String userName) {
        return new UserDTO(this.userService.findByUserName(userName));
    }

    @PutMapping(value = "/user")
    public void addUser(@RequestBody @Valid final UserDTO userDTO){
        this.userService.createOne(UserTransformer.toUser(userDTO));
    }


    @PostMapping(value = "/user/add", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User createUser(Model model, @RequestBody ABMUserForm userForm, BindingResult result){
        return userService.addUser(userForm);
    }

    @PostMapping(value = "/user/edit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User editUser(Model model, @RequestBody ABMUserForm userForm, BindingResult result){
        return userService.editUser(userForm);
    }

    @PostMapping(value = "/user/delete", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User deleteUser(Model model, @RequestBody ABMUserForm userForm, BindingResult result){
        return userService.deleteUser(userForm);
    }

    @PostMapping(value = "/user/profile/edit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User editUserProfile(Model model, @RequestBody Map<String, String> profileForm, BindingResult result, Principal principal){
        User user = userService.findByUserName(principal.getName());
        ProfileForm profileForm1 = new ProfileForm();
        profileForm1.setPName(profileForm.get("pName"));
        profileForm1.setPEmail(profileForm.get("pEmail"));
        profileForm1.setPPassword(profileForm.get("pPassword"));
        return userService.editUserProfile(profileForm1, user.getId());
    }

    @GetMapping(value = "/user/profile/delete")
    public String deleteUserPrincipal(HttpSession httpSession, Model model, Principal principal){
        User user = userService.findByUserName(principal.getName());
        ABMUserForm form = new ABMUserForm();
        form.setDeleteUserId(user.getId());
        userService.deleteUser(form);
        httpSession.invalidate();
        return "redirect:/login";
    }
}
