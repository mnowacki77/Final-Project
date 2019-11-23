package com.sda.tasklist.validator;

import com.sda.tasklist.dto.user.CreateUserForm;
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

    }
}

