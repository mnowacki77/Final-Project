package com.sda.tasklist.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserModel {
    private long id;
    private String login;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String securityQuestion;
}
