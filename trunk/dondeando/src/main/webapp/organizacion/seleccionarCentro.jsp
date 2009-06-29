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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resOrg['SELECCIONAR']}">
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
			
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:panelBox text="#{resOrg['SELECCIONAR']}" inlineStyle="width:100%;">
					<tr:selectOneChoice label="#{resOrg['ORGANICA']}" id="organica"
						autoSubmit="true"
						valueChangeListener="#{organizacion_seleccionarCentroBean.actualizarCentrosConsumo}"
						value="#{organizacion_seleccionarCentroBean.idOrganoGestor}">
						<f:selectItems
							value="#{organizacion_seleccionarCentroBean.listaOrganosGestores}" />
					</tr:selectOneChoice>
                    <tr:spacer height="20"/>
					<tr:selectOneChoice label="#{resOrg['CENTRO_CONSUMO']}" id="centro"
						partialTriggers="organica"
						value="#{organizacion_seleccionarCentroBean.idCentroConsumo}">
						<f:selectItems
							value="#{organizacion_seleccionarCentroBean.listaCentrosConsumo}" />
					</tr:selectOneChoice>
                    <tr:spacer height="20"/>
                    <tr:panelHorizontalLayout rendered="#{organizacion_seleccionarCentroBean.mostrarWarningMenuPrincipal}"
                                          inlineStyle="width: 100%; background-color: #9b0000; font-weight: bolder; font-size: 100%; color: white; text-align: center;">
                        
                        <tr:outputText value="#{resOrg['WARNING_CAMBIO_CENTRO']}" />
                    </tr:panelHorizontalLayout>
				</tr:panelBox>
				<tr:panelButtonBar halign="center">
					<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
						action="#{organizacion_seleccionarCentroBean.aceptar}" />
					<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
						action="#{organizacion_seleccionarCentroBean.cancelar}" />
				</tr:panelButtonBar>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
