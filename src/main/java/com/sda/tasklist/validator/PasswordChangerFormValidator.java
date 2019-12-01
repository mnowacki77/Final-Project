package com.sda.tasklist.validator;

import com.sda.tasklist.dto.user.PasswordChangerForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class PasswordChangerFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordChangerForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordChangerForm pwd = (PasswordChangerForm) o;

        if (StringUtils.isBlank(pwd.getPassword())) {
            errors.rejectValue("password", "validator.notEmpty");
        }

        if (StringUtils.isBlank(pwd.getConfirmedPassword())) {
            errors.rejectValue("confirmedPassword", "validator.notEmpty");
        }

        if (!pwd.getPassword().equals(pwd.getConfirmedPassword())) {
            errors.rejectValue("password", "validator.PasswordsNotMatch");
            errors.rejectValue("confirmedPassword", "validator.PasswordsNotMatch");
        }

        if (StringUtils.isBlank(pwd.getSecurityAnswer())) {
            errors.rejectValue("securityAnswer", "validator.notEmpty");
        }

        if (pwd.getBirthDate() == null) {
            errors.rejectValue("birthDate", "validator.notEmpty");
        } else {
            if (pwd.getBirthDate().isAfter(LocalDate.now())) {
                errors.rejectValue("birthDate", "validator.notInFuture");
            }
        }


    }
}
