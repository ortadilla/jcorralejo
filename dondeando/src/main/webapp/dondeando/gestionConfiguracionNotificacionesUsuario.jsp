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
				<tr:panelHeader text="#{gestionConfiguracionNotificacionesUsuarioBean.tituloPagina}" />

				<tr:panelBox inlineStyle="width:100%;" text="#{resCore['CONFIGURACION']}">
					<tr:panelGroupLayout>
						<tr:panelButtonBar>
							<tr:commandButton text="#{resCore['ANIADIR']}"
								id="btnAgregar" action="#{gestionConfiguracionNotificacionesUsuarioBean.agregar}" />
							<tr:commandButton text="#{resCore['ELIMINAR']}"
								id="btnEliminar" action="#{gestionConfiguracionNotificacionesUsuarioBean.eliminar}"
								onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_CONFIGURACION_NOTIFICACION']}')"/>
						</tr:panelButtonBar>
					</tr:panelGroupLayout>
					<tr:spacer height="10" />
						<tr:table var="var" first="0"
							emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
							value="#{gestionConfiguracionNotificacionesUsuarioBean.listaInteres}" rowBandingInterval="1"
							columnBandingInterval="0" partialTriggers=":btnEliminar"
							selectedRowKeys="#{gestionConfiguracionNotificacionesUsuarioBean.estadoDeSeleccionTabla}"
							rowSelection="single" id="listaInteres">
							<f:facet name="actions">
								<tr:panelHorizontalLayout inlineStyle="width: 350px">
									<tr:outputText id="elementosEncontrados"
										inlineStyle="font-weight: bold"
										value="#{gestionConfiguracionNotificacionesUsuarioBean.numeroElementosTabla}" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:column sortable="true" headerText="#{resCore['ALERTA']}">
								<tr:outputText value="#{var.tipoInteres.descripcion}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['ELEMENTO']}">
								<tr:outputText value="#{var.descripcionObjeto}" />
							</tr:column>
							<tr:column sortable="false" headerText="#{resCore['AVISAR_EMAIL']}">
								<tr:image
									shortDesc="#{var.enviarEmail ? resCore['SI'] : resCore['NO']}"
									source="#{var.enviarEmail ? '/imagenes/dondeando/check.png':'/imagenes/dondeando/cruz.png'}"
									inlineStyle="height: 20px;" />
							</tr:column>
						</tr:table>
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['VOLVER']}" id="btnVolver"
								action="#{gestionConfiguracionNotificacionesUsuarioBean.volver}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
			</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
