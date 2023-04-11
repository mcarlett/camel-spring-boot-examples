package org.apache.camel.example.springboot.cxf;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.camel.test.spring.junit5.CamelSpringBootTest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@CamelSpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void serviceExposedTest() {
		final ResponseEntity<Object> response = restTemplate.getForEntity("/services/api/user", Object.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	//@Test
	public void listItemsTest() {
		final ResponseEntity<Collection<User>> response = restTemplate.exchange("/services/api/user",
				HttpMethod.GET, null, new ParameterizedTypeReference<Collection<User>>() {
				});
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		final Collection<User> body = response.getBody();
		assertThat(body).size().isEqualTo(3);
	}
}
