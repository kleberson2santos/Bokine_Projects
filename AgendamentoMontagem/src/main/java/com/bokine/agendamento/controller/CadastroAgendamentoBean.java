package com.bokine.agendamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bokine.agendamento.model.Agendamento;
import com.bokine.agendamento.model.Cliente;
import com.bokine.agendamento.repository.Clientes;
import com.bokine.agendamento.service.NegocioException;
import com.bokine.agendamento.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAgendamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	private Agendamento agendamento;
	private Cliente cliente;
	private String filtro;


	public void salvar() {
		throw new NegocioException("Agendamento nao pode ser salvo pois ainda nao foi implementado!");
	}

	public CadastroAgendamentoBean() {
		limpar();
	}
	
	private void limpar(){
		agendamento = new Agendamento();
		
	}
	
	public void inicializar(){
		if(FacesUtil.isNotPostback()){
			
		}
	}
	
	public List<String> completarCliente(String nome){
		
		cliente  = new Cliente(nome);
		filtro=nome;
		return clientes.porNome(getFiltro());
	}
	
	 public List<String> completeText(String query) {
	        List<String> results = new ArrayList<String>();
	        for(int i = 0; i < 10; i++) {
	            results.add(query + i);
	        }
	         
	        return results;
	    }

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
}
