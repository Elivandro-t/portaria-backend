package com.portariacd.modulos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PortariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortariaApplication.class, args);
	}

}
