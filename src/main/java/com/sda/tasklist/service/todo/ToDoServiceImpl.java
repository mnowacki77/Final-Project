package com.sda.tasklist.service.todo;

import com.sda.tasklist.dao.todo.ToDoRepository;
import com.sda.tasklist.dao.user.UserRepository;
import com.sda.tasklist.dto.todo.CreateToDoForm;
import com.sda.tasklist.dto.todo.ToDoDTO;
import com.sda.tasklist.exception.ToDoNotExistsException;
import com.sda.tasklist.mapper.todo.ToDoMapper;
import com.sda.tasklist.model.todo.ToDoEntity;
import com.sda.tasklist.model.user.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ToDoDTO> getToDos() {
        UserEntity userEntity = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        return userEntity.getToDoEntityList().stream().map(t -> ToDoMapper.map(t)).collect(Collectors.toList());
    }

    @Override
    public void addToDo(CreateToDoForm createToDoForm) {
        UserEntity userEntity = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        ToDoEntity doEntity = ToDoMapper.map(createToDoForm);
        doEntity.setUser(userEntity);
        doEntity.setCreationDate(LocalDateTime.now());
        toDoRepository.save(doEntity);
    }

    @Override
    public String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public void deleteById(Long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDoDTO findById(Long id) throws ToDoNotExistsException {
        return ToDoMapper
                .map(toDoRepository
                        .findById(id)
                        .orElseThrow(() -> new ToDoNotExistsException("Task with given id doesn't exist")));
    }

    @Override
    public void editToDo(ToDoDTO toDoDTO) throws ToDoNotExistsException {
        ToDoEntity toDoEntity = toDoRepository
                .findById(toDoDTO.getId())
                .orElseThrow(() -> new ToDoNotExistsException("Task with given id doesn't exist"));
        toDoEntity.setName(toDoDTO.getName());
        toDoEntity.setDescription(toDoDTO.getDescription());
        toDoEntity.setStatus(toDoDTO.getStatus());
        toDoEntity.setDeadline(LocalDate.parse(toDoDTO.getDeadline()));
        toDoEntity.setDone(toDoDTO.isDone());
        toDoRepository.save(toDoEntity);
    }

    public List<ToDoDTO> getAllTodos() {
        return toDoRepository.findAll().stream().map(t -> ToDoMapper.map(t)).collect(Collectors.toList());
    }
}
