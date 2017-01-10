package com.bokine.agendamento.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.bokine.agendamento.model.Produto;
import com.bokine.agendamento.repository.Produtos;

public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	

	public Produto salvar(Produto produto){
		//TODO implementar regra de negcio
		return produtos.guardar(produto);
	}

}
