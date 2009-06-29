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
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<trh:html>
		<trh:head title="#{resCat['SELECCION_CLASIFICACION_POR_DEFECTO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>	
		</trh:head>
		<trh:body>
			<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
			<tr:statusIndicator id="indicador">
				<f:facet name="busy">
					<f:verbatim>
							<div id="divEspera">
								<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>
							</div>
					</f:verbatim>
				</f:facet>
				<f:facet name="ready">
				</f:facet>
			</tr:statusIndicator>
			
			<tr:panelFormLayout>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:outputText
							value="#{catalogo_seleccionarClasificacionPorDefecto.titulo}" />
						<tr:table emptyText="#{resCat['NO_ELEMENTOS']}"
							value="#{catalogo_seleccionarClasificacionPorDefecto.listaArticuloClasificacionFria}"
							var="var">
							<tr:column sortable="false" headerText="#{resCor['DESDE']}">
								<tr:panelHorizontalLayout valign="middle" halign="center">
									<tr:outputText value="#{var.fechaInicioValidez}" />
								</tr:panelHorizontalLayout>
							</tr:column>
							<tr:column sortable="false"
								headerText="#{resCat['DESCRIPCION_CLASIFICACION']}" >
								<tr:panelHorizontalLayout valign="middle" halign="center">
									<tr:outputText value="#{var.nivelValor.clasificacion.descripcion} : #{var.nivelValor.descripcion}" />
								</tr:panelHorizontalLayout>
							</tr:column>
							<tr:column sortable="false" headerText="#{resCat['COMENTARIO']}">
								<tr:inputText value="#{var.comentario}" columns="100" rows="3"
									maximumLength="255" />
							</tr:column>
							<tr:column sortable="false" headerText="#{resCat['DEFECTO']}">
								<tr:panelHorizontalLayout valign="middle" halign="center">
									<tr:selectBooleanCheckbox value="#{var.defecto}" />
								</tr:panelHorizontalLayout>
							</tr:column>
							<f:facet name="actions">
								<tr:panelHorizontalLayout>
									<tr:commandButton text="#{resCor['ACEPTAR']}"
										action="#{catalogo_seleccionarClasificacionPorDefecto.aceptar}" />
									<tr:spacer width="10" height="0" />
									<tr:commandButton text="#{resCor['CANCELAR']}"
										action="#{catalogo_seleccionarClasificacionPorDefecto.cancelar}" />
								</tr:panelHorizontalLayout>
							</f:facet>
						</tr:table>
					</tr:panelPage>
				</tr:form>
			</tr:panelFormLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
