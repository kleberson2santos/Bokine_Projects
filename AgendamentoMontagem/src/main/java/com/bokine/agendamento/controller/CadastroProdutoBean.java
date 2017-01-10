package com.bokine.agendamento.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bokine.agendamento.model.Produto;
import com.bokine.agendamento.service.CadastroProdutoService;
import com.bokine.agendamento.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//@Inject
	//private Produtos produtos;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	//private List<Produto> itens;
		
	public CadastroProdutoBean() {
		limpar();
	}
	
	public void inicializar() {
		
		if (FacesUtil.isNotPostback()) {
		//itens = produtos.todos();
		}
	}
	
	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();		
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}
	
	private void limpar(){
		produto = new Produto();
	}
}
