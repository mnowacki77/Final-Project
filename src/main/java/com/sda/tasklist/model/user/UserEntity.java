package com.sda.tasklist.model.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserEntity {
    private long id;
    private String login;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String securityQuestion;
}
