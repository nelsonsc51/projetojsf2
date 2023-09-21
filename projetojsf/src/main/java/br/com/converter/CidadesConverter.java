package br.com.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Cidades;

//Para aceitar a classe como converter é necessário a anotação abaixo:
@FacesConverter(forClass = Cidades.class, value="cidadeConverter")
public class CidadesConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	//Retorna obljeto inteiro
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, 
				String codigoCidade) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			Cidades cidades = (Cidades) entityManager.
					find(Cidades.class, Long.parseLong(codigoCidade));
			
			return cidades;
		}

		//Retorna String
		@Override
		public String getAsString(FacesContext context, UIComponent component, 
				Object cidade) {
			
			if(cidade == null) {
				
				return null;
			}
		
			if(cidade instanceof Cidades) {
				return ((Cidades) cidade).getId().toString();
			}
			else {
				return cidade.toString();
			}
			
			//Código antigo	
			//		return ((Cidades) cidade).getId().toString();
				
			
			
		}

	
}
