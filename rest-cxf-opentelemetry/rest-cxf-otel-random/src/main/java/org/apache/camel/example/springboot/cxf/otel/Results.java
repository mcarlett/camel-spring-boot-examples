package org.apache.camel.example.springboot.cxf.otel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.validation.constraints.NotNull;

public class Results {
	@NotNull
	Map<Constants.NUM_TYPE, List<RandomNumber>> result;

	public Results() {
		result = new ConcurrentHashMap<>(2);
	}

	public Map<Constants.NUM_TYPE, List<RandomNumber>> getResult() {
		return result;
	}

	public int getEvenCount() {
		return count(Constants.NUM_TYPE.EVEN);
	}

	public int getOddCount() {
		return count(Constants.NUM_TYPE.ODD);
	}

	public Results addNumber(RandomNumber num) {
		addNumber(num.getType(), num);
		return this;
	}

	private void addNumber(Constants.NUM_TYPE evenOddKey, RandomNumber number) {
		final List<RandomNumber> values = Optional.ofNullable(result.get(evenOddKey)).orElseGet(() -> new ArrayList<>());
		values.add(number);
		result.put(evenOddKey, values);
	}

	private Integer count(Constants.NUM_TYPE key) {
		return Optional.ofNullable(result.get(key)).map(List::size).orElse(0);
	}

}
