package de.keo9ren;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TestService {

	@Inject
	TestRepository tr;

	public Balance setIncome(BigDecimal income) {
		Balance b = tr.newIncome(income);
		return b;
	}

	public Balance setBalance() {
		Balance b = tr.createBalance();

		return b;
	}

	public List<Balance> getBalance() {
		List<Balance> balance = tr.readBalance();
		return balance;
	}

}
