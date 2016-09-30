package br.com.infnet.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("YagoAV2PU");
	
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	} 
	
	public void close(EntityManager em){
		em.close();
	}
}
