import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Etsyuser;
import EtsyTools.DBUtil;


public class EtsyuserDB {
	public static Etsyuser select() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyuser e";
		TypedQuery<Etsyuser> q = em.createQuery(query, Etsyuser.class);
		try {
			Etsyuser user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Etsyuser selectByName(String name) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyuser e WHERE e.name = '" + name + "'";
		TypedQuery<Etsyuser> q = em.createQuery(query, Etsyuser.class);
		try {
			Etsyuser user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Etsyuser selectByEmail(String email) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT e FROM Etsyuser e WHERE e.email = '" + email + "'";
		TypedQuery<Etsyuser> q = em.createQuery(query, Etsyuser.class);
		try {
			Etsyuser user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void insert(Etsyuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
}