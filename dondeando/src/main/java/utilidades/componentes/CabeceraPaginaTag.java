package utilidades.componentes;

import static dondeando.bean.MenuPrincipalBean.JSF_MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.CABECERA_PAGINA_BEAN;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import java.io.IOException;

import javax.faces.webapp.FacetTag;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.myfaces.taglib.core.VerbatimTag;
import org.apache.myfaces.trinidad.webapp.UIXComponentTag;
import org.apache.myfaces.trinidadinternal.taglib.core.CoreFormTag;
import org.apache.myfaces.trinidadinternal.taglib.core.layout.CorePanelHorizontalLayoutTag;
import org.apache.myfaces.trinidadinternal.taglib.core.nav.CoreCommandLinkTag;
import org.apache.myfaces.trinidadinternal.taglib.core.nav.CoreGoButtonTag;
import org.apache.myfaces.trinidadinternal.taglib.core.output.CoreImageTag;
import org.apache.myfaces.trinidadinternal.taglib.core.output.CoreOutputTextTag;
import org.apache.myfaces.trinidadinternal.taglib.core.output.CoreSpacerTag;
import org.apache.myfaces.trinidadinternal.taglib.core.output.CoreStatusIndicatorTag;
import org.apache.myfaces.trinidadinternal.taglib.html.HtmlCellFormatTag;
import org.apache.myfaces.trinidadinternal.taglib.html.HtmlRowLayoutTag;
import org.apache.myfaces.trinidadinternal.taglib.html.HtmlTableLayoutTag;
import org.jboss.seam.Component;

import utilidades.varios.MensajesCore;
import dondeando.modelo.servicio.ServicioImagen;
import dondeando.modelo.servicio.ServicioUsuario;

public class CabeceraPaginaTag extends CorePanelHorizontalLayoutTag {

    protected static final String METODO_LOGOUT                = "logout";
    protected static final String METODO_LOGIN_USUARIO         = "loginUsuario";
    protected static final String METODO_IR_MENU         	   = "irAlMenu";
    protected static final String METODO_REGISTRAR_USUARIO     = "registrarUsuario";
    protected static final String METODO_DETALLES_USUARIO      = "detallesUsuario";
    protected static final String PROPIEDAD_NOMBRE_USUARIO     = "nombreUsuario";
    
    private String botonMenu; //atributo del Tag
    private String agregarCapaEspera; //atributo del Tag
    
    private MensajesCore mensajesCore;
    private ServicioUsuario servicioUsuario;
    private ServicioImagen servicioImagen;

    public CabeceraPaginaTag() {
        super();
        super.setInlineStyle("width: 100%");
        mensajesCore = (MensajesCore)Component.getInstance(MENSAJES_CORE);
    }

