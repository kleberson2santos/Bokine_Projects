package com.bokine.agendamento.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.bokine.agendamento.model.StatusAgendamento;

public class AgendamentoFilter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long numeroDe;
	private Long numeroAte;
	private Date dataCriacaoDe;
	private Date dataCriacaoAte;
	private Date dataMontagemDe;
	private Date dataMontagemAte;
	private String cliente;
	private String documentoReceitaFederal;
	private StatusAgendamento[] statuses;
	public Long getNumeroDe() {
		return numeroDe;
	}
	public void setNumeroDe(Long numeroDe) {
		this.numeroDe = numeroDe;
	}
	public Long getNumeroAte() {
		return numeroAte;
	}
	public void setNumeroAte(Long numeroAte) {
		this.numeroAte = numeroAte;
	}
	public Date getDataCriacaoDe() {
		return dataCriacaoDe;
	}
	public void setDataCriacaoDe(Date dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}
	public Date getDataCriacaoAte() {
		return dataCriacaoAte;
	}
	public void setDataCriacaoAte(Date dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}
	public Date getDataMontagemDe() {
		return dataMontagemDe;
	}
	public void setDataMontagemDe(Date dataMontagemDe) {
		this.dataMontagemDe = dataMontagemDe;
	}
	public Date getDataMontagemAte() {
		return dataMontagemAte;
	}
	public void setDataMontagemAte(Date dataMontagemAte) {
		this.dataMontagemAte = dataMontagemAte;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}
	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}
	public StatusAgendamento[] getStatuses() {
		return statuses;
	}
	public void setStatuses(StatusAgendamento[] statuses) {
		this.statuses = statuses;
	}
	
	
	
}
