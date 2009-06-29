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
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resPed['TOTALES_PROCEDIMIENTO'] }">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<h:form>
				<tr:panelFormLayout>
<!--					<f:facet name="footer" />-->
					<tr:spacer width="550" height="10" />
					<tr:spacer width="550" height="10" />
					<tr:panelBox background="medium" inlineStyle="width:100%;"
						text="Totales por Procedimiento de Compra">
						<tr:spacer width="100%" height="10" />
<!--						<tr:panelHeader text="#{resPed['TOTALES_PROCEDIMIENTO']}"-->
<!--							messageType="none" />-->
						<tr:table rowBandingInterval="1" columnBandingInterval="0"
							emptyText="No hay pedidos" width="100%"
							value="#{pedidos_consultarTotalesPedidosBean.recogerTotales}" var="total" rows="10"
							binding="#{pedidos_consultarTotalesPedidosBinding.tablaTotales}">
<!--							<f:facet name="selection">-->
<!--								<tr:tableSelectMany-->
<!--								text="#{resPed['SELECCIONE_PEDIDOS_DESPLEGAR']}"-->
<!--								id="selectorTablaMuchos" autoSubmit="true"/>-->
<!--							</f:facet>-->
							<tr:column sortable="true" align="start" headerText="#{resPed['PROCEDIMIENTO_COMPRA']}">
								<tr:outputText value="#{total.procedimientoCompra}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resPed['IMPORTE']}" align="right">
								<tr:outputText value="#{total.importe}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resPed['PORCENTAJE']}" align="right">
								<tr:outputText value="#{total.porcentaje}" />
							</tr:column>
						</tr:table>
						<tr:commandButton text="#{resCore['VOLVER']}"
								action="#{pedidos_consultarPedidosBean.botonVolver}" />
					</tr:panelBox>
				</tr:panelFormLayout>
				<tr:spacer width="550" height="10" />
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
