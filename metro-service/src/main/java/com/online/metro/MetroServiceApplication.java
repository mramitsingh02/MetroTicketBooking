package com.online.metro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.online.metro.registration.*"})
@EnableJpaRepositories(basePackages = {"com.online.metro.registration.*", "com.online.metro.registration.entity.*"})
public class MetroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetroServiceApplication.class, args);
	}

}
