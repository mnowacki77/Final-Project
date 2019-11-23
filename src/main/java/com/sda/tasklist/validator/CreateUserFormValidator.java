package com.sda.tasklist.validator;

import com.sda.tasklist.dto.user.CreateUserForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateUserFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateUserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateUserForm createUserForm = (CreateUserForm) o;

        if (StringUtils.isBlank(createUserForm.getLogin())) {
            errors.rejectValue("login", "validator.notEmpty");
        }

        if (StringUtils.isBlank(createUserForm.getPassword())) {
            errors.rejectValue("password", "validator.notEmpty");
        }

        if (StringUtils.isBlank(createUserForm.getConfirmedPassword())) {
            errors.rejectValue("confirmedPassword", "validator.notEmpty");
        }

        if (StringUtils.isBlank(createUserForm.getEmail())) {
            errors.rejectValue("email", "validator.notEmpty");
        } else {
            if (!EmailValidator.isValid(createUserForm.getEmail())) {
                errors.rejectValue("email", "validator.emailFormat");
            }
        }

        if (!createUserForm.getPassword().equals(createUserForm.getConfirmedPassword())) {
            errors.rejectValue("password", "validator.PasswordsNotMatch");
            errors.rejectValue("confirmedPassword", "validator.PasswordsNotMatch");
        }

        if (StringUtils.isBlank(createUserForm.getSecurityQuestion())) {
            errors.rejectValue("securityQuestion", "validator.notEmpty");
        }

        if (StringUtils.isBlank(createUserForm.getSecurityAnswer())) {
            errors.rejectValue("securityAnswer", "validator.notEmpty");
        }
    }
}

