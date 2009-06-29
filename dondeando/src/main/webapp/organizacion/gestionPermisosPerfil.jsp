<?xml version="1.0" encoding="windows-1252" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
	
	<f:view>
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<trh:html >	 
			<trh:head title="#{resOrg['GESTION_PERMISOPERFIL']}">
				<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-15"/>
        		<trh:script source="/include/libreriaGEOS.js">
	        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
				</trh:script>
			</trh:head> 
			<trh:body>
			<geos:cabeceraPagina/>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<tr:panelHeader  text="#{resOrg['GESTION_PERMISOPERFIL']}"/>   
						<tr:commandButton 
								id = "botonVolver"
								text = "Volver" 
								action="#{organizacion_gestionPermisosPerfilBean.volver}" />         
						<f:facet name="messages">
							<tr:messages />
						</f:facet>

						<tr:spacer width="10" height="20" />
						<tr:panelBox text="#{resOrg['PERMISOSPERFIL']} #{organizacion_gestionPermisosPerfilBean.nombrePerfil}"
									background="medium"
									inlineStyle="width: 100%;">
							<tr:spacer height="15" width="0"/>

							<tr:commandButton id="botonAgregar" text="#{resCor['AGREGAR']}" action="#{organizacion_gestionPermisosPerfilBean.agregar}"/>
							<tr:commandButton id="botonEditar"  text="#{resCor['EDITAR']}"  action="#{organizacion_gestionPermisosPerfilBean.editar}"/>
							<tr:commandButton id="botonBorrar"  text="#{resCor['BORRAR']}"  action="#{organizacion_gestionPermisosPerfilBean.borrar}"
							                                    onclick="return confirm('#{resCor['CONFIRMAR_BORRAR']}')"/>

							<tr:spacer width="10" height="20"/>
				
							<tr:table emptyText="#{resCor['NO_ELEMENTOS']}" 
								rowBandingInterval="1"
				                var="var" rows="0"
				                id="tablaResultados"
				                value="#{organizacion_gestionPermisosPerfilBean.listaResultados}"
				                binding= "#{organizacion_gestionPermisosPerfilBinding.tablaResultados}"
				                partialTriggers= "botonEditar" 
				                immediate="false"
				                rowSelection="multiple" width="100%"> 
				                      			  
						    	<tr:column sortable="false" headerText="#{resOrg['ENTIDAD']}">
						        	<tr:outputText value="#{var.entidad}"/>
						        </tr:column>
						              	
								<tr:column sortable = "false" headerText="#{resOrg['PERMISO']}">
									<tr:outputText value="#{var.permiso}"/>
								</tr:column>
						              
						        <tr:column sortable="false" headerText="#{resOrg['OBJETO']}">
							    	<tr:outputText value="#{var.objeto}"  />
						        </tr:column>
						              
						        <tr:column sortable="false" headerText="#{resOrg['PERMITIR']}" align="center">
						            <tr:image source="#{var.permitir ? '/imagenes/aceptar_16.png' : '/imagenes/cancelar_16.png'}"
						                      shortDesc="#{var.permitir ? 'Hay Permiso' : 'No hay Permiso'}" />
						        </tr:column>
	            			</tr:table>
            			</tr:panelBox>
            			
            			<tr:spacer width="10" height="20" />
						<tr:panelBox text="#{resOrg['PERMISOSPERFIL_PADRES']}"
									background="medium"
									inlineStyle="width: 100%;">
							<tr:spacer height="15" width="0"/>
            			
	            			<tr:table emptyText="#{resCor['NO_ELEMENTOS']}" 
								rowBandingInterval="1"
								columnBandingInterval="0"
				                var="var" rows="0"
				                id="tablaResultadosPadres"
				                value="#{organizacion_gestionPermisosPerfilBean.listaResultadosPadres}"
				                binding= "#{organizacion_gestionPermisosPerfilBinding.tablaResultadosPadres}"
				                partialTriggers= "botonEditar" 
				                immediate="false"
				                rowSelection="multiple" width="100%"> 
				                      		
				                <tr:column sortable="false" headerText="#{resOrg['PERFIL']}">
						        	<tr:outputText value="#{var.perfil}"/>
						        </tr:column>
						              			  
						    	<tr:column sortable="false" headerText="#{resOrg['ENTIDAD']}">
						        	<tr:outputText value="#{var.entidad}"/>
						        </tr:column>
						              	
								<tr:column sortable="false" headerText="#{resOrg['PERMISO']}">
									<tr:outputText value="#{var.permiso}"/>
								</tr:column>
						              
						        <tr:column sortable="false" headerText="#{resOrg['OBJETO']}">
							    	<tr:outputText value="#{var.objeto}"  />
						        </tr:column>
						              
						        <tr:column sortable="false" headerText="#{resOrg['PERMITIR']}" align="center">
						            <tr:image source="#{var.permitir ? '/imagenes/aceptar_16.png' : '/imagenes/cancelar_16.png'}"
						                      shortDesc="#{var.permitir ? 'Hay Permiso' : 'No hay Permiso'}" />
						        </tr:column>
	            			</tr:table>
	            		</tr:panelBox>	           	 		
	            	</tr:panelPage>
	           </tr:form>			
			</trh:body>
		</trh:html>
	</f:view>
</jsp:root>