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
	public List<Cliente> todosclientesFirebird = new ArrayList<>();

	public List<Cliente> porNome(String nome) {
		return this.manager.createQuery("from Cliente " + "where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public List<Cliente> todosClientesFirebird() {
		Query q = managerCorporativo.createNativeQuery("select c.nome as nome from clientes c");
		@SuppressWarnings("unchecked")
		Collection<Object[]> results = q.getResultList();
		Iterator<Object[]> ite = results.iterator();
		while (ite.hasNext()) {
			Cliente c = new Cliente();
			Object element = ite.next();

			c.setNome((String) element);
			todosclientesFirebird.add(c);
		}
		return todosclientesFirebird;
	}

	// public Set<Cliente> todosClientesFirebird() {
	// Query q = managerCorporativo
	// .createNativeQuery("select c.nome from clientes c");
	// @SuppressWarnings("unchecked")
	// Collection<Object[]> results = q.getResultList();
	// Iterator<Object[]> ite =results.iterator();
	// while (ite.hasNext()) {
	// Cliente c = new Cliente();
	// Object[] result = (Object[]) ite.next();
	// String nome = (String) result[0];
	// c.setNome(nome);
	// clientesFirebird.add(c);
	// }
	// return clientesFirebird;
	// }

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

}
