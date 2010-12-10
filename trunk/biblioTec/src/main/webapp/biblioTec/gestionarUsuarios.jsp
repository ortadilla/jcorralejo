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

		<trh:head title="#{resCore['GESTION_USUARIOS']}" />

		<trh:body initialFocusId="user">

			<tr:form defaultCommand="btnBuscar">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{resCore['GESTION_USUARIOS']}" />

					<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
						disclosed="#{gestionUsuariosBean.desplegado}" id="showDetail"
						partialTriggers="showDetail"
						binding="#{gestionUsuariosBinding.busqueda}">
						<tr:panelBox inlineStyle="width:100%;" background="medium"
							partialTriggers="btnLimpiar">
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="5" cellPadding="0">
									<trh:rowLayout>
										<tr:outputText value="#{resCore['USUARIO']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText columns="20" partialTriggers=":::btnLimpiar"
											value="#{gestionUsuariosBean.criterioUsuario}"
											id="criterioUsuario" maximumLength="50" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['PERFIL']}"
											inlineStyle="font-weight: bolder;" />
										<trh:cellFormat>
											<tr:selectOneChoice
												value="#{gestionUsuariosBean.criterioPerfil}">
												<f:selectItems id="selectPefil"
													value="#{gestionUsuariosBean.selectPerfil}" />
											</tr:selectOneChoice>
										</trh:cellFormat>
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />

							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['BUSCAR']}" id="btnBuscar"
									action="#{gestionUsuariosBean.buscar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['LIMPIAR']}" id="btnLimpiar"
									immediate="true" action="#{gestionUsuariosBean.limpiar}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:showDetailHeader>

					<tr:spacer width="10" height="20" />

					<tr:panelBox inlineStyle="width:100%;"
						text="#{resCore['USUARIOS']}">
						<tr:panelGroupLayout>
							<tr:panelButtonBar>
								<tr:commandButton text="#{resCore['AGREGAR_USUARIO']}"
									disabled="#{gestionUsuariosBean.buscando}"
									id="btnAgregar" action="#{gestionUsuariosBean.agregar}" />
								<tr:commandButton text="#{resCore['DETALLES_USUARIO']}"
									disabled="#{gestionUsuariosBean.buscando}"
									id="btnDetalles" action="#{gestionUsuariosBean.detalles}" />
								<tr:commandButton text="#{resCore['MODIFICAR_USUARIO']}"
									disabled="#{gestionUsuariosBean.buscando}"
									id="btnModificar" action="#{gestionUsuariosBean.modificar}" />
								<tr:commandButton text="#{resCore['ELIMINAR_USUARIO']}"
									disabled="#{gestionUsuariosBean.buscando}"
									id="btnEliminar" action="#{gestionUsuariosBean.eliminar}"
									onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_USUARIO']}')" />
							</tr:panelButtonBar>
						</tr:panelGroupLayout>
						<tr:spacer height="10" />
						<tr:table var="var" first="0"
							emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
							value="#{gestionUsuariosBean.listaUsuarios}"
							rowBandingInterval="1" columnBandingInterval="0"
							selectedRowKeys="#{gestionUsuariosBean.estadoDeSeleccionTabla}"
							rowSelection="single" id="listaUsuarios">
							<f:facet name="actions">
								<tr:panelHorizontalLayout inlineStyle="width: 350px">
									<tr:outputText id="elementosEncontrados"
										inlineStyle="font-weight: bold"
										value="#{gestionUsuariosBean.numeroElementosTabla}" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:column sortable="true" headerText="#{resCore['USUARIO']}">
								<tr:outputText value="#{var.login}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['NOMBRE']}">
								<tr:outputText value="#{var.nombre}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['PERFILES']}">
								<tr:outputText value="#{var.descPerfiles}" />
							</tr:column>
						</tr:table>
						<tr:spacer height="20" width="20" />
						<tr:panelHorizontalLayout halign="center" rendered="#{gestionUsuariosBean.buscando}">
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
								action="#{gestionUsuariosBean.aceptar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
								immediate="true" action="#{gestionUsuariosBean.cancelar}" />
						</tr:panelHorizontalLayout>
						<tr:spacer height="20" width="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['MENU_PRINCIPAL']}"
								id="btnMenu" action="#{gestionUsuariosBean.menuPrincipal}" />
						</tr:panelHorizontalLayout>

					</tr:panelBox>

				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout halign="center">
				<h:outputText value="#{resCore['VERSION']}" style="font-size:50%" />
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
