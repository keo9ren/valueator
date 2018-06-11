package de.keo9ren;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TestRepository {

	@PersistenceContext
	EntityManager em;

	public Balance createBalance() {
		Balance b = new Balance();
		b.setId(UUID.randomUUID().toString());
		b.setIncome(new BigDecimal("21"));
		b.setExpenditure(new BigDecimal("18"));
		em.persist(b);
		return b;
	}

}
