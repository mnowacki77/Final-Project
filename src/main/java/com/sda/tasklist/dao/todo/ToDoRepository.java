package com.sda.tasklist.dao.todo;

import com.sda.tasklist.model.todo.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {

}
