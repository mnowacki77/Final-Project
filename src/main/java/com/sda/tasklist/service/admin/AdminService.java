package com.sda.tasklist.service.admin;

import com.sda.tasklist.dto.user.UserDTO;

import java.util.List;

public interface AdminService {
    List<UserDTO> getUsers();
}
