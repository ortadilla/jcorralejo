<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />


		<trh:html>
		<trh:head title="#{mensajesCat['HOMOGENEIZAR_ATRIBUTOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
			<tr:statusIndicator id="indicador">
				<f:facet name="busy">
					<f:verbatim>
							<div id="divEspera">
								<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>
							</div>
					</f:verbatim>
				</f:facet>
				<f:facet name="ready">
				</f:facet>
			</tr:statusIndicator>
			
			<tr:form onsubmit="bloquearPantalla(this);">

				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<f:facet name="actions" />
					<tr:panelBox text="#{mensajesCat['HOMOGENEIZAR_ATRIBUTOS']}" inlineStyle="width: 100%;">
						<tr:statusIndicator rendered="true">
							<f:facet name="busy">
								<tr:panelHorizontalLayout halign="center">
									<tr:outputText value="[    Cargando datos, por favor espere...    ]" inlineStyle="font-weight: bold; padding-top: 2px; padding-bottom: 2px; padding-left: 20px; padding-right: 20px; font-size: 110%;" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<f:facet name="ready">
								<tr:panelHorizontalLayout halign="center">
									<tr:outputText value="&#32;" inlineStyle="padding: 8px; font-size: 110%;" />
								</tr:panelHorizontalLayout>
							</f:facet>
						</tr:statusIndicator>
						<tr:spacer width="20" height="10" />
						<tr:panelHorizontalLayout halign="left">
							<tr:outputText value="#{mensajesCat['SELECCIONE_ATRIBUTO_MAESTRO']}" />
						</tr:panelHorizontalLayout>
						<tr:spacer width="20" height="10" />
						<tr:table rows="10" id="tablaAtributos" 
							emptyText="#{mensajesCat['TABLA_ATRIBUTOS_VACIA']}" width="100%"
							var="atributo" value="#{catalogo_homogeneizarAtributosBean.listaAtributos}" 
							rowSelection="single"
							autoSubmit="true"
							selectionListener="#{catalogo_homogeneizarAtributosBean.cambiarAtributo}">
							<tr:column sortable="true" headerText="#{mensajesCat['BLOQUE']}">
								<tr:outputText value="#{atributo.bloque.descripcion}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{mensajesCat['NOMBRE']}">
								<tr:outputText value="#{atributo.nombre}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{mensajesCat['TIPODATO']}">
								<tr:outputText value="#{atributo.tipoDato.descripcion}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{mensajesCat['UNIDADMEDIDA']}">
								<tr:outputText value="#{(atributo.unidadMedida!=null)?atributo.unidadMedida.descripcion:atributo.unidadMedida}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{mensajesCat['TEXTOAYUDA']}">
								<tr:outputText value="#{atributo.textoAyuda}" />
							</tr:column>
						</tr:table>
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{mensajesCore['ACEPTAR']}" id="Aceptar" partialTriggers="tablaAtributos"
								action="#{catalogo_homogeneizarAtributosBean.aceptar}" 
								disabled="#{catalogo_homogeneizarAtributosBean.deshabilitarAceptar}"/>
							<tr:spacer width="20" height="0" />
							<tr:commandButton text="#{mensajesCore['CANCELAR']}"
								action="#{catalogo_homogeneizarAtributosBean.cancelar}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
					<tr:spacer width="0" height="15" />
				</tr:panelPage>

			</tr:form>
			
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>