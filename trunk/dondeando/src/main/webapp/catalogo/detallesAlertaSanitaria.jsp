<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />


		<trh:html>
		<trh:head title="Detalles Alerta Sanitaria">
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
			
			<tr:form  defaultCommand="btnAceptar" onsubmit="bloquearPantalla(this);">

				<tr:panelPage id="panelPage1">
					<tr:panelHeader text="#{mensajesCat['DETALLES_ALERTA_SANITARIA']}"/>
					
					<f:facet name="actions" />
					<tr:panelBox text="#{mensajesCat['ALERTA_SANITARIA']}" inlineStyle="width: 100%;">
						<tr:table rows="5" banding="row"
							emptyText="#{mensajesCat['ALERTA_SANITARIA']}" var="alerta"
							value="#{catalogo_detallesAlertaSanitariaBean.lista}"
							width="100%" id="tablaAlertas">
							<tr:column  headerText="#{mensajesCore['DESCRIPCION']}">
								<tr:outputText value="#{alerta.descripcion}" />
							</tr:column>
							<tr:column  align="end"
								headerText="#{mensajesCat['FECHA_REGISTRO']}">
								<tr:outputText value="#{alerta.fechaRegistro}" />
							</tr:column>
							<tr:column  align="end"
								headerText="#{mensajesCore['ACTIVO']}">
								<tr:outputText value="#{alerta.activo}" />
							</tr:column>
						</tr:table>
					</tr:panelBox>
					<tr:spacer width="0" height="15" />
					<tr:panelGroup>
						<tr:commandButton text="#{mensajesCore['ACEPTAR']}" id="btnAceptar" 
							action="#{catalogo_detallesAlertaSanitariaBean.botonAceptar}" />
					</tr:panelGroup>
				</tr:panelPage>

			</tr:form>

		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>