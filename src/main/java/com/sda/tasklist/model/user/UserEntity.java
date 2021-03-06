package com.sda.tasklist.model.user;

import com.sda.tasklist.model.todo.ToDoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String securityQuestion;
    private String securityAnswer;
    private LocalDateTime lastLoginStamp;

    @ManyToMany
    private Set<UserRoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<ToDoEntity> toDoEntityList = new ArrayList<>();
}
