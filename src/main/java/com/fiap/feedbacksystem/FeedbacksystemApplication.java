package com.fiap.feedbacksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FeedbacksystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbacksystemApplication.class, args);
	}

}
