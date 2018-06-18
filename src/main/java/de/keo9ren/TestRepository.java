package de.keo9ren;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TestRepository {

	@PersistenceContext
	EntityManager em;

	public Balance newIncome(BigDecimal newIncome, BigDecimal newTs) {
		Balance b = new Balance();
		b.setId(UUID.randomUUID().toString());
		b.setIncome(newIncome);
		Date d = new Date();
		d.setTime(newTs.longValue());
		b.setDate(d);
		em.persist(b);
		return b;
	}

	public Balance createBalance() {
		Balance b = new Balance();
		b.setId(UUID.randomUUID().toString());
		b.setIncome(new BigDecimal("21"));
		b.setExpenditure(new BigDecimal("18"));
		em.persist(b);
		return b;
	}

	public List<Balance> readBalance() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Balance> cq = cb.createQuery(Balance.class);
		Root<Balance> balance = cq.from(Balance.class);

		cq.select(balance);
		TypedQuery<Balance> tq = em.createQuery(cq);
		List<Balance> allBalance = tq.getResultList();

		return allBalance;

	}

	public List<Balance> readIncome() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Balance> cq = cb.createQuery(Balance.class);
		Root<Balance> balance = cq.from(Balance.class);

		Predicate predicate = cb.isNotNull(balance.get(Balance_.income));

		cq.select(balance).where(predicate);
		TypedQuery<Balance> tq = em.createQuery(cq);
		List<Balance> allBalance = tq.getResultList();

		return allBalance;
	}

}
