package com.assignment.netent.bookStore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.assignment.netent.bookStore.DAO")
public class PersistenceJPAConfig {

}
