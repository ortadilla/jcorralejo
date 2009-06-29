<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/strict.dtd"
		doctype-public="-//W3C//DTD HTML 4.01//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<trh:html>
		<trh:head title="#{mensajesCat['MOSTRAR_HISTORICO_ARTICULOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
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
				<tr:panelHeader text="#{catalogo_mostrarHistoricoArticulosBean.titulo}" />
				<tr:panelBox background="medium" inlineStyle="width: 100%;">
					<tr:table rowBandingInterval="1" columnBandingInterval="0"
						emptyText="#{mensajesCore['NO_ELEMENTOS']}" rows="10" width="100%"
						var="historico" id="tablaHistorico"
						value="#{catalogo_mostrarHistoricoArticulosBean.historicoArticulo}">
						<tr:column sortable="true" sortProperty="fechaModif"
							headerText="#{mensajesCat['FECHAMODIFICACION']}" align="center">
							<tr:outputText value="#{historico.fechaModif}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="usuarioModif"
							headerText="#{mensajesCat['USUARIOMODIF']}" align="center">
							<tr:outputText value="#{historico.usuarioModif}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="atributoModificado"
							headerText="#{mensajesCat['ATRIBUTO_MODIFICADO']}" align="center">
							<tr:outputText value="#{historico.atributoModificado}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="valorAnterior"
							headerText="#{mensajesCat['VALOR_ANTERIOR']}" align="center">
							<tr:outputText value="#{historico.valorAnterior}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="valorNuevo"
							headerText="#{mensajesCat['VALOR_NUEVO']}" align="center">
							<tr:outputText value="#{historico.valorNuevo}" />
						</tr:column>
					</tr:table>
				</tr:panelBox>
				<tr:panelButtonBar halign="end">
					<tr:commandButton text="#{mensajesCore['CERRAR']}" id="botonCerrar"
						action="#{catalogo_mostrarHistoricoArticulosBean.cerrar}" />
				</tr:panelButtonBar>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>