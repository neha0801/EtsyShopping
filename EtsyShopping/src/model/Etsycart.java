package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the ETSYCART database table.
 * 
 */
@Entity
@Table(name="Etsycart",schema="testdb")
@NamedQuery(name="Etsycart.findAll", query="SELECT e FROM Etsycart e")
public class Etsycart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CART_ID")
	private long cartId;

	@Column(name="CART_STATUS")
	private int cartStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	private int quantity;

	private double totalprice;

	//bi-directional many-to-one association to Etsyitem
	@ManyToOne
	@JoinColumn(name="ITEM_ID")
	private Etsyitem etsyitem;

	//bi-directional many-to-one association to Etsyuser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Etsyuser etsyuser;

	public Etsycart() {
	}

	public long getCartId() {
		return this.cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getCartStatus() {
		return this.cartStatus;
	}

	public void setCartStatus(int cartStatus) {
		this.cartStatus = cartStatus;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalprice() {
		return this.totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public Etsyitem getEtsyitem() {
		return this.etsyitem;
	}

	public void setEtsyitem(Etsyitem etsyitem) {
		this.etsyitem = etsyitem;
	}

	public Etsyuser getEtsyuser() {
		return this.etsyuser;
	}

	public void setEtsyuser(Etsyuser etsyuser) {
		this.etsyuser = etsyuser;
	}

}