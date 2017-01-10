package com.bokine.agendamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.bokine.agendamento.model.Cliente;
import com.bokine.agendamento.repository.Clientes;
import com.bokine.agendamento.util.cdi.CDIServiceLocator;
import com.bokine.usuario.model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class ClienteConverter implements Converter{
	
	//@Inject nao e possivel injetar em Conversores
	private Clientes clientes;
	
	public ClienteConverter() {
		clientes = CDIServiceLocator.getBean(Clientes.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno= null;
		if(StringUtils.isNotEmpty(value)){
			retorno = clientes.porId(new Long(value));		
		}
		return retorno;
	}	

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null){
			Cliente cliente = (Cliente) value;
			return cliente.getId() == null ? null : cliente.getId().toString();
		}
		return "";
	}
}
