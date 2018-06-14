package com.setplex.odin.provider.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.setplex.odin.provider")
@EnableJpaRepositories("com.setplex.odin.provider")
public class ProviderConfig {
}
