package com.bokine.agendamento.model;

public enum StatusAgendamento {

	AGENDADO("Agendado"),MONTADO("Montado"),CANCELADO("Cancelado");
	
	private String descricao;
	
	private StatusAgendamento(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
