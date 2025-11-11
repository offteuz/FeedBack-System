package com.fiap.feedbacksystem;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeedbacksystemApplication {

	public static void main(String[] args) {
		// Ativa o Azure Application Insights por código.
		// Precisa vir antes do SpringApplication.run().
		ApplicationInsights.attach();
		
		SpringApplication.run(FeedbacksystemApplication.class, args);
	}

}
