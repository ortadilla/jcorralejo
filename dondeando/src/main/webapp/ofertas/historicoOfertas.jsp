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
		<f:loadBundle basename="mensajesOfe" var="mensajesOfe" />
		<trh:html>
		<trh:head title="#{mensajesOfe['MOSTRAR_HISTORICO_OFERTAS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<h:form>
				<tr:panelHeader text="#{ofertas_mostrarHistoricoOfertasBean.titulo}" />
				<tr:panelBox background="medium" inlineStyle="width: 100%;">
					<tr:table rowBandingInterval="1" columnBandingInterval="0"
						emptyText="#{mensajesCore['NO_ELEMENTOS']}" rows="10" width="100%"
						var="historico" id="tablaHistorico"
						value="#{ofertas_mostrarHistoricoOfertasBean.historicoOferta}">
						<tr:column sortable="true" sortProperty="fechaModif"
							headerText="#{mensajesOfe['MG_FECHAMODIFICACION']}" align="center">
							<tr:outputText value="#{historico.fechaModif}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="usuarioModif"
							headerText="#{mensajesOfe['USUARIOMODIF']}" align="center">
							<tr:outputText value="#{historico.usuarioModif}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="atributoModificado"
							headerText="#{mensajesOfe['ATRIBUTO_MODIFICADO']}" align="center">
							<tr:outputText value="#{historico.atributoModificado}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="valorAnterior"
							headerText="#{mensajesOfe['VALOR_ANTERIOR']}" align="center">
							<tr:outputText value="#{historico.valorAnterior}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="valorNuevo"
							headerText="#{mensajesOfe['VALOR_NUEVO']}" align="center">
							<tr:outputText value="#{historico.valorNuevo}" />
						</tr:column>
					</tr:table>
				</tr:panelBox>
				<tr:panelButtonBar halign="end">
					<tr:commandButton text="#{mensajesCore['CERRAR']}" id="botonCerrar"
						action="#{ofertas_mostrarHistoricoOfertasBean.cerrar}" />
				</tr:panelButtonBar>
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
