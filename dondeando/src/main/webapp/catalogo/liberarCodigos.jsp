<?xml version='1.0' encoding='iso-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=iso-8859-15" />
	<f:view>
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />

		<trh:html>
		<trh:head title="#{mensajesCat['LIBERACION_CODIGOS']}">
			<meta http-equiv="Content-Type"
				content="text/html;charset=iso-8859-15" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
			
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<tr:panelHeader text="#{catalogo_liberarCodigos.tituloPagina}"/>
					<tr:panelHorizontalLayout>
						<tr:panelFormLayout>
							<tr:panelHorizontalLayout>
								<tr:table
									emptyText="#{mensajesCat['NO_ARTICULOS_SELECCIONADOS']}"
									value="#{catalogo_liberarCodigos.listaNivelesOrigen}"
									var="fila" rowBandingInterval="1" columnBandingInterval="0"
									rowSelection="multiple"
									partialTriggers="botonSeleccionarOrigen botonBorrarOrigen">
									<f:facet name="header">
										<tr:outputText value="#{mensajesCat['ARTICULOS_ORIGEN']}" />
									</f:facet>
									<tr:column sortable="false" headerText="Artículo">
										<tr:outputText value="#{fila.descripcionInformativa}" />
									</tr:column>
								</tr:table>
								<tr:commandLink id="botonSeleccionarOrigen"
									action="#{catalogo_liberarCodigos.seleccionarOrigen }"
									returnListener="#{catalogo_liberarCodigos.returnSeleccionarOrigen}"
									useWindow="true" windowHeight="600" windowWidth="800">
									<tr:image source="../imagenes/lupa3.gif" />
								</tr:commandLink>
								<tr:commandLink id="botonBorrarOrigen"
									actionListener="#{catalogo_liberarCodigos.borrarOrigen }">
									<tr:image source="../imagenes/btnEliminar.gif" />
								</tr:commandLink>
								<tr:spacer width="50" height="10" />
								<tr:table
									emptyText="#{mensajesCat['NO_ARTICULOS_SELECCIONADOS']}"
									value="#{catalogo_liberarCodigos.nivelValorDestino}" var="fila"
									rowBandingInterval="1" columnBandingInterval="0"
									rowSelection="none"
									partialTriggers="botonSeleccionarDestino botonBorrarDestino">
									<f:facet name="header">
										<tr:outputText value="#{mensajesCat['ARTICULO_DESTINO']}" />
									</f:facet>
									<tr:column sortable="false" headerText="Artículo">
										<tr:outputText value="#{fila.descripcionInformativa}" />
									</tr:column>
								</tr:table>
								<tr:commandLink id="botonSeleccionarDestino"
									action="#{catalogo_liberarCodigos.seleccionarDestino }"
									returnListener="#{catalogo_liberarCodigos.returnSeleccionarDestino}"
									useWindow="true" windowHeight="600" windowWidth="800">
									<tr:image source="../imagenes/lupa3.gif" />
								</tr:commandLink>
								<tr:commandLink id="botonBorrarDestino"
									action="#{catalogo_liberarCodigos.borrarDestino }">
									<tr:image source="../imagenes/btnEliminar.gif" />
								</tr:commandLink>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="20" />
							<tr:commandButton text="#{mensajesCat['LIBERAR']}"
									action="#{catalogo_liberarCodigos.aceptar}" 
									returnListener="#{catalogo_liberarCodigos.retornarConfirmacion}"
									useWindow="true" windowHeight="600" windowWidth="800"/>
						</tr:panelFormLayout>
					</tr:panelHorizontalLayout>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
				</tr:panelPage>
			</tr:form>
			
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
			
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
