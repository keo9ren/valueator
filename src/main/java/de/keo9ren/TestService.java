package de.keo9ren;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TestService {

	@Inject
	TestRepository tr;

	public Balance setIncome(BigDecimal income, BigDecimal ts) {
		Balance b = tr.newIncome(income, ts);
		return b;
	}

	public Balance setBalance() {
		Balance b = tr.createBalance();

		return b;
	}

	public List<Balance> getIncome() {
		List<Balance> balance = tr.readIncome();
		return balance;
	}

	public List<Balance> getBalance() {
		List<Balance> balance = tr.readBalance();
		return balance;
	}

}
