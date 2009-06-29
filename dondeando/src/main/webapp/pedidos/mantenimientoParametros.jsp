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
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />


		<trh:html>
		<trh:head title="Mantenimiento Parametros">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
		 <geos:cabeceraPagina/>
			<tr:form onsubmit="bloquearPantalla(this);">

				<tr:panelPage id="panelPage1">
					<tr:panelHeader text="#{resPed['MANTENIMIENTO_PARAMETROS']}"/>
		
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					
					<tr:table emptyText="#{resCor['NO_ELEMENTOS']}" id="tabla"
						rowBandingInterval="1"
						columnBandingInterval="0"
						width="100%" var="param" 
						value="#{pedidos_mantenimientoParametrosBean.listaParametros}">
						
						<tr:column sortable="false" headerText="#{resCor['CODIGO']}">
							<tr:commandLink text="#{param.codigo}" id="commandLink"
								action="#{pedidos_mantenimientoParametrosBean.getOutcomeEditar}"
								actionListener="#{pedidos_mantenimientoParametrosBean.editarListener}"
								useWindow="true" partialSubmit="true"
								shortDesc="#{resCor['EDITAR']}"
								returnListener="#{pedidos_mantenimientoParametrosBean.aplicarCambiosAParametro}"
								windowHeight="500" windowWidth="620">
								<tr:attribute name="paramId" value="#{param.id}"/>
								<tr:attribute name="paramCod" value="#{param.codigo}"/>
							</tr:commandLink>
						</tr:column>
						<tr:column sortable="false" headerText="#{resCor['DESCRIPCION']}"
							noWrap="true">
							<tr:outputText value="#{param.descripcion}" truncateAt="60"
								shortDesc="#{param.descripcion}" />
						</tr:column>
						<tr:column sortable="false" headerText="#{resCat['VALOR']}"
							noWrap="true">
							<tr:outputText value="#{param.valor}" truncateAt="60"
								shortDesc="#{param.descripcion}" partialTriggers="commandLink" />
						</tr:column>
					</tr:table>


				</tr:panelPage>

			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
