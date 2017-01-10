package com.bokine.agendamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.bokine.agendamento.repository.Usuarios;
import com.bokine.agendamento.util.cdi.CDIServiceLocator;
import com.bokine.usuario.model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{
	
	//@Inject nao e possivel injetar em Conversores
	private Usuarios usuarios;
	
	public UsuarioConverter() {
		usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno= null;
		if(StringUtils.isNotEmpty(value)){
			Long id = new Long(value);
			retorno = usuarios.porId(id);		
		}
		return retorno;
	}
			

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null){
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return "";
	}
}
