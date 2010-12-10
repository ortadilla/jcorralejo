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

		<trh:head title="#{resCore['GESTION_LIBROS']}" />

		<trh:body initialFocusId="user">

			<tr:form defaultCommand="btnAceptar">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{resCore['GESTION_LIBROS']}" />

					<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
						disclosed="#{gestionLibrosBean.desplegado}" id="showDetail"
						partialTriggers="showDetail"
						binding="#{gestionLibrosBinding.busqueda}">
						<tr:panelBox inlineStyle="width:100%;" background="medium"
							partialTriggers="btnLimpiar">
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="5" cellPadding="0">
									<trh:rowLayout>
										<tr:outputText value="#{resCore['TITULO']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText columns="20" partialTriggers=":::btnLimpiar"
											value="#{gestionLibrosBean.criterioTitulo}"
											id="criterioTitulo" maximumLength="50" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['AUTOR']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText columns="20" partialTriggers=":::btnLimpiar"
											value="#{gestionLibrosBean.criterioAutor}"
											id="criterioAutor" maximumLength="50" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['ISBN']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText columns="20" partialTriggers=":::btnLimpiar"
											value="#{gestionLibrosBean.criterioISBN}"
											id="criterioISBN" maximumLength="13" />
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />

							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['BUSCAR']}" id="btnBuscar"
									action="#{gestionLibrosBean.buscar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['LIMPIAR']}" id="btnLimpiar"
									immediate="true" action="#{gestionLibrosBean.limpiar}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:showDetailHeader>

					<tr:spacer width="10" height="20" />

					<tr:panelBox inlineStyle="width:100%;"
						text="#{resCore['LIBROS']}">
						<tr:panelGroupLayout>
							<tr:panelButtonBar>
								<tr:commandButton text="#{resCore['AGREGAR_LIBRO']}"
									disabled="#{!gestionLibrosBean.permisoGestionarLibros || gestionLibrosBean.buscando}"
									id="btnAgregar" action="#{gestionLibrosBean.agregar}" />
								<tr:commandButton text="#{resCore['DETALLES_LIBRO']}"
									disabled="#{gestionLibrosBean.buscando}"
									id="btnDetalles" action="#{gestionLibrosBean.detalles}" />
								<tr:commandButton text="#{resCore['MODIFICAR_LIBRO']}"
									disabled="#{!gestionLibrosBean.permisoGestionarLibros || gestionLibrosBean.buscando}"
									id="btnModificar" action="#{gestionLibrosBean.modificar}" />
								<tr:commandButton text="#{resCore['ELIMINAR_LIBRO']}"
									disabled="#{!gestionLibrosBean.permisoGestionarLibros || gestionLibrosBean.buscando}"
									id="btnEliminar" action="#{gestionLibrosBean.eliminar}"
									onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_LIBRO']}')" />
							</tr:panelButtonBar>
						</tr:panelGroupLayout>
						<tr:spacer height="10" />
						<tr:table var="var" first="0"
							emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
							value="#{gestionLibrosBean.listaLibros}"
							rowBandingInterval="1" columnBandingInterval="0"
							selectedRowKeys="#{gestionLibrosBean.estadoDeSeleccionTabla}"
							rowSelection="single" id="listaLibros">
							<f:facet name="actions">
								<tr:panelHorizontalLayout inlineStyle="width: 350px">
									<tr:outputText id="elementosEncontrados"
										inlineStyle="font-weight: bold"
										value="#{gestionLibrosBean.numeroElementosTabla}" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:column sortable="true" headerText="#{resCore['TITULO']}">
								<tr:outputText value="#{var.titulo}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['AUTOR']}">
								<tr:outputText value="#{var.autor}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['ISBN']}">
								<tr:outputText value="#{var.isbn}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['UNIDADES_DISPONIBLES']}">
								<tr:outputText value="#{var.unidadesDisponibles}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['UNIDADES_PRESTAMO']}">
								<tr:outputText value="#{var.unidadesPrestamo}" />
							</tr:column>
						</tr:table>
						<tr:spacer height="20" width="20" />
						<tr:panelHorizontalLayout halign="center" rendered="#{gestionLibrosBean.buscando}">
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
								action="#{gestionLibrosBean.aceptar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
								immediate="true" action="#{gestionLibrosBean.cancelar}" />
						</tr:panelHorizontalLayout>
						<tr:spacer height="20" width="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['MENU_PRINCIPAL']}"
								id="btnMenu" action="#{gestionLibrosBean.menuPrincipal}" />
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
