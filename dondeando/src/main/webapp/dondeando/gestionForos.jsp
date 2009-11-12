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
				<tr:panelHeader text="#{resCore['GESTION_FOROS']}" />

				<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
					disclosed="#{gestionForosBean.desplegado}" id="showDetail"
					partialTriggers="showDetail"
					binding="#{gestionForosBinding.busqueda}">
					<tr:panelBox inlineStyle="width:100%;" background="medium"
						partialTriggers="btnLimpiar">
						<tr:panelHorizontalLayout halign="center">
							<trh:tableLayout cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TITULO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="20" partialTriggers=":::btnLimpiar"
										value="#{gestionForosBean.criterioTitulo}"
										id="criterioUsuario" maximumLength="20" />
								</trh:rowLayout>
								<trh:rowLayout rendered="#{gestionForosBean.mostrarCriterioActivo}">
									<tr:outputText value="#{resCore['ACTIVO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:selectOneChoice
										value="#{gestionForosBean.criterioActivo}">
										<f:selectItems id="selectSiNo"
											value="#{gestionForosBean.selectSiNo}" />
									</tr:selectOneChoice>
								</trh:rowLayout>
							</trh:tableLayout>
						</tr:panelHorizontalLayout>
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['BUSCAR']}" id="btnBuscar"
								action="#{gestionForosBean.buscar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['LIMPIAR']}" id="btnLimpiar"
								immediate="true" action="#{gestionForosBean.limpiar}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
				</tr:showDetailHeader>

				<tr:spacer width="10" height="20" />

				<tr:panelBox inlineStyle="width:100%;" text="#{resCore['FOROS']}">
					<tr:panelGroupLayout>
						<tr:panelButtonBar>
							<tr:commandButton text="#{resCore['VER_TEMAS']}"
								id="btnVerMensajes" action="#{gestionForosBean.verTemas}" />
							<tr:commandButton text="#{resCore['AGREGAR_FORO']}"
								rendered="#{gestionForosBean.mostrarAgregar}"
								id="btnAgregar" action="#{gestionForosBean.agregar}" />
							<tr:commandButton text="#{resCore['DETALLES_FORO']}"
								id="btnDetalles" action="#{gestionForosBean.detalles}" />
							<tr:commandButton text="#{resCore['MODIFICAR_FORO']}"
								rendered="#{gestionForosBean.mostrarAgregar}"
								id="btnModificar" action="#{gestionForosBean.modificar}" />
							<tr:commandButton text="#{resCore['ELIMINAR_FORO']}"
								rendered="#{gestionForosBean.mostrarAgregar}"
								id="btnEliminar" action="#{gestionForosBean.eliminar}"
								onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_FORO']}')" />
							<tr:commandButton text="#{resCore['RECUPERAR_FORO']}"
								rendered="#{gestionForosBean.mostrarAgregar}"
								id="btnRecuperar" action="#{gestionForosBean.recuperar}" />
							<tr:commandButton text="#{resCore['MODERADORES']}"
								rendered="#{gestionForosBean.mostrarAgregar}"
								id="btnModeradores" action="#{gestionForosBean.irModeradores}" />
						</tr:panelButtonBar>
					</tr:panelGroupLayout>
					<tr:spacer height="10" />
					<tr:table var="var" first="0"
						emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
						value="#{gestionForosBean.listaForos}"
						rowBandingInterval="1" columnBandingInterval="0"
						selectedRowKeys="#{gestionForosBean.estadoDeSeleccionTabla}"
						rowSelection="single" id="listaForos">
						<f:facet name="actions">
							<tr:panelHorizontalLayout inlineStyle="width: 350px">
								<tr:outputText id="elementosEncontrados"
									inlineStyle="font-weight: bold"
									value="#{gestionForosBean.numeroElementosTabla}" />
							</tr:panelHorizontalLayout>
						</f:facet>
						<tr:column sortable="true" headerText="#{resCore['TITULO']}">
							<tr:outputText value="#{var.titulo}" />
						</tr:column>
						<tr:column sortable="true"
							headerText="#{resCore['DESCRIPCION']}">
							<tr:outputText value="#{var.descripcion}" />
						</tr:column>
						<tr:column sortable="true"
							headerText="#{resCore['NUMERO_TEMAS']}">
							<tr:outputText value="#{var.numeroTemas}" />
						</tr:column>
						<tr:column sortable="true"
							headerText="#{resCore['NUMERO_MENSAJES']}">
							<tr:outputText value="#{var.numeroMensajes}" />
						</tr:column>
						<tr:column sortable="true"
							headerText="#{resCore['ULTIMO_POST']}">
							<tr:outputText value="#{var.descripcionUltimoPost}" />
						</tr:column>
						<tr:column sortable="false" headerText="#{resCore['ACTIVO']}"
							rendered="#{gestionForosBean.mostrarCriterioActivo}">
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
