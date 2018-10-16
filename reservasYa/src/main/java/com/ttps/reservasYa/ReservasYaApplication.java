package com.ttps.reservasYa;

import com.ttps.reservasYa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservasYaApplication {

	//@Autowired
	//private TestEntityManager entityManager;
	//@Autowired
	//private UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(ReservasYaApplication.class, args);


	}
}
