package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ETSYITEM database table.
 * 
 */
@Entity
@Table(name="Etsyitem", schema="testDB")
@NamedQuery(name="Etsyitem.findAll", query="SELECT e FROM Etsyitem e")
public class Etsyitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ITEM_ID")
	private long itemId;

	@Column(name="ITEM_DESCRIPTION")
	private String itemDescription;

	@Column(name="ITEM_INSTOCK")
	private int itemInstock;

	@Column(name="ITEM_NAME")
	private String itemName;

	@Column(name="ITEM_PRICE")
	private double itemPrice;

	@Column(name="ITEM_SHIPPINGCOST")
	private double itemShippingcost;

	@Column(name="ITEN_PICTURE")
	private String itenPicture;

	//bi-directional many-to-one association to Etsycart
	@OneToMany(mappedBy="etsyitem")
	private List<Etsycart> etsycarts;

	//bi-directional many-to-one association to Etsyuser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Etsyuser etsyuser;

	public Etsyitem() {
	}
	
	public Etsyitem(String name, String picture, String description, double price, double shipping, int instock, Etsyuser user) {
		this.itemName = name;
		this.itenPicture = picture;
		this.itemDescription = description;
		this.itemPrice = price;
		this.itemShippingcost = shipping;
		this.itemInstock = instock;
		this.etsyuser = user;
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemInstock() {
		if (this.itemInstock == 1) {
			return "Available";
		} else {
			return "NOT FOR SALE";
		}
	}

	public void setItemInstock(int itemInstock) {
		this.itemInstock = itemInstock;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getItemShippingcost() {
		return this.itemShippingcost;
	}

	public void setItemShippingcost(double itemShippingcost) {
		this.itemShippingcost = itemShippingcost;
	}

	public String getItenPicture() {
		return this.itenPicture;
	}

	public void setItenPicture(String itenPicture) {
		this.itenPicture = itenPicture;
	}

	public List<Etsycart> getEtsycarts() {
		return this.etsycarts;
	}

	public void setEtsycarts(List<Etsycart> etsycarts) {
		this.etsycarts = etsycarts;
	}

	public Etsycart addEtsycart(Etsycart etsycart) {
		getEtsycarts().add(etsycart);
		etsycart.setEtsyitem(this);

		return etsycart;
	}

	public Etsycart removeEtsycart(Etsycart etsycart) {
		getEtsycarts().remove(etsycart);
		etsycart.setEtsyitem(null);

		return etsycart;
	}

	public Etsyuser getEtsyuser() {
		return this.etsyuser;
	}

	public void setEtsyuser(Etsyuser etsyuser) {
		this.etsyuser = etsyuser;
	}

}