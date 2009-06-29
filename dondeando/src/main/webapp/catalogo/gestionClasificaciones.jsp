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
		<f:loadBundle basename="mensajesCore" var="resCor" />

		<trh:html>
		<trh:head title="#{resCat['SELECCION_CLASIFICACION']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina/>
			<h:form enctype="application/x-www-form-urlencoded">

				<tr:messages />

				<tr:panelBox id="panelPage" text="#{resCat['SELECCION_CLASIFICACION']}"
					rendered="#{catalogo_gestionClasificaciones.renderizar}"
					inlineStyle="width: 100%;">

					<tr:treeTable emptyText="#{resCor['NO_ELEMENTOS']}"
						binding="#{catalogo_gestionClasificacionesBinding.treeTable1}" var="fila" id="treeTable1"
						value="#{catalogo_gestionClasificaciones.valorArbol}"
						rendered="#{catalogo_gestionClasificaciones.renderizar}"
						expandAllEnabled="false"
						selectionListener="#{catalogo_gestionClasificaciones.seleccionArbol}"
						disclosureListener="#{catalogo_gestionClasificaciones.seleccionNodo}"
						partialTriggers="treeTable1" width="100%">

						<f:facet name="nodeStamp">
							<tr:column sortable="false" headerText="#{resCat['NIVELES']}">
								<tr:outputText value="#{fila.descripcion}" inlineStyle="color:#{fila.color}" /> 
							</tr:column>
						</f:facet>

						<f:facet name="header">
							<tr:panelHorizontalLayout halign="right">
								<tr:menuTabs binding="#{catalogo_gestionClasificacionesBinding.menuPestanas}" startDepth="10"
									id="menuTabs1" partialTriggers="treeTable1 menuTabs1">
									<tr:forEach var="item"
										items="#{catalogo_gestionClasificaciones.valoresMenu}">
										<tr:commandMenuItem text="#{item.descripcion}"
											rendered="#{catalogo_gestionClasificaciones.renderizar}"
											selected="#{item.seleccionado}" immediate="true"
											actionListener="#{catalogo_gestionClasificaciones.seleccionable}">
											<tr:outputText value="#{item.identificador}" rendered="false" />
										</tr:commandMenuItem>
									</tr:forEach>
								</tr:menuTabs>
							</tr:panelHorizontalLayout>
						</f:facet>

						<f:facet name="footer">
							<tr:panelHorizontalLayout id="panelGroup1" halign="right">
								<tr:commandButton text="#{resCor['ACEPTAR']}" id="btnAceptar"
									action="#{catalogo_gestionClasificaciones.aceptar}" />
								<tr:spacer width="10" />
								<tr:commandButton text="#{resCor['CANCELAR']}" id="btnCancelar"
									action="#{catalogo_gestionClasificaciones.cancelar }" />
							</tr:panelHorizontalLayout>
						</f:facet>

					</tr:treeTable>

				</tr:panelBox>

			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
