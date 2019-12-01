package com.sda.tasklist.service.user;

import com.sda.tasklist.dao.user.UserRepository;
import com.sda.tasklist.model.user.UserEntity;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements ApplicationListener<AuthenticationSuccessEvent> {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        UserEntity userEntity = userRepository.findByLogin(username).get();
        userEntity.setLastLoginStamp(LocalDateTime.now());
        userRepository.save(userEntity);
    }
}