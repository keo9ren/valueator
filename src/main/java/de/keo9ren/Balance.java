package de.keo9ren;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the "Balance" database table.
 * 
 */
@Entity
@Table(name = "\"Balance\"")
@NamedQuery(name = "Balance.findAll", query = "SELECT b FROM Balance b")
public class Balance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1620578681563092951L;

	@Id
	private String id;

	private BigDecimal income;

	private BigDecimal expenditure;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Balance() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}