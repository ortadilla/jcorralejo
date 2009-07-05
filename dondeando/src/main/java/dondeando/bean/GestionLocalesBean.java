package dondeando.bean;

import static utilidades.varios.NombresBean.GESTION_LOCALES_BEAN;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.NombresBean;
import dondeando.binding.GestionLocalesBinding;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_LOCALES_BEAN)
public class GestionLocalesBean {
	
	//Atributos
	private boolean desplegado;
	private String criterioNombre;
	
	//Utilidades
	@In(value=NombresBean.GESTION_LOCALES_BINDING, create=true)
	private GestionLocalesBinding binding;
	
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		desplegado = false;
	}


	public boolean isDesplegado() {
		return desplegado;
	}
	public void setDesplegado(boolean desplegado) {
		this.desplegado = desplegado;
	}
	public GestionLocalesBinding getBinding() {
		return binding;
	}
	public void setBinding(GestionLocalesBinding binding) {
		this.binding = binding;
	}


	public String getCriterioNombre() {
		return criterioNombre;
	}


	public void setCriterioNombre(String criterioNombre) {
		this.criterioNombre = criterioNombre;
	}

}
