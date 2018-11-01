package repo;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.City;

public class CityRepo {
	public void insertCity(City c){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.merge(c);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
    }
	public void updateCity(City c){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.flush();  
        eniEntityManager.find(City.class, c.getId());
    	//c.setStatus("ACTIVE");
    	//eniEntityManager.commit();
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
        }
	public void deleteCity(int id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.flush();   
        City c = new City();
        c.setId(id);
        eniEntityManager.find(City.class, c.getId());
        eniEntityManager.remove(c);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
        }
	public List<City> viewAllCitys(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.flush();  
        List<City> listCities = eniEntityManager.createQuery("SELECT c FROM City c").getResultList();
	    eniEntityManager.getTransaction().commit();
	    eniEntityManager.close();
        entityManagerFactory.close();
        return listCities;
    }
}