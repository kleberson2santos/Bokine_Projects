package com.bokine.agendamento.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bokine.agendamento.model.Produto;
import com.bokine.agendamento.util.jpa.Corporativo;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	@Corporativo
	private EntityManager managerCorporativo;
	public Set<Produto> produtosFirebird = new HashSet<Produto>();
	
	public List<Produto> todos() {
		return manager.createQuery("from Produto", 
				Produto.class).getResultList();
	}
	public Set<Produto> todosProdutosFirebird() {
		 Query q = managerCorporativo	
					.createNativeQuery("select p.cod_produto as codigo, p.descricao1 as descricao from produtos p");
		@SuppressWarnings("unchecked")
		Collection<Object[]> results = q.getResultList();
		Iterator<Object[]> ite =results.iterator();
        while (ite.hasNext()) {
        	Produto p = new Produto();
            Object[] result = (Object[]) ite.next();
            String codigo = (String) result[0];
            String descricao = (String) result[1];
 
            p.setCodigo(codigo);
            p.setDescricao(descricao);
            produtosFirebird.add(p);
            }
		return produtosFirebird; 					   
	}
	
	public Produto buscaProdutoPorCodigo(String codigo) {
	    return null;
	}
	

	public Produto guardar(Produto produto) {
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		produto = manager.merge(produto); 
		trx.commit();
		return produto;
	}
	
	public void testaConexao(){
		System.out.println("CONEXAO Aberta!"+managerCorporativo.getEntityManagerFactory().isOpen());
	}
	

}
