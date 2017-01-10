package com.bokine.agendamento.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {
	@PersistenceUnit(unitName = "AgendamentoPU")
	private EntityManagerFactory factory;
	
	@PersistenceUnit(unitName = "Corporativo")
	private EntityManagerFactory factoryCorporativo;
	
	public EntityManagerProducer(){
		factory = Persistence.createEntityManagerFactory("AgendamentoPU");
		factoryCorporativo = Persistence.createEntityManagerFactory("Corporativo");
	}
	
	@Produces @RequestScoped @Default
	public EntityManager createEntityManager(){
		return factory.createEntityManager();
	}
	@Produces @RequestScoped @Corporativo 
    public EntityManager createCorporativoEntityManager() {
        return factoryCorporativo.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager manager){
		manager.close();		
	}

}
