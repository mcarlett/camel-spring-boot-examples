package org.apache.camel.example.springboot.cxf.otel;

import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;

@Converter(generateLoader = true)
public class IntegerConverter implements TypeConverters {

	@Converter
	public static RandomNumber toRandomNumber(Integer num) {
		return new RandomNumber(num);
	}
}
