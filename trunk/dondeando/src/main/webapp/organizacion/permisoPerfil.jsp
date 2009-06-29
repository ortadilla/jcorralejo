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
			<trh:html>
			<trh:head title="#{resOrg['PERMISOSPERFIL']}">
				<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
				<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
				</trh:script>
			</trh:head>
			<trh:body>
				<geos:cabeceraPagina/>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<tr:panelHeader text="#{organizacion_gestionPermisosPerfilBean.nombrePerfil}"/>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelBox inlineStyle="width: 100%;">
						<trh:tableLayout borderWidth="0" cellSpacing="10" halign="center">						
							<trh:rowLayout halign="left">
								<trh:cellFormat>
									<tr:outputText value="#{resOrg['ENTIDAD']}"/>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectOneChoice id ="selectEntidad"
										value = "#{organizacion_permisoPerfilBean.entidadId}"
										autoSubmit="true" unselectedLabel="" 
										valueChangeListener="#{organizacion_permisoPerfilBean.cambiarEntidad}" 
										disabled="#{organizacion_permisoPerfilBean.modificar}">
											<f:selectItems id="selectItems1" 
												value="#{organizacion_permisoPerfilBean.listaEntidades}"/>
									</tr:selectOneChoice>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout halign="left">
								<trh:cellFormat>
									<tr:outputText value="#{resOrg['PERMISO']}"/>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectOneChoice id="selectPermiso" 
										value="#{organizacion_permisoPerfilBean.permisoId}"
										autoSubmit="true" unselectedLabel="" 
										partialTriggers="selectEntidad" 
										valueChangeListener="#{organizacion_permisoPerfilBean.cambiarPermiso}"
										disabled="#{organizacion_permisoPerfilBean.modificar}">
										<f:selectItems id = "selectItems2" 
										 value = "#{organizacion_permisoPerfilBean.listaPermisos}"/>
									</tr:selectOneChoice>
								</trh:cellFormat>	
							</trh:rowLayout>
							<trh:rowLayout halign="left">
								<trh:cellFormat>
									<tr:outputText value="#{resOrg['OBJETO']}"/>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:panelHorizontalLayout partialTriggers="selectPermiso">
										<tr:selectOneChoice value="#{organizacion_permisoPerfilBean.claveNegObj}"
											                unselectedLabel=""
											                rendered="#{!organizacion_permisoPerfilBean.objRequiereBusqueda}">
											<f:selectItems value="#{organizacion_permisoPerfilBean.listaObjetos}"/>
										</tr:selectOneChoice>
										<tr:inputText value="#{organizacion_permisoPerfilBean.objBuscado.descripcion}"
										              disabled="true" columns="60"
										              rendered="#{organizacion_permisoPerfilBean.objRequiereBusqueda}"/>
										<tr:commandLink shortDesc="#{resCore['BUSCAR']}"
									                    action="#{organizacion_permisoPerfilBean.accionBuscarObj}"
									                    rendered="#{organizacion_permisoPerfilBean.objRequiereBusqueda}">
									    	<tr:image source="/imagenes/lupa3.gif"/>
									    </tr:commandLink>
									</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout halign="left">
								<trh:cellFormat>
									<tr:outputText value="#{resOrg['PERMITIR']}"/>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectBooleanCheckbox value="#{organizacion_permisoPerfilBean.permitir}"/>
								</trh:cellFormat>
							</trh:rowLayout>
						</trh:tableLayout>
						 
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCor['ACEPTAR']}"
								action="#{organizacion_permisoPerfilBean.aceptar}" />
							<tr:spacer height="15" width="10" />
							<tr:commandButton text="#{resCor['CANCELAR']}"
								action="#{organizacion_permisoPerfilBean.cancelar}" />
							<tr:spacer height="15" width="10" />
						</tr:panelHorizontalLayout>	
						</tr:panelBox>
					</tr:panelPage>
				</tr:form>
			</trh:body>
			</trh:html>						
	  </f:view>
  </jsp:root>