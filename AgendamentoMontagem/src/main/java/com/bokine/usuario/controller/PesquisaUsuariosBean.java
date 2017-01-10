package com.bokine.usuario.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bokine.agendamento.repository.Usuarios;
import com.bokine.agendamento.repository.filter.UsuarioFilter;
import com.bokine.agendamento.util.jsf.FacesUtil;
import com.bokine.usuario.model.Usuario;

@Named
@ViewScoped
public class PesquisaUsuariosBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	private Usuario usuarioSelecionado;	

	private List<Usuario> usuariosFiltrados;

	private UsuarioFilter filtro;
	
	public PesquisaUsuariosBean() {
		filtro = new UsuarioFilter();
	}
	public void pesquisar(){
		usuariosFiltrados = usuarios.filtrados(filtro);
		//System.out.println("RETORNO: "+produtos.todosProdutosFirebird());
	}
	
	public void excluir(){
		System.out.println("USUARIO SELECIONADO "+usuarioSelecionado.getNome());
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuario "+usuarioSelecionado.getNome()+" excluido com sucesso.");
	}
	public void destroyWorld() {
        FacesUtil.addInfoMessage("USUARIO SELECIONADO "+usuarioSelecionado.getNome());
    }
	
	public List<Usuario> getusuariosFiltrados() {
		return usuariosFiltrados;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}