package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import model.Etsycart;
import model.Etsyitem;
import model.Etsyuser;


public class DBUtil {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("EtsyShopping");

	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	
	public static Etsyitem getSelectedItem(long id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select e from Etsyitem e where e.itemId=" + id;
		System.out.println(sql);
		TypedQuery<Etsyitem> query = em.createQuery(sql, Etsyitem.class);
		List<Etsyitem> item;
		Etsyitem itemObj = new Etsyitem();
		try {
			item = query.getResultList();
			if (!item.isEmpty()){				
				itemObj = item.get(0);
			}
		} finally {
			em.close();
		}
		return itemObj;
	}
	
	
	public static List<Etsycart> getUserCart(Etsyuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select e from Etsycart e where e.etsyuser = :user and e.cartStatus=0";
		System.out.println(sql);
		TypedQuery<Etsycart> query = em.createQuery(sql, Etsycart.class).setParameter("user", user);
		List<Etsycart> cartList;
		try {
			cartList = query.getResultList();
		} finally {
			em.close();
		}
		return cartList;
	}
	
	public static void insert(Etsycart c) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(c);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void updateStatus(int status,Etsyuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Update Etsycart e set e.cartStatus = 1 where e.cartStatus = 0 and e.etsyuser=:user";
		Query query = em.createQuery(sql, Etsycart.class).setParameter("user", user);
		System.out.println("update query:" + sql);
		trans.begin();
		try {
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<Etsycart> getOrderedCart(Etsyuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql;
		TypedQuery<Etsycart> query;
			sql = "select c from Etsycart c where c.etsyuser = :user and c.cartStatus=1";
			query = em.createQuery(sql, Etsycart.class).setParameter("user", user);
	
		System.out.println(sql);
		List<Etsycart> cartList;
		try {
			cartList = query.getResultList();
			if (cartList == null || cartList.isEmpty())
				cartList = null;
		} finally {
			em.close();
		}
		return cartList;
	}
	
	public static void returnItem(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql;
		TypedQuery<Etsycart> query;
			sql = "Delete from Etsycart c  where c.cartId = " + id;
			query = em.createQuery(sql, Etsycart.class);
	
		System.out.println(sql);
		trans.begin();
		try {
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void delete(int prodId, Etsyuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql;
		Query query;
		if(user==null){
			sql = "Delete from Etsycart c  where c.etsyitem.itemId=" + prodId + " and c.cardStatus=0 and c.etsyuser is null";
			System.out.println(sql);
			query = em.createQuery(sql, Etsycart.class);
		} else{
			sql= "Delete from Etsycart c  where c.etsyitem.itemId=" + prodId + " and c.cartStatus=0 and c.etsyuser= :user";
			query = em.createQuery(sql, Etsycart.class).setParameter("user", user);
			System.out.println(sql);
		}
		trans.begin();
		try {
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void deleteAll(Etsyuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql;
		Query query;
		if(user==null){
			sql = "Delete from Etsycart c  where c.cartStatus=0 and c.etsyuser is null";
			System.out.println(sql);
			query = em.createQuery(sql, Etsyuser.class);
		} else{
			sql= "Delete from Etsycart c  where c.cartStatus=0 and c.etsyuser = :user";
			query = em.createQuery(sql, Etsyuser.class).setParameter("user", user);
		}
		trans.begin();
		try {
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	
}
