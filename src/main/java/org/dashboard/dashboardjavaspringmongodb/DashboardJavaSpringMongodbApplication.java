package org.dashboard.dashboardjavaspringmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DashboardJavaSpringMongodbApplication {
	public static void main(String[] args) {
		SpringApplication.run(DashboardJavaSpringMongodbApplication.class, args);
	}

}

