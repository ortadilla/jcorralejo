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
								rendered="#{gestionMensajesTemaBean.mostrarAgregar}"
								id="btnAgregar" action="#{gestionMensajesTemaBean.agregar}" />
							<tr:commandButton text="#{resCore['EDITAR_RESPUESTA']}"
								rendered="#{gestionMensajesTemaBean.mostrarAgregar}"
								id="btnEditar" action="#{gestionMensajesTemaBean.editar}" />
							<tr:commandButton text="#{resCore['ELIMINAR_RESPUESTA']}"
								id="btnEliminar" action="#{gestionMensajesTemaBean.eliminar}"
								onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_TEMA']}')"
								rendered="#{gestionMensajesTemaBean.mostrarAgregar}" />
						</tr:panelButtonBar>
					</tr:panelGroupLayout>
					<tr:spacer height="10" />
						<tr:table var="var" first="0"
							emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
							value="#{gestionMensajesTemaBean.listaMensajesTema}" rowBandingInterval="1"
							columnBandingInterval="0" partialTriggers=":btnEliminar"
							selectedRowKeys="#{gestionMensajesTemaBean.estadoDeSeleccionTabla}"
							rowSelection="single" id="listaForos">
							<f:facet name="actions">
								<tr:panelHorizontalLayout inlineStyle="width: 350px">
									<tr:outputText id="elementosEncontrados"
										inlineStyle="font-weight: bold"
										value="#{gestionMensajesTemaBean.numeroElementosTabla}" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:column headerText="#{resCore['VALORACION']}" sortable="false" inlineStyle="vertical-align: middle;">
								<tr:panelHorizontalLayout >
									<tr:outputText value="#{var.valoracionUsuarios}"   
									inlineStyle="#{var.valoracionPositiva ? 'font-weight: bolder; font-size: 150%; color: green;' : 'font-weight: bolder; font-size: 150%; color: red;'}"/>
									<tr:spacer width="15"/>
									<tr:commandLink rendered="#{gestionMensajesTemaBean.mostrarAgregar}"
										actionListener="#{gestionMensajesTemaBean.accionListenerAFavor}">
										<tr:attribute name="idMensaje" value="#{var.id}" />
										<tr:image source="/imagenes/dondeando/mas.png"
												shortDesc="#{resCore['A_FAVOR']}" 
												inlineStyle="height: 20px;"/>
									</tr:commandLink>
									<tr:spacer width="10"/>
									<tr:commandLink rendered="#{gestionMensajesTemaBean.mostrarAgregar}"
										actionListener="#{gestionMensajesTemaBean.accionListenerEnContra}">
										<tr:attribute name="idMensaje" value="#{var.id}" />
										<tr:image source="/imagenes/dondeando/menos.png"
												shortDesc="#{resCore['EN_CONTRA']}" 
												inlineStyle="height: 20px;"/>
									</tr:commandLink>
								</tr:panelHorizontalLayout>
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['MENSAJE']}" width="70%">
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
