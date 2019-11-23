package com.sda.tasklist;

import com.sda.tasklist.dao.user.UserRoleRepository;
import com.sda.tasklist.model.user.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskListApplication implements CommandLineRunner {

	@Autowired
	private UserRoleRepository userRoleRepository;


	public static void main(String[] args) {
		SpringApplication.run(TaskListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!userRoleRepository.existsByName("ADMIN")) {
			userRoleRepository.save(new UserRoleEntity("ADMIN"));
		}
		if (!userRoleRepository.existsByName("USER")) {
			userRoleRepository.save(new UserRoleEntity("USER"));
		}
	}
}
