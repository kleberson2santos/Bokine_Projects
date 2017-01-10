package com.bokine.agendamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bokine.agendamento.repository.filter.UsuarioFilter;
import com.bokine.agendamento.service.NegocioException;
import com.bokine.agendamento.util.jpa.Corporativo;
import com.bokine.agendamento.util.jpa.Transactional;
import com.bokine.usuario.model.Usuario;

public class Usuarios implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private	EntityManager manager;
	
	@Inject
	@Corporativo
	private EntityManager managerCoporativo;
	
	public List<Usuario> usuarios(){
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}
	public void testeFirebird(){
		 managerCoporativo.createQuery("from Produtos").getResultList();
	}
	
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario); 		
	}
	
	@Transactional
	public void remover(Usuario usuario){
		try{
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Usuario não pode ser excluído.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);		
		//if (StringUtils.isNotBlank(filtro.getEmail())) {
		//	criteria.add(Restrictions.eq("email", filtro.getEmail()));
		//}		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	public void testaConexao(){
		if(managerCoporativo.getEntityManagerFactory().isOpen())
			System.out.println("CONEXAO FIREBIRD Aberta!");
		else
		System.out.println("CONEXAO FIREBIRD Fechada!");
	}

}
