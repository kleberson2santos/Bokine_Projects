package com.bokine.agendamento.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bokine.agendamento.model.Cliente;
import com.bokine.agendamento.util.jpa.Corporativo;

public class Clientes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	@Corporativo
	private EntityManager managerCorporativo;

	@Inject
	private EntityManager manager;

	public Set<Cliente> clientesFirebird = new HashSet<Cliente>();
	public List<String> todosclientesFirebird;

	public Cliente porNome2(String nome) {
		Query q = managerCorporativo.createNativeQuery("select c.nome from Clientes c where upper(c.nome) = :nome")
				.setParameter("nome", nome.toUpperCase());
				
				return (Cliente) q.getSingleResult();
	}

	public List<String> porNome(String nome) {
		Query q = managerCorporativo.createNativeQuery("select c.nome from Clientes c where upper(c.nome) like :nome")
		.setParameter("nome", nome.toUpperCase() + "%");
		
		@SuppressWarnings("unchecked")
		Collection<Object[]> results = q.getResultList();
		Iterator<Object[]> ite = results.iterator();
		todosclientesFirebird = new ArrayList<>();
		while (ite.hasNext()) {
//			String c = new String();
			Object element = ite.next();

//			c.setNome((String) element);
			todosclientesFirebird.add((String)element);
		}
		return todosclientesFirebird;
		
//		@SuppressWarnings("unchecked")
//		Collection<Object[]> results = q.getResultList();
//		Iterator<Object[]> ite = results.iterator();
//		while (ite.hasNext()) {
//			Cliente c = new Cliente();
//			Object element = ite.next();
//
//			c.setNome((String) element);
//			todosclientesFirebird.add(c);
//		}
//		return todosclientesFirebird;
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

}
