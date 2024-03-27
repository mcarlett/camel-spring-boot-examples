package org.apache.camel.example.springboot.cxf.otel;

import org.apache.camel.ProducerTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OddServiceImpl implements OddService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OddServiceImpl.class);

	@Autowired
	ProducerTemplate producerTemplate;

	@Override
	public RandomNumber check(RandomNumber number) {
		if (number.getNumber() % 2 == 1) {
			//register
			producerTemplate.sendBody("direct:register", new RandomNumber().setNumber(number.getNumber()).setType(Constants.NUM_TYPE.ODD));
		} else {
			LOGGER.info("skip {}, it is even", number);
		}
		return number;
	}
}
