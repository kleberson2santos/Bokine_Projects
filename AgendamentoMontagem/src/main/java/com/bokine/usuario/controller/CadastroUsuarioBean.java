package com.bokine.usuario.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bokine.agendamento.service.CadastroUsuarioService;
import com.bokine.agendamento.util.jsf.FacesUtil;
import com.bokine.usuario.model.Grupo;
import com.bokine.usuario.model.Grupos;
import com.bokine.usuario.model.Usuario;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	@Inject
	private Grupos repositorios_grupos;

	private Usuario usuario;
	private Grupo grupo;
	private List<Grupo> grupos; // Lista todos grupos do banco;

	private List<Grupo> gruposUsuario = new ArrayList<Grupo>();

	public CadastroUsuarioBean() {
		System.out.println("Chamando o construtor do controlador da pagina...");
		limpar();
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public void inicializar() {
		System.out.println("CHAMOU O INICIALIZAR");
		if (FacesUtil.isNotPostback()) {
			grupos = repositorios_grupos.grupos();
		}
	}

	public void adicionarGrupo() {
		if (grupo != null) {
			System.out.println("Grupo Selecionado: " + grupo.getNome());
			if (usuario.getGrupos().isEmpty()) {

				gruposUsuario.add(grupo);
				usuario.setGrupos(gruposUsuario);
			} else {
				System.out.println("Grupo do usuario nao e vazio");
				for (Grupo grupo : usuario.getGrupos()) {
					System.out.println("->" + grupo.getNome());
				}
				if (usuario.getGrupos().contains(grupo)) {
					FacesUtil.addInfoMessage("Esse grupo já foi adicionado.");
				} else {

					gruposUsuario.add(grupo);
					usuario.setGrupos(gruposUsuario);
				}
			}
		} else
			System.out.println("Grupo Selecionado NULO");
	}

	private void limpar() {
		System.out.println("Instanciando Usuario...");
		usuario = new Usuario();
	}

	public void salvar() {
		usuario = cadastroUsuarioService.salvar(usuario);
		limpar();
		FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		if (this.usuario != null) {
			this.grupos = usuario.getGrupos();
		}
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getgruposUsuario() {
		return gruposUsuario;
	}

	public void setgruposUsuario(List<Grupo> gruposUsuarios) {
		this.gruposUsuario = gruposUsuarios;
	}
}