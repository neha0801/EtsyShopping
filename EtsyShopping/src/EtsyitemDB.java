import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Etsyitem;
import EtsyTools.DBUtil;

public class EtsyitemDB {
	public static List<Etsyitem> select() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyitem e";
		TypedQuery<Etsyitem> q = em.createQuery(query, Etsyitem.class);
		try {
			List<Etsyitem> productList = q.getResultList();
			return productList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Etsyitem selectById(long id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyitem e WHERE e.itemId = " + id;
		TypedQuery<Etsyitem> q = em.createQuery(query, Etsyitem.class);
		try {
			Etsyitem item = q.getSingleResult();
			return item;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static List<Etsyitem> selectByUserName(String userName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyitem e WHERE e.etsyuser.name = " + userName;
		TypedQuery<Etsyitem> q = em.createQuery(query, Etsyitem.class);
		try {
			List<Etsyitem> itemList = q.getResultList();
			return itemList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static List<Etsyitem> selectByInstock(int instock) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyitem e WHERE e.itemInstock = " + instock;
		TypedQuery<Etsyitem> q = em.createQuery(query, Etsyitem.class);
		try {
			List<Etsyitem> productList = q.getResultList();
			return productList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static List<Etsyitem> selectByKeyword(String keyword) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyitem e WHERE e.itemName LIKE '%" + keyword + "%'";
		TypedQuery<Etsyitem> q = em.createQuery(query, Etsyitem.class);
		try {
			List<Etsyitem> productList = q.getResultList();
			return productList;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void insert(Etsyitem item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}