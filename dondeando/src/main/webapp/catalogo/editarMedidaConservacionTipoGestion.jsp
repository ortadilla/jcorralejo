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
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<trh:html>
		<trh:head title="#{resCat['INDICAR_VALOR']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
				<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>

			<tr:outputText escape="false" value="#{htmlHead}" />
		</trh:head>
		<trh:body>
			<tr:statusIndicator id="indicador">
				<f:facet name="busy">
					<f:verbatim>
						<div id="divEspera">
						<p style="margin-top: 60px; text-align: center; width: 100%;">[
						Cargando datos, por favor espere... ]</p>
						</div>
					</f:verbatim>
				</f:facet>
				<f:facet name="ready">
				</f:facet>
			</tr:statusIndicator>

			<tr:panelFormLayout>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<tr:panelHeader
							text="#{catalogo_editarMedidaConservacionTipoGestionBean.titulo}" />
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelHorizontalLayout>
						<tr:table emptyText="#{resCor['NO_ELEMENTOS']}"
							value="#{catalogo_editarMedidaConservacionTipoGestionBean.listaValoresTipoGestion}"
							var="var"
							rowSelection="single"
							rowBandingInterval="1"
							selectedRowKeys="#{catalogo_editarMedidaConservacionTipoGestionBean.seleccionTipoGestion}"
							partialTriggers="botonBorrarTipoGestion">
							<tr:column sortable="false"
								headerText="#{resCat['TIPO_GESTION_LOGISTICA']}">
								<tr:outputText value="#{var.descripcion}" />
							</tr:column>
						</tr:table>
						<tr:commandLink id="botonBorrarTipoGestion"
							actionListener="#{catalogo_editarMedidaConservacionTipoGestionBean.borrarTipoGestion}">
							<tr:image source="../imagenes/btnEliminar.gif" />
						</tr:commandLink>
						<tr:spacer width="10" height="10" />
						<tr:table emptyText="#{resCor['NO_ELEMENTOS']}"
							value="#{catalogo_editarMedidaConservacionTipoGestionBean.listaValoresMedidasConservacion}"
							var="var"
							rowSelection="multiple"
							rowBandingInterval="1"
							selectedRowKeys="#{catalogo_editarMedidaConservacionTipoGestionBean.seleccionMedidasConservacion}">
							<tr:column sortable="false"
								headerText="#{resCat['ATRIBUTOMEDIDASCONSERVACION']}">
								<tr:outputText value="#{var.descripcion}" />
							</tr:column>
						</tr:table>
						</tr:panelHorizontalLayout>
					</tr:panelPage>
					<tr:panelButtonBar>
						<tr:commandButton text="#{resCor['ACEPTAR']}" id="btnAceptar"
							action="#{catalogo_editarMedidaConservacionTipoGestionBean.aceptar}" />
						<tr:commandButton text="#{resCor['CANCELAR']}" id="btnCancelar"
							action="#{catalogo_editarMedidaConservacionTipoGestionBean.cancelar}" />
					</tr:panelButtonBar>
				</tr:form>
			</tr:panelFormLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
