package com.bokine.agendamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.bokine.agendamento.util.cdi.CDIServiceLocator;
import com.bokine.usuario.model.Grupo;
import com.bokine.usuario.model.Grupos;

@FacesConverter(forClass = Grupo.class)
public class GruposConverter implements Converter{
	
	//@Inject
	private Grupos grupos;
	
	public GruposConverter() {
		grupos = CDIServiceLocator.getBean(Grupos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno= null;
		if(value!=null){
			Long id = new Long(value);
			retorno = grupos.porId(id);		
		}
		return retorno;
	}
			

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null){
			Grupo grupo = (Grupo) value;
			return grupo.getId() == null ? null : grupo.getId().toString();
		}
		System.out.println("O valor do Id Grupo é nulo!");
		return "";
	}
	

}
