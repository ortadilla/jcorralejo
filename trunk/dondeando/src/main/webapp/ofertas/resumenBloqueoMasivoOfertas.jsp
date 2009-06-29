<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/strict.dtd"
		doctype-public="-//W3C//DTD HTML 4.01//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOfe" var="mensajesOfe" />
		<trh:html>
		<trh:head title="#{mensajesOfe['RESUMEN_BLOQUEO_MASIVO_OFERTAS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<h:form>
				<tr:panelHeader text="#{mensajesOfe['OFERTAS_BLOQUEADAS']}" />
				<tr:panelBox background="medium" inlineStyle="width: 100%;">
					<tr:table rowBandingInterval="1" columnBandingInterval="0"
						emptyText="#{mensajesCore['NO_ELEMENTOS']}" rows="10" width="100%"
						var="bloqueadas" id="tablaOfertasBloqueadas"
						value="#{ofertas_resumenBloqueoMasivoOfertasBean.ofertasBloqueadas}">
						<tr:column sortable="true" sortProperty="modelo"
							headerText="#{mensajesOfe['MODELO']}" align="center">
							<tr:outputText value="#{bloqueadas.modelo}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="refFabricante"
							headerText="#{mensajesOfe['REFFABRICANTE']}" align="center">
							<tr:outputText value="#{bloqueadas.refFabricante}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="estadoOferta"
							headerText="#{mensajesOfe['ESTADOOFERTA']}" align="center">
							<tr:outputText value="#{mensajesOfe[bloqueadas.estadoOferta]}" />
						</tr:column>
						<tr:column inlineStyle="vertical-align:middle" sortable="false"
							align="center" headerText="#{mensajesOfe['ICONOINFLOGISTICA']}">
							<tr:image source="/imagenes/#{bloqueadas.iconoInfLogistica}" />
						</tr:column>
						<tr:column sortable="false" align="center"
							headerText="#{mensajesOfe['CDC']}">
							<tr:outputText value="#{bloqueadas.producto.CDC}" />
						</tr:column>
					</tr:table>
				</tr:panelBox>
				<tr:panelHeader text="#{mensajesOfe['OFERTAS_NO_BLOQUEADAS']}" />
				<tr:panelBox background="medium" inlineStyle="width: 100%;">
					<tr:table rowBandingInterval="1" columnBandingInterval="0"
						emptyText="#{mensajesCore['NO_ELEMENTOS']}" rows="10" width="100%"
						var="noBloqueadas" id="tablaOfertasNoBloqueadas"
						value="#{ofertas_resumenBloqueoMasivoOfertasBean.ofertasNoBloqueadas}">
						<tr:column sortable="true" sortProperty="modelo"
							headerText="#{mensajesOfe['MODELO']}" align="center">
							<tr:outputText value="#{noBloqueadas.modelo}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="refFabricante"
							headerText="#{mensajesOfe['REFFABRICANTE']}" align="center">
							<tr:outputText value="#{noBloqueadas.refFabricante}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="estadoOferta"
							headerText="#{mensajesOfe['ESTADOOFERTA']}" align="center">
							<tr:outputText value="#{mensajesOfe[noBloqueadas.estadoOferta]}" />
						</tr:column>
						<tr:column inlineStyle="vertical-align:middle" sortable="false"
							align="center" headerText="#{mensajesOfe['ICONOINFLOGISTICA']}">
							<tr:image source="/imagenes/#{noBloqueadas.iconoInfLogistica}" />
						</tr:column>
						<tr:column sortable="false" align="center"
							headerText="#{mensajesOfe['CDC']}">
							<tr:outputText value="#{noBloqueadas.producto.CDC}" />
						</tr:column>
					</tr:table>
				</tr:panelBox>
				<tr:panelButtonBar halign="end">
					<tr:commandButton text="#{mensajesCore['CERRAR']}"
						action="#{ofertas_resumenBloqueoMasivoOfertasBean.cerrar}" />
				</tr:panelButtonBar>
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
