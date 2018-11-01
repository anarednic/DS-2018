package repo;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Flight;
import entity.User;

public class FlightRepo {
	public void insertFlight(Flight c){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.merge(c);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
    }
	public void updateFlight(Flight c){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.flush(); 
        eniEntityManager.find(Flight.class, c.getFlightNumber());
    	//c.setStatus("ACTIVE");
    	//eniEntityManager.commit();
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
        }
	public void deleteFlight(Flight f){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.remove(eniEntityManager.contains(f) ? f : eniEntityManager.merge(f));
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
        }
	public List<Flight> viewAllFlights(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.flush();  
        List<Flight> listFlights = eniEntityManager.createQuery("SELECT c FROM Flight c").getResultList();
	    eniEntityManager.getTransaction().commit();
	    eniEntityManager.close();
        entityManagerFactory.close();
        return listFlights;
    }
	public List<Flight> allFlightsOfAUser(User user){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.flush();  
        List<Flight> listFlights = eniEntityManager.createQuery("SELECT c FROM Flight c join c.users u where u.id="+user.getId()).getResultList();
	    eniEntityManager.getTransaction().commit();
	    eniEntityManager.close();
        entityManagerFactory.close();
        return listFlights;
    }
}