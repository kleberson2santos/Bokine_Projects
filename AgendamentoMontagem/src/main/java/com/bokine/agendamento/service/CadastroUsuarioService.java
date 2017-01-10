package com.bokine.agendamento.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.bokine.agendamento.repository.Usuarios;
import com.bokine.agendamento.util.jpa.Transactional;
import com.bokine.usuario.model.Usuario;

public class CadastroUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		//TODO implementar regra de negocio
		return usuarios.guardar(usuario);
	}

}
