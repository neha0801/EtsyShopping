package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ETSYUSER database table.
 * 
 */
@Entity
@Table(name="Etsyuser", schema="testDB")
@NamedQuery(name="Etsyuser.findAll", query="SELECT e FROM Etsyuser e")
public class Etsyuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private long userId;

	private double credit;

	private String email;

	private String name;

	private String password;

	//bi-directional many-to-one association to Etsycart
	@OneToMany(mappedBy="etsyuser")
	private List<Etsycart> etsycarts;

	//bi-directional many-to-one association to Etsyitem
	@OneToMany(mappedBy="etsyuser")
	private List<Etsyitem> etsyitems;

	public Etsyuser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getCredit() {
		return this.credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Etsycart> getEtsycarts() {
		return this.etsycarts;
	}

	public void setEtsycarts(List<Etsycart> etsycarts) {
		this.etsycarts = etsycarts;
	}

	public Etsycart addEtsycart(Etsycart etsycart) {
		getEtsycarts().add(etsycart);
		etsycart.setEtsyuser(this);

		return etsycart;
	}

	public Etsycart removeEtsycart(Etsycart etsycart) {
		getEtsycarts().remove(etsycart);
		etsycart.setEtsyuser(null);

		return etsycart;
	}

	public List<Etsyitem> getEtsyitems() {
		return this.etsyitems;
	}

	public void setEtsyitems(List<Etsyitem> etsyitems) {
		this.etsyitems = etsyitems;
	}

	public Etsyitem addEtsyitem(Etsyitem etsyitem) {
		getEtsyitems().add(etsyitem);
		etsyitem.setEtsyuser(this);

		return etsyitem;
	}

	public Etsyitem removeEtsyitem(Etsyitem etsyitem) {
		getEtsyitems().remove(etsyitem);
		etsyitem.setEtsyuser(null);

		return etsyitem;
	}

}