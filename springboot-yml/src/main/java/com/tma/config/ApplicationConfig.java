package com.tma.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.tma.*"})
@EnableConfigurationProperties(ApplicationSetup.class)
public class ApplicationConfig {

	@Bean
	public PropertySource<?> yamlPropertySourceLoader() throws IOException {
		YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
		PropertySource<?> propertySource = loader.load("application.yml",
				new ClassPathResource("application.yml"), "default");
		return propertySource;

	}
}
