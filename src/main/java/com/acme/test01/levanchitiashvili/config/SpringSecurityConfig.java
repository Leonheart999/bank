package com.acme.test01.levanchitiashvili.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.authorizeHttpRequests(req -> {
            req.anyRequest()
                    .authenticated();
        }).httpBasic(withDefaults())
                .formLogin(withDefaults()).build();

//        http.authorizeRequests()
//                .antMatchers("/secure**").hasAnyRole("ADMIN","USER")
//                .anyRequest()
//                .authenticated()
//                .antMatchers("/home**").anonymous()
//                .and()
//                .formLogin().permitAll();
//        http;

    }

}