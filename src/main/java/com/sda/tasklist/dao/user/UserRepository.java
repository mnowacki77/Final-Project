package com.sda.tasklist.dao.user;

import com.sda.tasklist.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByLogin(String login);

    Boolean existsByLogin(String name);

    @Query("from UserEntity u left join fetch u.roles where u.login = :login")
    Optional<UserEntity> findUserWithRolesByLogin(String login);

}
