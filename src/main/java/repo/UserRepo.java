package repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.User;

public class UserRepo {
	public void insertUser(User c) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		eniEntityManager.merge(c);
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
	}

	public void updateUser(User c) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		eniEntityManager.flush();
		eniEntityManager.find(User.class, c.getId());
		// c.setStatus("ACTIVE");
		// eniEntityManager.commit();
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
	}

	public void deleteUser(int id) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		eniEntityManager.flush();
		User c = new User();
		c.setId(id);
		eniEntityManager.find(User.class, c.getId());
		eniEntityManager.remove(c);
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
	}

	public List<User> viewAllUsers() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		eniEntityManager.flush();
		List<User> listUsers = eniEntityManager.createQuery("SELECT c FROM User c").getResultList();
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return listUsers;
	}

	public User getUserById(int id) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		eniEntityManager.flush();
		User user = (User) eniEntityManager.createQuery("SELECT c FROM User c WHERE id=" + id).getResultList().stream()
				.findFirst().orElse(null);
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return user;
	}
}