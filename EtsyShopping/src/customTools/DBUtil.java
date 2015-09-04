package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
	
	public static Etsyitem getSelectedItem(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select e from Etsyitem e where e.itemId=" + id;
		System.out.println(sql);
		TypedQuery<Etsyitem> query = em.createQuery(sql, Etsyitem.class);
		Etsyitem item;
		try {
			item = query.getSingleResult();
			if (item == null)
				item = null;
		} finally {
			em.close();
		}
		return item;
	}
	
	
	public static List<Etsycart> getUserCart(Etsyuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select e from Etsycart e where e.etsyuser = :user";
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
}
