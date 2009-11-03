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

		<trh:body initialFocusId="nombre">

			<geos:cabeceraPagina />

			<tr:form defaultCommand="btnAceptar" id="formCrearUsuario"
				usesUpload="true">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{editarConfiguracionNotificacionesBean.tituloPagina}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['RECIBIR_NOTIFICACIONES']}" inlineStyle="width:100%;">
							<tr:spacer height="20" />
							<trh:tableLayout
								partialTriggers="tipoNotificacion"
								inlineStyle="border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TIPO_NOTIFICACION']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:selectOneChoice value="#{editarConfiguracionNotificacionesBean.tipoNotificacion}"
										valueChangeListener="#{editarConfiguracionNotificacionesBean.cambioTipoNotificacion}"
										autoSubmit="true" id="tipoNotificacion">
										<f:selectItems id="selectTipo"
											value="#{editarConfiguracionNotificacionesBean.selectTipoNotificacion}" />
									</tr:selectOneChoice>
								</trh:rowLayout>
								<trh:rowLayout rendered="#{editarConfiguracionNotificacionesBean.mostrarProvincias}">
									<tr:outputText value="#{resCore['PROVINCIA']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:selectOneChoice value="#{editarConfiguracionNotificacionesBean.provincia}">
										<f:selectItems id="selectProvincias"
											value="#{editarConfiguracionNotificacionesBean.selectProvincias}" />
									</tr:selectOneChoice>
								</trh:rowLayout>
								<trh:rowLayout rendered="#{editarConfiguracionNotificacionesBean.mostrarBusquedaLocal}">
									<tr:outputText value="#{resCore['LOCAL']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="50" value="#{editarConfiguracionNotificacionesBean.local!=null ? editarConfiguracionNotificacionesBean.local.nombre : ''}"
										id="local" disabled="true"/>
									<tr:commandLink	action="#{editarConfiguracionNotificacionesBean.buscarLocal}">
										<tr:image shortDesc="#{resCore['SELECCIONAR_LOCAL']}"
											source="#{'/imagenes/dondeando/buscar.png'}"
											inlineStyle="height: 20px;" />
									</tr:commandLink>
									<tr:commandLink	action="#{editarConfiguracionNotificacionesBean.eliminarLocal}">
										<tr:image shortDesc="#{resCore['BORRAR_LOCAL']}"
											source="#{'/imagenes/dondeando/borrar.png'}"
											inlineStyle="height: 20px;" />
									</tr:commandLink>
								</trh:rowLayout>
								<trh:rowLayout rendered="#{editarConfiguracionNotificacionesBean.mostrarForos}">
									<tr:outputText value="#{resCore['FORO_0']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:selectOneChoice value="#{editarConfiguracionNotificacionesBean.foro}">
										<f:selectItems id="selectForos"
											value="#{editarConfiguracionNotificacionesBean.selectForos}" />
									</tr:selectOneChoice>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['AVISAR_EMAIL']} *"	inlineStyle="font-weight: bolder;" />
									<tr:selectOneChoice
										value="#{editarConfiguracionNotificacionesBean.enviarEmail}">
										<f:selectItems id="selectSiNo"
											value="#{editarConfiguracionNotificacionesBean.selectSiNo}" />
									</tr:selectOneChoice>
								</trh:rowLayout>
							</trh:tableLayout>
							<tr:spacer width="20" height="20" />

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{editarConfiguracionNotificacionesBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{editarConfiguracionNotificacionesBean.cancelar}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
