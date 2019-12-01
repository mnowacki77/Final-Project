package com.sda.tasklist.service.user;

import com.sda.tasklist.dao.user.UserRepository;
import com.sda.tasklist.dto.user.PasswordChangerForm;
import com.sda.tasklist.model.user.UserEntity;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements ApplicationListener<AuthenticationSuccessEvent> {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        UserEntity userEntity = userRepository.findByLogin(username).get();
        userEntity.setLastLoginStamp(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    public PasswordChangerForm prepareForm() {
        UserEntity userEntity = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        PasswordChangerForm passwordChangerForm = new PasswordChangerForm();
        passwordChangerForm.setSecurityQuestion(userEntity.getSecurityQuestion());
        return passwordChangerForm;
    }

    public boolean changePassword(PasswordChangerForm pwd) {
        UserEntity userEntity = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        if (!pwd.getSecurityAnswer().equals(userEntity.getSecurityAnswer())) {
            return false;
        }
        if (!pwd.getBirthDate().isEqual(userEntity.getBirthDate())) {
            return false;
        }
        userEntity.setPassword(passwordEncoder.encode(pwd.getPassword()));
        userRepository.save(userEntity);
        return true;
    }

}