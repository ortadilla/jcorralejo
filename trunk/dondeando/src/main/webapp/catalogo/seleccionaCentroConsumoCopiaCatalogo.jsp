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
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />


		<trh:html>
		<trh:head title="Selecciona Centro Consumo">
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

				<tr:panelPage
					title="#{mensajesCat['SELECCIONE_CENTRO_CONSUMO']}"
					id="panelPage1">
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<f:facet name="actions" />
					<tr:panelBox text="#{mensajesCat['CENTRO_CONSUMO']}" inlineStyle="width: 100%;">

						<tr:inputText label="#{mensajesCat['CATALOGO']}" disabled="#{catalogo_seleccionaCentroConsumoBean.deshabilitarOutputNombre}"
							wrap="off" rows="3" columns="50" partialTriggers="combo1"
							value="#{catalogo_seleccionaCentroConsumoBean.nombreCatalogo}" />
							<tr:spacer width="0" height="15" />
							<tr:panelLabelAndMessage label="#{mensajesCat['CENTRO_CONSUMO']}">
								<tr:selectOneChoice id="combo1" autoSubmit="true"
									value="#{catalogo_seleccionaCentroConsumoBean.centroConsumoSeleccionado}"
									valueChangeListener="#{catalogo_seleccionaCentroConsumoBean.recalculaSelectItemPuntosEntrega}">
									<f:selectItems
										value="#{catalogo_seleccionaCentroConsumoBean.centrosConsumo}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
							<tr:spacer width="0" height="15" />
							<tr:panelLabelAndMessage label="#{mensajesOrg['PUNTO_ENTREGA']}">
								<tr:selectOneChoice partialTriggers="combo1"
									value="#{catalogo_seleccionaCentroConsumoBean.puntoEntregaSeleccionado}">
									<f:selectItems
										value="#{catalogo_seleccionaCentroConsumoBean.puntosEntrega}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
					</tr:panelBox>
					<tr:spacer width="0" height="15" />
					<tr:panelGroup>
						<tr:commandButton text="#{mensajesCore['ACEPTAR']}" id="btnAceptar" partialTriggers="combo1"
							action="#{catalogo_seleccionaCentroConsumoBean.botonAceptar}" 
							disabled="#{catalogo_seleccionaCentroConsumoBean.deshabilitarBotonAceptar}"/>
						<tr:spacer width="20" height="0" />
						<tr:commandButton text="#{mensajesCore['CANCELAR']}"
							action="#{catalogo_seleccionaCentroConsumoBean.botonCancelar}" />
					</tr:panelGroup>
				</tr:panelPage>

			</tr:form>

		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>