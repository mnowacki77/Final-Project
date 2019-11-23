package com.sda.tasklist.service.user;

import com.sda.tasklist.dto.user.CreateUserForm;
import com.sda.tasklist.exception.UserExistsException;

public interface RegistrationService {
    void addNewUser(CreateUserForm createUserForm) throws UserExistsException;
}
