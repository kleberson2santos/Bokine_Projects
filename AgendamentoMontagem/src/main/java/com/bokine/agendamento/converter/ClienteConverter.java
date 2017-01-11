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
		System.out.println("Transformando o value:"+value);
		if(StringUtils.isNotEmpty(value)){
			retorno = clientes.porId(new Long(value));
			System.out.println("PARA UM OBJETO:");			
		}
		System.out.println("Mas veio nulo");
		return retorno;
	}	

	@SuppressWarnings("unused")
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("Transformando o objeto:"+value.toString());
		if(value!=null){
			Cliente cliente = (Cliente) value;
			System.out.println("PARA UM OBJETO:");
			if(cliente.getId() == null){
				System.out.println("MAS O ID VEIO NULLO");
				return null;}
			else{
				return cliente.getId().toString();}
			
		}
		return "";
	}
}
