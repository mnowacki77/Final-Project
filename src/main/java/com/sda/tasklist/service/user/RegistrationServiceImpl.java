package com.sda.tasklist.service.user;

import com.sda.tasklist.dao.user.UserRepository;
import com.sda.tasklist.dao.user.UserRoleRepository;
import com.sda.tasklist.dto.user.CreateUserForm;
import com.sda.tasklist.exception.UserExistsException;
import com.sda.tasklist.mapper.user.UserMapper;
import com.sda.tasklist.model.user.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void addNewUser(CreateUserForm createUserForm) throws UserExistsException {
        if (userRepository.existsByLogin(createUserForm.getLogin())) {
            throw new UserExistsException("User with given login already exists!");
        }
        if (userRepository.existsByEmail(createUserForm.getEmail())) {
            throw new UserExistsException("User with given Email already exists!");
        }
        UserEntity userEntity = UserMapper.map(createUserForm);
        userEntity.setPassword(passwordEncoder.encode(createUserForm.getPassword()));
        userEntity.setLastLoginStamp(LocalDateTime.now());

        if (userEntity.getLogin().contains("admin")) {
            userEntity.getRoles().add(userRoleRepository.findByName("ADMIN"));
        } else {
            userEntity.getRoles().add(userRoleRepository.findByName("USER"));
        }

        userRepository.save(userEntity);
    }
}
