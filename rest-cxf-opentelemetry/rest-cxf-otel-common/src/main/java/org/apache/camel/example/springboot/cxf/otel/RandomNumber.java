package org.apache.camel.example.springboot.cxf.otel;

import java.util.Objects;
import java.util.Optional;

import jakarta.validation.constraints.NotNull;

public class RandomNumber {

	@NotNull
	Integer number;

	Constants.NUM_TYPE type;

	public RandomNumber() {
	}

	public RandomNumber(final Integer number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public RandomNumber setNumber(final Integer number) {
		this.number = number;
		return this;
	}

	public Constants.NUM_TYPE getType() {
		return type;
	}

	public RandomNumber setType(final Constants.NUM_TYPE type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		return Optional.ofNullable(number).map(Objects::toString).orElse("");
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final RandomNumber that = (RandomNumber) o;
		return Objects.equals(getNumber(), that.getNumber());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNumber());
	}
}
