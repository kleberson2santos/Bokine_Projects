package com.bokine.agendamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.bokine.agendamento.model.Cliente;
import com.bokine.agendamento.repository.Clientes;
import com.bokine.agendamento.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {
	// @Inject
	private Clientes clientes;

	public ClienteConverter() {
		this.clientes = (Clientes) CDIServiceLocator.getBean(Clientes.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente c = clientes.buscaClientePorNome(value);
		return c;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Cliente cliente = new Cliente();
		cliente = (Cliente) value;
		String v = cliente.getNome();
		return v;
	}
}
