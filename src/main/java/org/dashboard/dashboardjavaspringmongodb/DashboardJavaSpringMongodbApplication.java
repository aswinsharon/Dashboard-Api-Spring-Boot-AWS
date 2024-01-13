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

@Configuration
@EnableWebMvc
class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:6001")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("Content-Type", "Authorization");
	}
}