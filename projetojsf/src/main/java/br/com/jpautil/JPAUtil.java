package br.com.jpautil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {
	
	private EntityManagerFactory factory = null;
	
	public JPAUtil() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("projetojsf");
		}
	}
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public Object getPrimaryKey(Object entitY) {
		return factory.getPersistenceUnitUtil().getIdentifier(entitY);
	}

}
