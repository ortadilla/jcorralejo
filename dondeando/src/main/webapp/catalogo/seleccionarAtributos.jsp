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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resCat['SELECCIONAR_LOS_ATRIBUTOS_A_USAR']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>					
					<tr:panelHeader
						text="#{resCat['ATRIBUTO_ASOCIADOS_A_CLASIFICACION']}" />
					<tr:panelBox inlineStyle="width:100%;"
						rendered="#{catalogo_seleccionarAtributosBean.pintarTablaSeleccionar}">		
						<tr:panelGroupLayout layout="vertical" inlineStyle="width:100%;">
							<tr:panelHorizontalLayout halign="center">
								<tr:outputText value="#{resCat['CODIGO_COLOR_ROJO']}" inlineStyle="color:#FF0000;"></tr:outputText>
								<tr:spacer width="10"></tr:spacer>
								<tr:outputText value="#{resCat['ATRIBUTOS_DC']}"></tr:outputText>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10"></tr:spacer>	
							<tr:form onsubmit="bloquearPantalla(this);">					
								<tr:panelHorizontalLayout halign="center">
									<tr:table
										emptyText="No existen atributos seleccionables para las clasificaciones elegidas"
										rowBandingInterval="1" columnBandingInterval="0" var="var"
										rows="10"
										id="tablaAtributosSeleccionados"
										value="#{catalogo_seleccionarAtributosBean.listaAtributoSeleccionadoVista}"
										immediate="false" rowSelection="multiple"
										selectedRowKeys="#{catalogo_seleccionarAtributosBean.selectedRowKeys}">
										<!--rowSelection="#{var.habilitado ? 'multiple' : 'single'}">-->
										<tr:column sortable="false" headerText="Seleccionar Atributos">
											<tr:outputText value="#{var.nombre}" inlineStyle="#{var.color}" />
										</tr:column>							
										<f:facet name="actions">
											<tr:panelHorizontalLayout>
												<tr:commandButton text="#{resCore['ACEPTAR']}"
													action="#{catalogo_seleccionarAtributosBean.aceptar}"
													rendered="#{catalogo_seleccionarAtributosBean.renderizarBotonAceptar}" />
												<tr:spacer width="10" height="10" />
												<tr:commandButton text="#{resCore['CANCELAR']}"
													action="#{catalogo_seleccionarAtributosBean.cancelar}"
													rendered="#{catalogo_seleccionarAtributosBean.renderizarBotonCancelar}" />
												<tr:spacer width="10" height="10" />
												<tr:commandButton text="#{resCore['VOLVER']}"
													action="#{catalogo_seleccionarAtributosBean.cancelar}"
													rendered="#{catalogo_seleccionarAtributosBean.renderizarBotonVolver}" />
											</tr:panelHorizontalLayout>
										</f:facet>
									</tr:table>
								</tr:panelHorizontalLayout>
							</tr:form>						
						</tr:panelGroupLayout>						
					</tr:panelBox>
					<!--Cuando no se permita la selección de atributos -->
					<tr:panelBox inlineStyle="width:100%;"
						rendered="#{!catalogo_seleccionarAtributosBean.pintarTablaSeleccionar}">
						<tr:form>
						<tr:panelHorizontalLayout halign="center">
							<tr:table
								emptyText="No existen atributos seleccionables para las clasificaciones elegidas"
								rowBandingInterval="1" columnBandingInterval="0" var="var"
								rows="10" id="tablaAtributosSeleccionados2"
								value="#{catalogo_seleccionarAtributosBean.listaAtributoSeleccionadoVista}"
								immediate="false">
								<tr:column sortable="false" headerText="Lista de atributos">
									<tr:outputText value="#{var.nombre}" />
								</tr:column>
								<f:facet name="actions">
									<tr:panelHorizontalLayout>
										<tr:commandButton text="#{resCat['SIGUIENTE']}"
											action="#{catalogo_seleccionarAtributosBean.siguiente}" />
										<tr:spacer width="10" height="10" />
										<tr:commandButton text="#{resCore['CANCELAR']}"
											action="#{catalogo_seleccionarAtributosBean.cancelar}" />
									</tr:panelHorizontalLayout>
								</f:facet>
							</tr:table>
						</tr:panelHorizontalLayout>
						</tr:form>
				</tr:panelBox>
			</tr:panelPage>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
