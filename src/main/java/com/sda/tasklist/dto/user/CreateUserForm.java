package com.sda.tasklist.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateUserForm {

    private String login;
    private String password;
    private String confirmedPassword;
    private String email;
    private LocalDate birthDate;
    private String securityQuestion;
    private String securityAnswer;

}
