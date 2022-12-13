package com.emanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }, scanBasePackages = ("controller"))
@EntityScan("models")
@EnableJpaRepositories("dao")
@ComponentScan({"controller","services","security"})
public class EManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EManagerApplication.class, args);
	}

}
