package com.sda.tasklist.dao.user;

import com.sda.tasklist.model.user.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    boolean existsByName(String name);

    UserRoleEntity findByName (String name);
}
