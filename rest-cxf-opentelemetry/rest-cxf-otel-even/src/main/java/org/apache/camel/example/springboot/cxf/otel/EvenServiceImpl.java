package org.apache.camel.example.springboot.cxf.otel;

import org.apache.camel.ProducerTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EvenServiceImpl implements EvenService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EvenServiceImpl.class);

	@Autowired
	ProducerTemplate producerTemplate;

	@Override
	public RandomNumber check(RandomNumber number) {
		if (number.getNumber() % 2 == 0) {
			//register
			producerTemplate.sendBody("direct:register", new RandomNumber().setNumber(number.getNumber()).setType(Constants.NUM_TYPE.EVEN));
		} else {
			LOGGER.info("skip {}, it is odd", number);
		}
		return number;
	}
}
