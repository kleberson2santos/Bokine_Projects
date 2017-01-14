package com.bokine.agendamento.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

	public List<Cliente> clientesFirebird = new ArrayList<Cliente>();

	public Cliente buscaClientePorNome(String nome) {
		Query q = managerCorporativo
				.createNativeQuery("select c.nome,c.cpf from Clientes c where upper(c.nome) like :nome")
				.setParameter("nome", nome.toUpperCase() + "%");

		@SuppressWarnings("unchecked")
		Collection<Object[]> results = q.getResultList();
		Iterator<Object[]> ite = results.iterator();
		while (ite.hasNext()) {
			Object[] elements = (Object[]) ite.next();
			String name = (String) elements[0];
			String cpf =removerCaracteres( (String) elements[1]);

			Cliente c = new Cliente(name, cpf);
			clientesFirebird.add(c);
		}
		if (clientesFirebird.size() == 1) {
			Cliente c = new Cliente();
			c.setNome(clientesFirebird.get(0).getNome());
			c.setCpf(clientesFirebird.get(0).getCpf());
			return c;
		}
		return new Cliente(nome,"000.000.000-00");
	}
	
	public Cliente buscaClientePorCpf(String cpf) {
		Query q = managerCorporativo
				.createNativeQuery("select c.nome,c.cpf from Clientes c where upper(c.cpf) like :cpf")
				.setParameter("cpf", cpf.toUpperCase() + "%");

		@SuppressWarnings("unchecked")
		Collection<Object[]> results = q.getResultList();
		Iterator<Object[]> ite = results.iterator();
		while (ite.hasNext()) {
			Object[] elements = (Object[]) ite.next();
			String name = (String) elements[0];
			String documento =removerCaracteres( (String) elements[1]);

			Cliente c = new Cliente(name, documento);
			clientesFirebird.add(c);
		}
		if (clientesFirebird.size() == 1) {
			Cliente c = new Cliente();
			c.setNome(clientesFirebird.get(0).getNome());
			c.setCpf(clientesFirebird.get(0).getCpf());
			return c;
		}
		return new Cliente("name",cpf);
	}
	
	public List<Cliente> porNome(String nome) {
		Query q = managerCorporativo
				.createNativeQuery("select c.nome,c.cpf from Clientes c where upper(c.nome) like :nome")
				.setParameter("nome", nome.toUpperCase() + "%");

		@SuppressWarnings("unchecked")
		Collection<Object[]> results = q.getResultList();
		Iterator<Object[]> ite = results.iterator();
		clientesFirebird.clear();
		while (ite.hasNext()) {
			Object[] elements = (Object[]) ite.next();
			String name = (String) elements[0];
			String cpf =removerCaracteres( (String) elements[1]);
			Cliente c = new Cliente(name, cpf);
			clientesFirebird.add(c);
		}
		return clientesFirebird;
	}
	
	public List<Cliente> porCpf(String cpf) {
		String str = "";
		if (!cpf.trim().isEmpty()){
			for(int i = 0;i<cpf.length();i++){  
		           str += cpf.charAt(i);
		             if(i==2){
		            	 str +=".";
		             }
		             if(i==5){
		            	 str +=".";
		             }
		             if(i==8){
		            	 str +="-";;
		             }
		    }
			Query q = managerCorporativo
					.createNativeQuery("select c.nome,c.cpf from Clientes c where c.cpf like :cpf")
					.setParameter("cpf", str + "%");

			@SuppressWarnings("unchecked")
			Collection<Object[]> results = q.getResultList();
			Iterator<Object[]> ite = results.iterator();
			clientesFirebird.clear();
			while (ite.hasNext()) {
				Object[] elements = (Object[]) ite.next();
				String name = (String) elements[0];
				String documento =removerCaracteres( (String) elements[1]);
				Cliente c = new Cliente(name, documento);
				clientesFirebird.add(c);
			}
			return clientesFirebird;
		}else
			return null;			
	}
	
	private String removerCaracteres(String str){
		if(str!=null){
			return str.replaceAll("\\D", "");
		}
		return null;
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

}
