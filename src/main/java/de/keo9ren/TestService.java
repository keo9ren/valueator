package de.keo9ren;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TestService {

	@Inject
	TestRepository tr;

	public Balance setBalance() {
		Balance b = tr.createBalance();

		return b;
	}

}