    @Override
    public int doStartTag() throws JspException {
    	int res = super.doStartTag();
    	
    	//No se puede carga en el constructor porque inyecta uno de nuevo
    	//cada vez que se crear el Tag, que es siempre que se pinta la página
    	//y así se obtienen las propiedades cacheadas
    	servicioUsuario = (ServicioUsuario)Component.getInstance(SERVICIO_USUARIO);
    	servicioImagen = (ServicioImagen)Component.getInstance(SERVICIO_IMAGEN);

    	HtmlTableLayoutTag tablaPadre = crearTabla(this);
    	HtmlRowLayoutTag filasPadre = null;          
    	HtmlCellFormatTag celda = null;
    	//Se añade por defecto el statusIndicator si no se dice lo contrario
    	if(!"false".equals(agregarCapaEspera)) {
    		filasPadre = crearFila(tablaPadre);
    		celda = crearCelda(null, null, null, "2", null, filasPadre);
    		crearComponenteEspera(filasPadre);
    		fin(celda);
    		fin(filasPadre);
    	}

    	filasPadre = crearFila(tablaPadre);
    	celda = crearCelda(null, null, null, "2", null, filasPadre);
    	crearOutputCabecera(filasPadre);
    	fin(celda);
    	fin(filasPadre);

    	filasPadre = crearFila(tablaPadre);
    	//Separamos el form porque da problemas al importar la cabecera del SAS (al tener dos form
    	//anidados no funciona bien en explorer porque se hace un lío).
    	CoreFormTag form = new CoreFormTag();
    	form.setPageContext(this.pageContext);
    	form.setParent(filasPadre);
    	form.setId("formMenuSiglo");
    	form.doStartTag();
    	HtmlTableLayoutTag tabla = crearTabla(form);
    	HtmlRowLayoutTag fila = crearFila(tabla);

    	celda = crearCelda("right", null, null, null, null, fila);
//    	CoreCommandLinkTag link = crearCommandLink(null, 
//    			"#{"+MENU_PRINCIPAL_BEAN+"."+METODO_ACCION_NOTIFICACIONES+"}", 
//    			null, null, false, false,
//    			"#{"+PAGINAS_BEAN+"."+PROPIEDAD_HAY_NOTIFICACIONES+"}", celda, false);
//    	crearImagen("/imagenes/mensaje_blink.gif",null, 
//    			mensajesCore.obtenerTexto("NOTIFICACIONES_PENDIENTES"), null, link);
//    	fin(link);
    	CoreCommandLinkTag link = null;

    	if(servicioUsuario.isUsuarioActivoAnonimo()){
    		link = crearCommandLink(mensajesCore.obtenerTexto("LOGIN"),
    				"#{"+CABECERA_PAGINA_BEAN+"."+METODO_LOGIN_USUARIO+"}", null, null,false, false, null, celda, false);
    		
    		fin(link);
    		crearEspacio("5", "5", celda);
    		
    		link = crearCommandLink(mensajesCore.obtenerTexto("REGISTRARSE"),
    				"#{"+CABECERA_PAGINA_BEAN+"."+METODO_REGISTRAR_USUARIO+"}", null, null,false, false, null, celda, false);
    		fin(link);
    		
    	}
    	else{
    		//TODO: Imagen usuario
//    		crearImagen("/imagenes/usuario.gif", null, mensajesCore.obtenerTexto("USUARIO"), null, celda); 
    		crearImagen(servicioImagen.calcularUrlImagenUsuario(servicioUsuario.devolverUsuarioActivo()), "35", mensajesCore.obtenerTexto("USUARIO"), null, celda); 
    		link = crearCommandLink(" #{"+CABECERA_PAGINA_BEAN+"."+PROPIEDAD_NOMBRE_USUARIO+"} ",
    				"#{"+CABECERA_PAGINA_BEAN+"."+METODO_DETALLES_USUARIO+"}", null, null,false, false, null, celda, false);
    		fin(link);
    		crearEspacio("5", "5", celda);
    		link = crearCommandLink(mensajesCore.obtenerTexto("SALIR"),
    				"#{"+CABECERA_PAGINA_BEAN+"."+METODO_LOGOUT+"}", null, null,false, false, null, celda, false);
    		fin(link);
    	}
    	fin(celda);
    	fin(fila);

    	fin(tabla);
    	if(!"false".equals(botonMenu)){
    		tabla = crearTabla(this);
    		fila = crearFila(tabla);
    		//TODO: ¿Meter alguna imagen de fondo?
    		//celda = crearCelda(null, "1%", null, null, "background-image: url(../skins/geos2/skin_images/menuBarBackground.png);", fila);
    		celda = crearCelda(null, "1%", null, null, null, fila);
    		crearBotonMenu(celda);
    		fin(celda);
    		fin(fila);
    		fin(tabla);
    	}
    	fin(filasPadre);
    	fin(tablaPadre);

    	form.doEndTag();

    	return res;
    }

    private void crearOutputCabecera(Tag padre) throws JspException  {
    	CoreOutputTextTag output = new CoreOutputTextTag();
    	output.setParent(padre);
    	output.setPageContext(this.pageContext);
    	output.setValue("#{htmlCabecera}");
    	output.setRendered("true");
        output.setEscape("false");
        output.doStartTag();
        output.doEndTag();
    }
    
    private void crearComponenteEspera(Tag padre) throws JspException {
		CoreStatusIndicatorTag status = new CoreStatusIndicatorTag();
		status.setId("indicador");
		status.setPageContext(this.pageContext);
		status.setParent(padre);
		status.doStartTag();
			
			FacetTag facet = new FacetTag();
			facet.setName("busy");
			facet.setPageContext(this.pageContext);
			facet.setParent(status);
			facet.doStartTag();		
				VerbatimTag verbatim = new VerbatimTag() {
					@Override
					public int doAfterBody() throws JspException {
						try {
							getBodyContent().println("<div id=\"divEspera\"> <p style=\"margin-top: 60px; text-align:center; width: 100%;\">[    Cargando datos, por favor espere...    ]</p></div>");
							return super.doAfterBody();
						} catch (IOException e) {
							throw new JspException("No se ha podido crear el cuerpo para la etiqueta verbatim de la capa de espera.", e);
						}			
					}
				} ;
				verbatim.setPageContext(this.pageContext);
				verbatim.setParent(facet);
				verbatim.doStartTag();
				//Hay que "robar" el flujo de escritura de la página para que pinte lo que queremos 
				//como body de la etiqueta verbatim.
				verbatim.setBodyContent(pageContext.pushBody());	
				verbatim.doInitBody();
				verbatim.doAfterBody();
//			Es necesario hacer esto para devolver el flujo de escritura a la página. 
				pageContext.popBody();
				verbatim.doEndTag();
				verbatim.release();
				
				facet.doEndTag();
				facet.release();
 
		status.doEndTag();
		
	}

