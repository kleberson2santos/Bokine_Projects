package com.bokine.usuario.model;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Grupos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private	EntityManager manager;
	
	public List<Grupo> grupos(){
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
	
	public Grupo porId(Long id){
		return manager.find(Grupo.class, id);
	}

	public List<Grupo> gruposDe(Usuario usuario) {
		return null;
	}

}
