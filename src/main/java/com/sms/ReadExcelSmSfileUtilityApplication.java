package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
@EnableScheduling
public class ReadExcelSmSfileUtilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadExcelSmSfileUtilityApplication.class, args);
		System.out.println("Server Started...");
	}

}
