package com.bokine.agendamento.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.bokine.usuario.model.Usuario;

@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCriacao;
	private StatusAgendamento status = StatusAgendamento.AGENDADO;
	private Date dataMontagem;
	private String cliente;
	private String documentoReceitaFederal;
	private String notaFiscal;
	private String romaneio;
	private String pedido;
	private String telefone;
	private String contato;
	private List<ItemMontagem> itens = new ArrayList<>();
	private String observacao;
	private Usuario usuario;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao" , nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date string) {
		this.dataCriacao = string;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable=false , length=10)
	public StatusAgendamento getStatus() {
		return status;
	}
	public void setStatus(StatusAgendamento status) {
		this.status = status;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_montagem" , nullable = false)
	public Date getDataMontagem() {
		return dataMontagem;
	}
	public void setDataMontagem(Date dataMontagem) {
		this.dataMontagem = dataMontagem;
	}
	
	@NotNull
	@Column(nullable=false , length=100)
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@NotNull
	@Column(name="doc_receita_federal" , nullable=false , length=14)
	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}
	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}
	
	@Column(name = "nota_fiscal" , length=10)
	public String getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	@Column(nullable=false , length=10)
	public String getRomaneio() {
		return romaneio;
	}
	public void setRomaneio(String romaneio) {
		this.romaneio = romaneio;
	}
	
	@Column(nullable=false , length=20)
	public String getPedido() {
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	
	@NotBlank
	@Column(nullable=false , length=10)
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(length=10)
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	
	@OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ItemMontagem> getItens() {
		return itens;
	}
	public void setItens(List<ItemMontagem> itens) {
		this.itens = itens;
	}
	@Column(columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Transient
	public boolean isNovo(){
		return getId()==null;
	}
	
	@Transient
	public boolean isExistente(){
		return !isNovo();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
