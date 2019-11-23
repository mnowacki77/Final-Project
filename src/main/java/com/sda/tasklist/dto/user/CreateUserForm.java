package com.sda.tasklist.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class CreateUserForm {

    private String login;
    private String password;
    private String confirmedPassword;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String securityQuestion;
    private String securityAnswer;

}
