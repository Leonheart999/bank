package com.acme.test01.levanchitiashvili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EntityScan(basePackages = "com.acme.test01.levanchitiashvili.models")
@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class})
@EnableJpaRepositories("com.acme.test01.levanchitiashvili.repositories.jpa")
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

}
