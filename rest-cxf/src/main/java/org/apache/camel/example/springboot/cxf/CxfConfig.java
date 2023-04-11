package org.apache.camel.example.springboot.cxf;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import java.util.List;

import jakarta.validation.ValidationProviderResolver;

@Configuration
public class CxfConfig {

	//@Bean
	public JacksonJsonProvider jaxrsProvider() {
		return new JacksonJsonProvider();
	}

	@Bean
	public ValidationProviderResolver validationProviderResolver() {
		return () -> List.of(new HibernateValidator());
	}
}
