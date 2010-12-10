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

		<trh:head title="#{resCore['GESTION_PRESTAMOS']}" />

		<trh:body initialFocusId="user">

			<tr:form defaultCommand="btnAceptar">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{resCore['GESTION_PRESTAMOS']}" />

					<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
						disclosed="#{gestionPrestamosBean.desplegado}" id="showDetail"
						partialTriggers="showDetail"
						binding="#{gestionPrestamosBinding.busqueda}">
						<tr:panelBox inlineStyle="width:100%;" background="medium"
							partialTriggers="btnLimpiar">
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="5" cellPadding="0">
									<trh:rowLayout>
										<tr:outputText value="#{resCore['USUARIO']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText columns="50"
											value="#{gestionPrestamosBean.criterioUsuario!=null ? gestionPrestamosBean.criterioUsuario.nombre : ''}"
											id="usuario" disabled="true" />
										<tr:commandLink action="#{gestionPrestamosBean.buscarUsuario}">
											<tr:image shortDesc="#{resCore['SELECCIONAR_USUARIO']}"
												source="#{'/imagenes/buscar.png'}"
												inlineStyle="height: 20px;" />
										</tr:commandLink>
										<tr:commandLink
											action="#{gestionPrestamosBean.eliminarUsuario}">
											<tr:image shortDesc="#{resCore['BORRAR_USUARIO']}"
												source="#{'/imagenes/borrar.png'}"
												inlineStyle="height: 20px;" />
										</tr:commandLink>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['LIBRO']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText columns="50"
											value="#{gestionPrestamosBean.criterioLibro!=null ? gestionPrestamosBean.criterioLibro.titulo : ''}"
											id="libro" disabled="true" />
										<tr:commandLink action="#{gestionPrestamosBean.buscarLibro}">
											<tr:image shortDesc="#{resCore['SELECCIONAR_LIBRO']}"
												source="#{'/imagenes/buscar.png'}"
												inlineStyle="height: 20px;" />
										</tr:commandLink>
										<tr:commandLink
											action="#{gestionPrestamosBean.eliminarLibro}">
											<tr:image shortDesc="#{resCore['BORRAR_LIBRO']}"
												source="#{'/imagenes/borrar.png'}"
												inlineStyle="height: 20px;" />
										</tr:commandLink>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['FECHA_INICIO']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputDate columns="20" partialTriggers=":::btnLimpiar"
											value="#{gestionPrestamosBean.criterioFechaInicio}" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['FECHA_FIN']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputDate columns="20" partialTriggers=":::btnLimpiar"
											value="#{gestionPrestamosBean.criterioFechaFin}" />
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />

							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['BUSCAR']}" id="btnBuscar"
									action="#{gestionPrestamosBean.buscar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['LIMPIAR']}" id="btnLimpiar"
									immediate="true" action="#{gestionPrestamosBean.limpiar}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:showDetailHeader>

					<tr:spacer width="10" height="20" />

					<tr:panelBox inlineStyle="width:100%;" text="#{resCore['PRESTAMOS']}">
						<tr:panelGroupLayout>
							<tr:panelButtonBar>
								<tr:commandButton text="#{resCore['AGREGAR_PRESTAMO']}"
									disabled="#{!gestionPrestamosBean.permisoGestionarPrestamos}"
									id="btnAgregar" action="#{gestionPrestamosBean.agregar}" />
								<tr:commandButton text="#{resCore['DETALLES_PRESTAMO']}"
									id="btnDetalles" action="#{gestionPrestamosBean.detalles}" />
								<tr:commandButton text="#{resCore['MODIFICAR_PRESTAMO']}"
									disabled="#{!gestionPrestamosBean.permisoGestionarPrestamos}"
									id="btnModificar" action="#{gestionPrestamosBean.modificar}" />
								<tr:commandButton text="#{resCore['ELIMINAR_PRESTAMO']}"
									disabled="#{!gestionPrestamosBean.permisoGestionarPrestamos}"
									id="btnEliminar" action="#{gestionPrestamosBean.eliminar}"
									onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_PRESTAMO']}')" />
								<tr:commandButton text="#{resCore['DEVOLVER_LIBRO']}"
									disabled="#{!gestionPrestamosBean.permisoGestionarPrestamos}"
									id="btnDevolver" action="#{gestionPrestamosBean.devolver}" />
							</tr:panelButtonBar>
						</tr:panelGroupLayout>
						<tr:spacer height="10" />
						<tr:table var="var" first="0"
							emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
							value="#{gestionPrestamosBean.listaPrestamos}"
							rowBandingInterval="1" columnBandingInterval="0"
							selectedRowKeys="#{gestionPrestamosBean.estadoDeSeleccionTabla}"
							rowSelection="single" id="listaPrestamos">
							<f:facet name="actions">
								<tr:panelHorizontalLayout inlineStyle="width: 350px">
									<tr:outputText id="elementosEncontrados"
										inlineStyle="font-weight: bold"
										value="#{gestionPrestamosBean.numeroElementosTabla}" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:column sortable="true" headerText="#{resCore['USUARIO']}">
								<tr:outputText value="#{var.usuario.nombre}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['LIBRO']}">
								<tr:outputText value="#{var.libro.titulo}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['FECHA_INICIO']}">
								<tr:outputText value="#{var.fechaInicio}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['FECHA_FIN']}">
								<tr:outputText value="#{var.fechaFin}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['DEVUELTO']}">
								<tr:outputText value="#{var.devuelto ? resCore['SI'] : resCore['NO']}" />
							</tr:column>
						</tr:table>
						<tr:spacer height="20" width="20" />
						<tr:panelHorizontalLayout halign="center" rendered="false">
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
								action="#{gestionPrestamosBean.aceptar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
								immediate="true" action="#{gestionPrestamosBean.cancelar}" />
						</tr:panelHorizontalLayout>
						<tr:spacer height="20" width="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['MENU_PRINCIPAL']}"
								id="btnMenu" action="#{gestionPrestamosBean.menuPrincipal}" />
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
