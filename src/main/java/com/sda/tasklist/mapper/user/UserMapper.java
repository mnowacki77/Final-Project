package com.sda.tasklist.mapper.user;

import com.sda.tasklist.dto.user.CreateUserForm;
import com.sda.tasklist.model.user.UserEntity;

public class UserMapper {

    public static UserEntity map(CreateUserForm createUserForm) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(createUserForm.getLogin());
        userEntity.setEmail(createUserForm.getEmail());
        userEntity.setBirthDate(createUserForm.getBirthDate());
        userEntity.setSecurityQuestion(createUserForm.getSecurityQuestion());
        userEntity.setSecurityAnswer(createUserForm.getSecurityAnswer());
        return userEntity;
    }

}
