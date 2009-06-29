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
		<trh:head title="Registrar Incidencia de Línea de Entrada">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<tr:form>
				<tr:panelPage id="panelPage1">
					<tr:panelHeader text="#{mensajesPed['REGISTRO_INCIDENCIAS']}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<f:facet name="actions" />
					<tr:panelBox inlineStyle="width: 100%;">
						<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText value="#{mensajesPed['TIPOINCIDENCIA']}"/>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectOneChoice id="comboTiposIncidencia" 
									value="#{pedidos_registrarIncidenciaBean.tipoIncidencia}"
									unselectedLabel=''>
										<f:selectItems
											value="#{pedidos_registrarIncidenciaBean.selectorTiposIncidencias}"
											id="selectorTiposIncidencia"
										 />
									</tr:selectOneChoice>
								</trh:cellFormat>					
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText value="#{mensajesCore['INCIDENCIAS']}"/>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:inputText rows="5" columns="20" value="#{pedidos_registrarIncidenciaBean.incidencias}">
									</tr:inputText>
								</trh:cellFormat>
							</trh:rowLayout>
						</trh:tableLayout>									
					</tr:panelBox>
					<tr:spacer width="0" height="15" />
					<tr:panelGroupLayout>					
        				<tr:commandButton text="#{mensajesCore['ACEPTAR']}"
							action="#{pedidos_registrarIncidenciaBean.botonAceptar}"/>
						<tr:spacer width="10" height="0" />
						<tr:commandButton text="#{mensajesCore['CANCELAR']}"
							action="#{pedidos_registrarIncidenciaBean.botonCancelar}"/>
					</tr:panelGroupLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>