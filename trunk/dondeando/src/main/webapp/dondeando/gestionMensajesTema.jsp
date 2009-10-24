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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<trh:html>

		<trh:head>
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>

		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="btnBuscar">
			<tr:panelPage>
				<tr:messages />
				<tr:panelHeader text="#{gestionMensajesTemaBean.tituloPagina}" />

				<tr:panelBox inlineStyle="width:100%;" text="#{resCore['MENSAJES_TEMA']}">
					<tr:panelGroupLayout>
						<tr:panelButtonBar>
							<tr:commandButton text="#{resCore['NUEVA_RESPUESTA']}"
								id="btnAgregar" action="#{gestionMensajesTemaBean.agregar}" />
							<tr:commandButton text="#{resCore['EDITAR_RESPUESTA']}"
								id="btnEditar" action="#{gestionMensajesTemaBean.editar}" />
							<tr:commandButton text="#{resCore['ELIMINAR_RESPUESTA']}"
								id="btnEliminar" action="#{gestionMensajesTemaBean.eliminar}"
								onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_TEMA']}')"
								rendered="#{gestionMensajesTemaBean.mostrarEliminarRespuesta}" />
						</tr:panelButtonBar>
					</tr:panelGroupLayout>
					<tr:spacer height="10" />
						<tr:table var="var" first="0"
							emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
							value="#{gestionMensajesTemaBean.listaMensajesTema}" rowBandingInterval="1"
							columnBandingInterval="0"
							selectedRowKeys="#{gestionMensajesTemaBean.estadoDeSeleccionTabla}"
							rowSelection="single" id="listaForos">
							<f:facet name="actions">
								<tr:panelHorizontalLayout inlineStyle="width: 350px">
									<tr:outputText id="elementosEncontrados"
										inlineStyle="font-weight: bold"
										value="#{gestionMensajesTemaBean.numeroElementosTabla}" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:column sortable="true" headerText="#{resCore['MENSAJE']}">
								<tr:outputText value="#{var.mensaje}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['AUTOR']}">
								<tr:outputText value="#{var.autorYFecha}" />
							</tr:column>
						</tr:table>
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['VOLVER']}" id="btnVolver"
								action="#{gestionMensajesTemaBean.volver}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
			</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
