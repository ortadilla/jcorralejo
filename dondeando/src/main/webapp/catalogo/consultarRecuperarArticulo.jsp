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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<trh:html>
		<trh:head title="#{resCat['TITULO_CONFIRMAR_RECUPERACION_ARTICULO']}">
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
						<p style="margin-top: 60px; text-align:center; width: 100%;">[
						Cargando datos, por favor espere... ]</p>
						</div>
					</f:verbatim>
				</f:facet>
				<f:facet name="ready">
				</f:facet>
			</tr:statusIndicator>
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:messages />
				<tr:panelHeader text="#{resCat['OPCION_RECUPERACION']}" />
				<tr:panelBox inlineStyle="width: 100%;">
					<tr:panelHorizontalLayout
						rendered="#{catalogo_consultarRecuperarArticuloBean.mostrarRecuperarReal}">
						<tr:selectBooleanCheckbox id="recuperarReal"
							value="#{catalogo_consultarRecuperarArticuloBean.recuperarReal}"
							autoSubmit="true" immediate="false" />
						<tr:outputText value="#{resCat['RECUPERAR_VALORES_REAL']}" />
					</tr:panelHorizontalLayout>
					<tr:outputText
						rendered="#{!catalogo_consultarRecuperarArticuloBean.mostrarRecuperarReal}"
						value="#{catalogo_consultarRecuperarArticuloBean.mensajeInformacion}" />
					<tr:spacer width="30" height="30" />
					<tr:panelButtonBar halign="center">
						<tr:commandButton text="#{resCore['CONTINUAR']}"
							action="#{catalogo_consultarRecuperarArticuloBean.continuar}" />
						<tr:commandButton text="#{resCore['CANCELAR']}"
							action="#{catalogo_consultarRecuperarArticuloBean.cancelar}" />
					</tr:panelButtonBar>
				</tr:panelBox>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
