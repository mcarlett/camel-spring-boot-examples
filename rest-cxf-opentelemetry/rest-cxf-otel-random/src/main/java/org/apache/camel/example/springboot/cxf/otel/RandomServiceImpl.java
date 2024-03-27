package org.apache.camel.example.springboot.cxf.otel;

import static org.apache.camel.example.springboot.cxf.otel.Constants.SERVICE_HEADER_NAME;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RandomServiceImpl implements RandomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RandomServiceImpl.class);

	@Autowired
	ProducerTemplate producerTemplate;

	@Override
	public Results play(Integer attempts) {
		producerTemplate.requestBodyAndHeader("direct:play", null, "attempts", attempts, Void.class);
		return producerTemplate.requestBody("direct:load-results", null, Results.class);
	}

	@Override
	public RandomNumber generate() {
		return producerTemplate.requestBody("direct:random", null, RandomNumber.class);
	}

	@Override
	public void register(RandomNumber number, Exchange exchange) {
		final String serviceName = (String) exchange.getIn().getHeader(SERVICE_HEADER_NAME);
		assert serviceName != null;
		assert number != null;
		assert number.getType() != null;
		LOGGER.info("register {}", number);
		final String objName = String.format("%s-%s", serviceName, number.getNumber());
		producerTemplate.sendBodyAndHeader("direct:save-obj", number, "objectName", objName);
	}
}
