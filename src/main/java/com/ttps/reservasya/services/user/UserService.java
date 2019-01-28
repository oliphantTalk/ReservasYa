package com.ttps.reservasya.services.user;


import com.ttps.reservasya.controllers.panel.form.ABMUserForm;
import com.ttps.reservasya.controllers.panel.form.ProfileForm;
import com.ttps.reservasya.error.exceptions.NoElementInDBException;
import com.ttps.reservasya.error.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.models.user.settings.UserSettings;
import com.ttps.reservasya.repository.user.UserRepository;
import com.ttps.reservasya.repository.user.UserSettingsRepository;
import com.ttps.reservasya.services.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.*;

@Service
public class UserService extends BasicCrudService<User, UserRepository> implements UserDetailsService {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserSettingsRepository settingsRepository;

    private RoleService roleService;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    
    public UserService(UserRepository userRepository){
        super(userRepository);
    }


    public void signin(User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    public UserSettings getUserSettingsByUserName(String userName){
        return settingsRepository.findByUser(repository.findByUsername(userName).get()).get();
    }

    private Authentication authenticate(User user) {
        return new UsernamePasswordAuthenticationToken(createOne(user), null, Collections.singleton(createAuthority(user)));
    }


    @Override
    @Transactional
    public User createOne(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(this.roleService.findById(1L).orElseThrow(NoElementInDBException::new));
        }
        repository.save(user);
        createUserSettings(user, 0);
        return user;
    }

    public User addUser(ABMUserForm userForm){
        User user = new User();
        user.setUsername(userForm.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        user.setRole(this.roleService.findById(userForm.getRoleId()).orElseThrow(NoElementInDBException::new));
        repository.save(user);
        createUserSettings(user, userForm.getAddPoints());
        return user;
    }

    public User editUser(ABMUserForm userForm){
        User user = repository.findById(userForm.getUserId()).orElseThrow(NoElementInDBException::new);
        if(!Objects.equals(user.getRole().getId(), userForm.getRoleId())) {
            user.setRole(this.roleService.findById(userForm.getRoleId()).orElseThrow(NoElementInDBException::new));
        }
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        this.updateUserSettings(user, userForm.getEditPoints());
        return updateOne(user);
    }

    public User editUserProfile(ProfileForm form, Long userId){
        User user = repository.findById(userId).orElseThrow(NoElementInDBException::new);
        user.setName(form.getPName());
        user.setEmail(form.getPEmail());
        user.setPassword(bCryptPasswordEncoder.encode(form.getPPassword()));
        return updateOne(user);
    }

    public User deleteUser(ABMUserForm userForm){
        User userToDelete = repository.findById(userForm.getDeleteUserId()).orElseThrow(NoElementInDBException::new);
        UserSettings userSettings = getUserSettingsByUserName(userToDelete.getUsername());
        userSettings.setUser(null);
        settingsRepository.save(userSettings);
        settingsRepository.delete(userSettings);
        repository.delete(userToDelete);
        return userToDelete;
    }

    private void createUserSettings(User user, int points) {
        UserSettings userSettings = new UserSettings();
        userSettings.setUser(user);
        userSettings.setPointsToUse(points);
        settingsRepository.save(userSettings);
    }

    private void updateUserSettings(User user, int points){
        UserSettings settings = settingsRepository.findByUser(user).orElseThrow(NoElementInDBException::new);
        settings.setPointsToUse(points);
        settingsRepository.save(settings);
    }

    public Optional<User> findByEmail(@Email String email){
        return repository.findByEmail(email);
    }

    public User findByUserName(String userName){
        return repository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username).orElseGet(() -> repository.findByEmail(username).orElseThrow(UserNotFoundException::new));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private GrantedAuthority createAuthority(User user) {
        return new SimpleGrantedAuthority(user.getRole().getName());
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

}
