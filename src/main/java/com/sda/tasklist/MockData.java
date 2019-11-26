package com.sda.tasklist;


import com.sda.tasklist.dao.todo.ToDoRepository;
import com.sda.tasklist.dao.user.UserRepository;
import com.sda.tasklist.dao.user.UserRoleRepository;
import com.sda.tasklist.model.todo.ToDoEntity;
import com.sda.tasklist.model.user.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class MockData {

    private final UserRepository userRepository;
    private final ToDoRepository toDoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public MockData(UserRepository userRepository, ToDoRepository toDoRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.toDoRepository = toDoRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @PostConstruct
    public void mock() {

        if (!userRepository.existsByLogin("mock")) {
            UserEntity user = new UserEntity();
            user.setLogin("mock");
            user.setPassword(passwordEncoder.encode("mock"));
            user.setEmail("mock@mock.pl");
            user.setBirthDate(LocalDate.now());
            user.setSecurityQuestion("mock");
            user.setSecurityAnswer("mock");
            user.getRoles().add(userRoleRepository.findByName("USER"));
            user.getRoles().add(userRoleRepository.findByName("ADMIN"));
            UserEntity savedUser = userRepository.save(user);
            ToDoEntity todo = new ToDoEntity();
            todo.setName("Mock Task");
            todo.setDescription("Mock Description");
            todo.setStatus("OPEN");
            todo.setCreationDate(LocalDateTime.now());
            todo.setDeadline(LocalDate.now().plusDays(3));
            todo.setDone(false);
            todo.setUser(savedUser);
            toDoRepository.save(todo);
        }
    }
}
