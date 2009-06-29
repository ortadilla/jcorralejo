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
		<f:loadBundle basename="mensajesPed" var="mensajesPed" />


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

				<tr:panelPage id="panelPage1">
					<tr:panelHeader text="#{mensajesPed['SELECCIONE_AVISO_EXPEDICION']}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<f:facet name="actions" />
					<tr:panelBox inlineStyle="width: 100%;">
						<tr:table emptyText="" id="tabla" rows="10" var="var"  width="100%"
						rowBandingInterval="1" columnBandingInterval="0"
						autoSubmit="true"
						selectedRowKeys="#{pedidos_seleccionarEntradaAlmacenAsociadaPedido.estadoTabla}"
						value="#{pedidos_seleccionarEntradaAlmacenAsociadaPedido.lista}"
						rowSelection="single">
<!--						<f:facet name="selection">-->
<!--								<tr:tableSelectOne text="Select items and ..."  autoSubmit="true"/>-->
<!--							</f:facet>-->
							
							<tr:column sortable="false" headerText="#{mensajesPed['MGPROVEEDOR']}">
								<tr:outputText value="#{var.mgProveedor.descripcion}"/>
							</tr:column>
							<tr:column sortable="false" headerText="#{mensajesPed['ALBARANPROVEEDOR']}" >
								<tr:outputText value="#{var.albaranProveedor}"/>
							</tr:column>
							<tr:column sortable="false" headerText="#{mensajesPed['FECHAENTREGAPREVISTA']}">
								<tr:outputText value="#{var.fechaEntregaPrevista}"/>
							</tr:column>
							<tr:column sortable="false" headerText="#{mensajesPed['NUMEROBULTOS']}">
								<tr:outputText value="#{var.numeroBultos}"/>
							</tr:column>
							<tr:column sortable="false" headerText="#{mensajesPed['NUMEROAVISO']}">
								<tr:outputText value="#{var.numeroAviso}"/>
							</tr:column>
							
							</tr:table>
						
					</tr:panelBox>
					<tr:spacer width="0" height="15" />
					<tr:panelGroupLayout>					
        				<tr:commandButton text="#{mensajesCore['ACEPTAR']}"
							action="#{pedidos_seleccionarEntradaAlmacenAsociadaPedido.botonAceptar}" />
						<tr:spacer width="10" height="0" />
						<tr:commandButton text="#{mensajesCore['CANCELAR']}"
							action="#{pedidos_seleccionarEntradaAlmacenAsociadaPedido.botonCancelar}" />
					</tr:panelGroupLayout>
				</tr:panelPage>

			</tr:form>

		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>