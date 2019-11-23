package com.sda.tasklist.dao.user;

import com.sda.tasklist.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserName(String name);

    Boolean existsByUserName(String name);

}
