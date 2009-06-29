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
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<trh:html id="html1">
		<trh:head title="#{resPed['AGRUPACION_PEDIDOS']}">
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
			<tr:form defaultCommand="botonGuardar" onsubmit="bloquearPantalla(this);">
				<tr:messages />
				<tr:panelHeader text="#{resPed['AGRUPAR_PEDIDOS_SEGUN']}" />
				<tr:panelBox id="panelBox1" background="medium"
					inlineStyle="width: 100%;">
					<tr:panelFormLayout maxColumns="2" rows="1">
						<h:panelGroup id="panelGroupIzq">
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="tipoCompra" selected="true"
									disabled="true" />
								<tr:panelLabelAndMessage label="#{resPed['TIPO_COMPRA']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="emprsa" disabled="true"
									selected="true" />
								<tr:panelLabelAndMessage label="#{resPed['EMPRESA']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="contrato" selected="true"
									disabled="true" />
								<tr:panelLabelAndMessage label="#{resPed['EXPEDIENTE']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="clasifOrganica" selected="true"
									disabled="true" />
								<tr:panelLabelAndMessage label="#{resCat['ORGANO_GESTOR']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="clasifFuncional"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.funcional}" />
								<tr:panelLabelAndMessage
									label="#{resCat['CLASIFICACION_FUNCIONAL']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="clasifEconomica"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.economica}" />
								<tr:panelLabelAndMessage
									label="#{resCat['CLASIFICACION_ECONOMICA']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
						</h:panelGroup>
						<h:panelGroup id="panelGroupDcho">
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="tipoOrigen"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.tipoOrigen}" />
								<tr:panelLabelAndMessage label="#{resNec['TIPO_ORIGEN']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="origenSolicitud"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.origen}" />
								<tr:panelLabelAndMessage label="#{resNec['ORIGEN']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="puntoEntrega"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.puntoEntrega}" />
								<tr:panelLabelAndMessage label="#{resNec['PUNTO_ENTREGA']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="fechaEntrega"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.fechaEntrega}" />
								<tr:panelLabelAndMessage label="#{resNec['FECHA_ENTREGA']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="prioridad"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.prioridad}" />
								<tr:panelLabelAndMessage label="#{resNec['PRIORIDAD']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout>
								<tr:selectBooleanCheckbox id="articulo"
									selected="#{pedidos_agruparPedidosBean.pedidoTemporal.articulo}" />
								<tr:panelLabelAndMessage label="#{resCat['ARTICULO']}">
								</tr:panelLabelAndMessage>
							</tr:panelHorizontalLayout>
						</h:panelGroup>
					</tr:panelFormLayout>
					<tr:panelHorizontalLayout halign="right">
						<tr:commandButton text="#{resCore['GUARDAR']}" id="botonGuardar"
							action="#{pedidos_agruparPedidosBean.guardar}" />
						<tr:commandButton text="#{resCore['CANCELAR']}"
							action="#{pedidos_agruparPedidosBean.cancelar}" />
					</tr:panelHorizontalLayout>
				</tr:panelBox>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
