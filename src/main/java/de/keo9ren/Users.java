package de.keo9ren;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8681821655384281055L;

	@Id
	private String id;

	private String name;

	private String surname;

	private String username;

	public Users() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}