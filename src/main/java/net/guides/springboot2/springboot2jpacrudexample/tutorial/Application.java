package net.guides.springboot2.springboot2jpacrudexample.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="net.guides.springboot2.springboot2jpacrudexample")
@EntityScan("net.guides.springboot2.springboot2jpacrudexample")
@EnableJpaRepositories("net.guides.springboot2.springboot2jpacrudexample")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
