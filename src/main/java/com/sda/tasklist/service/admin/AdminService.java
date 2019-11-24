package com.sda.tasklist.service.admin;

import com.sda.tasklist.dao.user.UserRepository;
import com.sda.tasklist.dto.user.UserDTO;
import com.sda.tasklist.mapper.user.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(u -> UserMapper.map(u))
                .collect(Collectors.toList());
    }
}
