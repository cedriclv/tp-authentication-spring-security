package com.wildcodeschool.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import com.wildcodeschool.spring.security.persistence.entities.User;
import com.wildcodeschool.spring.security.persistence.enums.RoleEnum;
import com.wildcodeschool.spring.security.persistence.repositories.UserRepository;

@SpringBootApplication
public class ExerciseApplication {
	private static Logger logger = LoggerFactory.getLogger(ExerciseApplication.class);

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@EventListener
	public void onStarted(ApplicationStartedEvent event) {
		logger.info("application started");

		// 2. Initialisation des données
		userRepository.deleteAll();

		Collection<RoleEnum> userRoles = new ArrayList<>();
		userRoles.add(RoleEnum.USER);
		User user = new User("user", "user", "userFN", "userLN", userRoles);
		userRepository.save(user);

		Collection<RoleEnum> adminRoles = new ArrayList<>();
		adminRoles.add(RoleEnum.ADMINISTRATOR);
		User admin = new User("admin", "admin", "adminFN", "adminLN", adminRoles);
		userRepository.save(admin);
	}
}
