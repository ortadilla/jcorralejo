<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<trh:html>
		<trh:head title="#{resCat['SELECCIONAR_CLASIFICACIONES']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina/>
			<tr:panelHorizontalLayout halign="center">
			<tr:panelForm id="id7">
				<tr:panelPage title="#{resCat['SELECCIONAR_CLASIFICACIONES']}">
					<tr:panelBox>
					<tr:form onsubmit="bloquearPantalla(this);">
					
						<tr:table emptyText="No existen clasificaciones asociadas al artículo" 
							rowBandingInterval="1"
							columnBandingInterval="0"
    		                var="var" rows="10"
        		            binding="#{catalogo_eliminarClasificacionesBinding.tabla}"
            		        id="tabla"
                		    value="#{catalogo_eliminarClasificacionesBean.articulosClasificacionDelArticulo}"
                  			immediate="false"
                  			rowSelection="multiple">
<!--	    			        	<f:facet name="selection" >-->
<!--    	            				<tr:tableSelectMany/>-->
<!--				              	</f:facet>-->
				              	
				              	<tr:column sortable="false" headerText="Clasificación" >
					                <tr:outputText value="#{var.nivelValor.descripcion}"/>
				              	</tr:column>
                			
                				<f:facet name="actions">
					                <tr:panelHorizontalLayout>
                  						<tr:commandButton text="Aceptar"
		                                    action="#{catalogo_eliminarClasificacionesBean.aceptar}"/>
                					  	<tr:spacer width="10" height="10"/>
						                <tr:commandButton text="Cancelar"
							                action="#{catalogo_eliminarClasificacionesBean.cancelar}"/>
					                </tr:panelHorizontalLayout>
              					</f:facet>
			            </tr:table>
					
					</tr:form>
					</tr:panelBox>
				</tr:panelPage>
			</tr:panelForm>
			  </tr:panelHorizontalLayout>
		  <tr:panelHorizontalLayout inlineStyle="width: 100%;">
			<tr:outputText escape="false" value="#{htmlPie}"/>
  		  </tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>