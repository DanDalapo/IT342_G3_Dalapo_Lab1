package com.dalapo.miniapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dalapo") 
@EnableJpaRepositories("com.dalapo.repository")
@EntityScan("com.dalapo.entity")
public class MiniappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniappApplication.class, args);
	}

}