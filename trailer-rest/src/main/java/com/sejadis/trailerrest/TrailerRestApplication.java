package com.sejadis.trailerrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class TrailerRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailerRestApplication.class, args);
	}

}
