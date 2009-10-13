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
					<tr:panelHeader text="#{resCore['GESTION_LOCALES']}" />

					<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
						disclosed="#{gestionLocalesBean.desplegado}" id="showDetail"
						partialTriggers="showDetail"
						binding="#{gestionLocalesBinding.busqueda}">
						<tr:panelBox inlineStyle="width:100%;" background="medium"
							partialTriggers="btnLimpiar">
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="5" cellPadding="0">
									<trh:rowLayout>
										<tr:outputText value="#{resCore['PROVINCIA']}"
											inlineStyle="font-weight: bolder;" />
										<trh:cellFormat>
											<tr:selectOneChoice
												value="#{gestionLocalesBean.criterioProvincia}">
												<f:selectItems id="selectProvincia"
													value="#{gestionLocalesBean.selectProvincia}" />
											</tr:selectOneChoice>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['PRECIO_MEDIO']}"
											inlineStyle="font-weight: bolder;" />
										<trh:cellFormat>
											<tr:selectOneChoice
												value="#{gestionLocalesBean.criterioPrecio}">
												<f:selectItems id="selectPrecio"
													value="#{gestionLocalesBean.selectPrecio}" />
											</tr:selectOneChoice>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['NOMBRE']}"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText columns="20" partialTriggers=":::btnLimpiar"
											value="#{gestionLocalesBean.criterioNombre}"
											id="criterioNombre" maximumLength="50" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['TIPO_LOCAL']}"
											inlineStyle="font-weight: bolder;" />
										<trh:cellFormat>
											<tr:selectManyListbox
												value="#{gestionLocalesBean.criterioTipoLocal}">
												<f:selectItems id="selectTipoLocal"
													value="#{gestionLocalesBean.selectTipoLocal}" />
											</tr:selectManyListbox>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout
										rendered="#{gestionLocalesBean.mostrarCriterioActivo}">
										<tr:outputText value="#{resCore['ACTIVO']}"
											inlineStyle="font-weight: bolder;" />
										<tr:selectOneChoice
											value="#{gestionLocalesBean.criterioActivo}">
											<f:selectItems id="selectSiNo"
												value="#{gestionLocalesBean.selectSiNo}" />
										</tr:selectOneChoice>
									</trh:rowLayout>


								</trh:tableLayout>
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['BUSCAR']}" id="btnBuscar"
									action="#{gestionLocalesBean.buscar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['LIMPIAR']}" id="btnLimpiar"
									immediate="true" action="#{gestionLocalesBean.limpiar}" />
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:showDetailHeader>

					<tr:spacer width="10" height="20" />

					<tr:panelBox inlineStyle="width:100%;"
						text="#{resCore['LOCALES']}">
						<tr:panelGroupLayout>
							<tr:panelButtonBar>
								<tr:commandButton text="#{resCore['AGREGAR_LOCAL']}"
									id="btnAgregar" action="#{gestionLocalesBean.agregar}" />
								<tr:commandButton text="#{resCore['DETALLES_LOCAL']}"
									id="btnDetalles" action="#{gestionLocalesBean.detalles}" />
								<tr:commandButton text="#{resCore['MODIFICAR_LOCAL']}"
									id="btnModificar" action="#{gestionLocalesBean.modificar}" />
								<tr:commandButton text="#{resCore['ELIMINAR_LOCAL']}"
									id="btnEliminar" action="#{gestionLocalesBean.eliminar}"
									onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_LOCAL']}')" />
								<tr:commandButton text="#{resCore['RECUPERAR_LOCAL']}"
									id="btnRecuperar" action="#{gestionLocalesBean.recuperar}" />
							</tr:panelButtonBar>
						</tr:panelGroupLayout>
						<tr:spacer height="10" />
						<tr:table var="var" first="0"
							emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
							value="#{gestionLocalesBean.listaLocales}" rowBandingInterval="1"
							columnBandingInterval="0"
							selectedRowKeys="#{gestionLocalesBean.estadoDeSeleccionTabla}"
							rowSelection="single" id="listaLocales">
							<f:facet name="actions">
								<tr:panelHorizontalLayout inlineStyle="width: 350px">
									<tr:outputText id="elementosEncontrados"
										inlineStyle="font-weight: bold"
										value="#{gestionLocalesBean.numeroElementosTabla}" />
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:column sortable="true" headerText="#{resCore['NOMBRE']}">
								<tr:outputText value="#{var.nombre}" />
							</tr:column>
							<tr:column sortable="false" headerText="#{resCore['TIPO_LOCAL']}">
								<tr:outputText value="#{var.cadenaTiposLocal}" />
							</tr:column>
							<tr:column sortable="true"
								headerText="#{resCore['PRECIO_MEDIO']}">
								<tr:image shortDesc="#{var.shortDescPrecio}"
									source="#{var.imagenPrecio}" inlineStyle="height: 20px;" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resCore['VALORACION']}">
								<tr:outputText value="#{var.valoracion}" />
							</tr:column>
							<tr:column sortable="false" headerText="#{resCore['DIRECCION']}">
								<tr:goLink destination="#{var.urlVerMapa}"
									text="#{var.direccionHumana}" targetFrame="_blank"
									onclick="window.open(this.href, this.target, 'width=800,height=600'); return false;" />
							</tr:column>
							<tr:column rendered="#{gestionLocalesBean.mostrarCriterioActivo}"
								sortable="false" headerText="#{resCore['ACTIVO']}">
								<tr:image
									shortDesc="#{var.activo ? resCore['SI'] : resCore['NO']}"
									source="#{var.activo ? '/imagenes/dondeando/check.png':'/imagenes/dondeando/cruz.png'}"
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