	private HtmlTableLayoutTag crearTabla(Tag padre) throws JspException {
        HtmlTableLayoutTag tabla = new HtmlTableLayoutTag();
        tabla.setWidth("100%");
        tabla.setCellPadding("0");
        tabla.setCellSpacing("0");
        
        inicio(tabla, padre);
        return tabla;
    }
    private HtmlRowLayoutTag crearFila(Tag padre) throws JspException {
        HtmlRowLayoutTag fila = new HtmlRowLayoutTag();
        inicio(fila, padre);
        return fila;
    }
    
    private CoreSpacerTag crearEspacio(String ancho, String alto, Tag padre) throws JspException{
    	CoreSpacerTag espacio = new CoreSpacerTag();
    	espacio.setHeight(alto);
    	espacio.setWidth(ancho);
    	
    	inicio(espacio, padre);
    	fin(espacio);
    	return espacio;
    }
    
    private HtmlCellFormatTag crearCelda(String halign, String ancho, String rowSpan, String colSpan, String estilo, Tag padre) throws JspException {
        HtmlCellFormatTag celda = new HtmlCellFormatTag();
        if(halign!=null)  celda.setHalign(halign);
        if(ancho!=null)   celda.setWidth(ancho);
        if(rowSpan!=null) celda.setRowSpan(rowSpan);
        if(colSpan!=null) celda.setColumnSpan(colSpan);
        if(estilo!=null)  celda.setInlineStyle(estilo);
        
        inicio(celda, padre);
        return celda;
    }
    private CoreImageTag crearImagen(String fichero, String alto, String tip, String rendered, Tag padre) throws JspException {
    	CoreImageTag imagen = new CoreImageTag();
        imagen.setSource(fichero);
        imagen.setStyleClass("imagenCabeceraPagina");
        if(alto!=null)
            imagen.setInlineStyle("height:"+alto+"px");
        if(tip!=null)
            imagen.setShortDesc(tip);
        if(rendered!=null)
            imagen.setRendered(rendered);
        
        inicio(imagen,padre);
        fin(imagen);
        return imagen;
    }
    private CoreOutputTextTag crearOutput(String valor, String rendered, Tag padre, boolean agregarStyleClass) throws JspException {
        CoreOutputTextTag output = new CoreOutputTextTag();
        output.setValue(valor);
        if(agregarStyleClass)
        	output.setStyleClass("AFLabelText");
        if(rendered!=null)
            output.setRendered(rendered);
        
        inicio(output, padre);
        fin(output);
        return output;
    }
    private CoreCommandLinkTag crearCommandLink(String texto, String accion, String actionListener,
                                                String returnListener, boolean abrirPopup, boolean partialSubmit, String rendered, Tag padre, boolean cerrarVentana) throws JspException {
        CoreCommandLinkTag link = new CoreCommandLinkTag();
        link.setText(texto);
        if(!cerrarVentana){
            link.setAction(accion);
            if(actionListener!=null)
                link.setActionListener(actionListener);
            link.setPartialSubmit(partialSubmit ? "true" : "false");
            if(returnListener!=null || abrirPopup){
                link.setUseWindow("true");
                link.setWindowHeight("300");
                link.setWindowWidth("600");
                link.setReturnListener(returnListener);
            }
        }else
            link.setOnclick("window.close();");
        if(rendered!=null)
            link.setRendered(rendered);
        link.setStyleClass("linksCabecera");
        
        inicio(link, padre);
        return link;
    }
    private CoreGoButtonTag crearBotonMenu(Tag padre) throws JspException{
        //Necesitamos un goButton con el llink explicito para que sepa volver al menu adecuado
        //aunque haya caducado la conversación
        CoreGoButtonTag boton = new CoreGoButtonTag();
        boton.setText(mensajesCore.obtenerTexto("MENU"));
        boton.setDestination(JSF_MENU_PRINCIPAL);
        boton.setAccessKey(String.valueOf(mensajesCore.obtenerTexto("MENU").charAt(0)));
        
        inicio(boton, padre);
        fin(boton);
        return boton;
    }
    
    private void inicio(UIXComponentTag elemento, Tag padre) throws JspException {
        elemento.setPageContext(this.pageContext);
        elemento.setParent(padre);
        elemento.doStartTag();
    }
    private void fin(UIXComponentTag elemento) throws JspException {
        elemento.doEndTag();
    }


    
    public String getBotonMenu() {
        return botonMenu;
    }
    public void setBotonMenu(String botonMenu) {
        this.botonMenu = botonMenu;
    }

	public String getAgregarCapaEspera() {
		return agregarCapaEspera;
	}

	public void setAgregarCapaEspera(String agregarCapaEspera) {
		this.agregarCapaEspera = agregarCapaEspera;
	}
}
