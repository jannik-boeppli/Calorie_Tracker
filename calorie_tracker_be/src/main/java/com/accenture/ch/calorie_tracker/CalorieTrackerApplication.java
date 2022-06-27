package com.accenture.ch.calorie_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories("com.accenture.ch")
public class CalorieTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalorieTrackerApplication.class, args);
	}

}
