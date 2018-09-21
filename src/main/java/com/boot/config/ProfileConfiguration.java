package com.boot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("${spring.profiles.active}")
public class ProfileConfiguration {

}
