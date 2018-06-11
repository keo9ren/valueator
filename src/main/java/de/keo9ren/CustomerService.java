package de.keo9ren;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.Stateless;

@Stateless
public class CustomerService {

	public int getUser(int id) {
		return id;
	}

	private static Collector<BigDecimal, ?, BigDecimal> sum() {
		return Collectors.reducing(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getIncome() {
		List<BigDecimal> l = Arrays.asList(BigDecimal.ONE, BigDecimal.valueOf(2L), BigDecimal.valueOf(3L),
				BigDecimal.valueOf(4L));

		BigDecimal sum = l.stream().collect(sum());

		return sum;
	}

}
