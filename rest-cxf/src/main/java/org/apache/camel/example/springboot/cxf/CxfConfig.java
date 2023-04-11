package org.apache.camel.example.springboot.cxf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
@Configuration
public class CxfConfig {

	@Bean
	public JacksonJsonProvider jaxrsProvider() {
		return new JacksonJsonProvider();
	}

}
