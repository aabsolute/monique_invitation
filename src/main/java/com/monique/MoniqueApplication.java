package com.monique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoniqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoniqueApplication.class, args);
	}

}
