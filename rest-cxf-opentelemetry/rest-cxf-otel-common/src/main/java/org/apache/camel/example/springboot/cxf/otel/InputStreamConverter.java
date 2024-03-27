package org.apache.camel.example.springboot.cxf.otel;

import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Converter(generateLoader = true)
public class InputStreamConverter implements TypeConverters {

	@Converter
	public static RandomNumber toRandomNumber(InputStream inputStream) throws IOException {
		return new ObjectMapper().readValue(inputStream, RandomNumber.class);
	}
}
