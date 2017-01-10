package com.bokine.agendamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bokine.agendamento.model.Agendamento;
import com.bokine.agendamento.model.StatusAgendamento;
import com.bokine.agendamento.repository.Agendamentos;
import com.bokine.agendamento.repository.filter.AgendamentoFilter;

@Named
@ViewScoped
public class PesquisaAgendamentosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Agendamentos agendamentos;
	
	private AgendamentoFilter filtro;
	private List<Agendamento> agendamentosFiltrados;
	
	public PesquisaAgendamentosBean() {
		filtro = new AgendamentoFilter();
		agendamentosFiltrados = new ArrayList<>();
	}
	
	public void pesquisar(){
		agendamentosFiltrados = agendamentos.filtrados(filtro);
	}
	
	public StatusAgendamento[] getStatuses(){
		return StatusAgendamento.values();
	}	
	
	public AgendamentoFilter getFiltro() {
		return filtro;
	}

	public List<Agendamento> getAgendamentosFiltrados() {
		return agendamentosFiltrados;
	}
	
}