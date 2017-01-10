package com.bokine.agendamento;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.bokine.agendamento.model.Agendamento;
import com.bokine.agendamento.model.ItemMontagem;
import com.bokine.agendamento.model.Produto;
import com.bokine.agendamento.model.StatusAgendamento;
import com.bokine.agendamento.model.StatusItem;
import com.bokine.usuario.model.Grupo;
import com.bokine.usuario.model.Usuario;

public class Teste {
	public static void main(String[] args){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AgendamentoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Produto produto = new Produto();
		produto.setCodigo("0020125");
		produto.setDescricao("0020125 - ROUPEIRO TRES PORTAS AMERICANO");
		
		Grupo grupo = new Grupo();
		grupo.setNome("Administrador");
		grupo.setDescricao("Grupo de Administradores");

		Usuario usuario = new Usuario();
		usuario.setNome("Kleberson");
		usuario.setEmail("kleberson2santos@gmail.com");
		usuario.setSenha("123456");
		usuario.getGrupos().add(grupo);
		
		Agendamento agendamento = new Agendamento();
		agendamento.setDataCriacao(new Date());
		agendamento.setStatus(StatusAgendamento.MONTADO);
		agendamento.setDataMontagem(new Date());
		agendamento.setCliente("Joao da Silva");
		agendamento.setDocumentoReceitaFederal("98453524854");
		agendamento.setNotaFiscal("5555");
		agendamento.setRomaneio("2548");
		agendamento.setPedido("0021548");
		agendamento.setTelefone("9236371197");
		agendamento.setContato("9236372221");
		
		agendamento.setObservacao("Horario marcado!");
		agendamento.setUsuario(usuario);
		
		ItemMontagem item_montagem = new ItemMontagem();
		item_montagem.setProduto(produto);
		item_montagem.setStatusItem(StatusItem.PENDENTE);
		item_montagem.setAgendamento(agendamento);
		
		agendamento.getItens().add(item_montagem);
		
		manager.persist(produto);
		manager.persist(grupo);
		manager.persist(usuario);
		manager.persist(item_montagem);
		manager.persist(agendamento);
		
		trx.commit();
		
	}

}
