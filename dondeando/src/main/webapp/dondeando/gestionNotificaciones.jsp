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
				<tr:panelHeader text="#{resCore['GESTION_NOTIFICACIONES']}" />

				<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
					disclosed="#{gestionNotificacionesBean.desplegado}" id="showDetail"
					partialTriggers="showDetail"
					binding="#{gestionNotificacionesBinding.busqueda}">
					<tr:panelBox inlineStyle="width:100%;" background="medium"
						partialTriggers="btnLimpiar">
						<tr:panelHorizontalLayout halign="center">
							<trh:tableLayout cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['USUARIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="20" partialTriggers=":::btnLimpiar"
										value="#{gestionNotificacionesBean.criterioUsuario!=null ? gestionNotificacionesBean.criterioUsuario.login : ''}"
										disabled="true"
										id="criterioUsuario" maximumLength="20" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['LEIDA']}"
										inlineStyle="font-weight: bolder;" />
									<tr:selectOneChoice
										value="#{gestionNotificacionesBean.criterioLeidas}">
										<f:selectItems id="selectSiNo"
											value="#{gestionNotificacionesBean.selectSiNo}" />
									</tr:selectOneChoice>
								</trh:rowLayout>
							</trh:tableLayout>
						</tr:panelHorizontalLayout>
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['BUSCAR']}" id="btnBuscar"
								action="#{gestionNotificacionesBean.buscar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['LIMPIAR']}" id="btnLimpiar"
								immediate="true" action="#{gestionNotificacionesBean.limpiar}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
				</tr:showDetailHeader>

				<tr:spacer width="10" height="20" />

				<tr:panelBox inlineStyle="width:100%;" text="#{resCore['NOTIFICACIONES']}">
					<tr:panelGroupLayout>
						<tr:panelButtonBar>
							<tr:commandButton text="#{resCore['MARCAR_COMO_LEIDA']}"
								id="btnDetalles" action="#{gestionNotificacionesBean.marcar}" />
						</tr:panelButtonBar>
					</tr:panelGroupLayout>
					<tr:spacer height="10" />
					<tr:table var="var" first="0"
						emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
						value="#{gestionNotificacionesBean.listaNotificaciones}"
						rowBandingInterval="1" columnBandingInterval="0"
						selectedRowKeys="#{gestionNotificacionesBean.estadoDeSeleccionTabla}"
						rowSelection="single" id="listaNotificaciones">
						<f:facet name="actions">
							<tr:panelHorizontalLayout inlineStyle="width: 350px">
								<tr:outputText id="elementosEncontrados"
									inlineStyle="font-weight: bold"
									value="#{gestionNotificacionesBean.numeroElementosTabla}" />
							</tr:panelHorizontalLayout>
						</f:facet>
						<tr:column sortable="true" headerText="#{resCore['MENSAJE']}">
							<tr:outputText value="#{var.mensaje}" />
						</tr:column>
						<tr:column sortable="true" headerText="#{resCore['TIPO_NOTIFICACION']}">
							<tr:outputText value="#{var.tipoInteres.descripcion}" />
						</tr:column>
						<tr:column sortable="true"
							headerText="#{resCore['FECHA']}">
							<tr:outputText value="#{var.fecha}" />
						</tr:column>
						<tr:column sortable="false" headerText="#{resCore['LEIDA']}">
							<tr:image
								shortDesc="#{var.leida ? resCore['SI'] : resCore['NO']}"
								source="#{var.leida ? '/imagenes/dondeando/check.png':'/imagenes/dondeando/cruz.png'}"
								inlineStyle="height: 20px;" />
						</tr:column>
					</tr:table>

					</tr:panelBox>
			</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
