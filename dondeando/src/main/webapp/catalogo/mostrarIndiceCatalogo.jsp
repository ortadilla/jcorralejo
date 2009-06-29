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
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />

		<trh:html>
		<trh:head title="#{mensajesCat['MOSTRAR_INDICE_CATALOGO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			 <link rel="stylesheet" charset="UTF-8" type="text/css" href="../skins/hojiblanca/mostrarIndiceCatalogo.css" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina/>
			<tr:form onsubmit="bloquearPantalla(this);">			
				<tr:showDetailHeader text="#{mensajesOrg['LEYENDA']}" id="showDetail" partialTriggers="showDetail">
					<tr:panelBox>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{catalogo_mostrarIndiceCatalogoBean.leyendaGrupo}" styleClass="IndiceCatalogoGrupo"></tr:outputText>
						<tr:spacer width="10"></tr:spacer>
						<tr:outputText value="#{mensajesCat['DENOMINACION_GRUPO']}"></tr:outputText>
					</tr:panelHorizontalLayout>
					<tr:spacer height="10"></tr:spacer>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{catalogo_mostrarIndiceCatalogoBean.leyendaSubgrupo}" styleClass="IndiceCatalogoSubGrupo"></tr:outputText>
						<tr:spacer width="10"></tr:spacer>
						<tr:outputText value="#{mensajesCat['DENOMINACION_SUBGRUPO']}"></tr:outputText>
					</tr:panelHorizontalLayout>
					<tr:spacer height="10"></tr:spacer>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{catalogo_mostrarIndiceCatalogoBean.leyendaFamilia}"></tr:outputText>
						<tr:spacer width="10"></tr:spacer>
						<tr:outputText value="#{mensajesCat['DENOMINACION_FAMILIA']}"></tr:outputText>
					</tr:panelHorizontalLayout>
					</tr:panelBox>
					<tr:spacer height="10"></tr:spacer>
					<tr:panelBox>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{mensajesCat['INFORMACION_DE_FAMILIA']}"></tr:outputText>						
					</tr:panelHorizontalLayout>
					<tr:spacer height="10"></tr:spacer>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_NEGRO']}"></tr:outputText>
						<tr:spacer width="10"></tr:spacer>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_NEGRO_INFORMACION']}"></tr:outputText>
					</tr:panelHorizontalLayout>
					<tr:spacer height="10"></tr:spacer>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_VERDE']}" styleClass="IndiceCatalogoColorVerde"></tr:outputText>
						<tr:spacer width="10"></tr:spacer>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_VERDE_INFORMACION']}"></tr:outputText>
					</tr:panelHorizontalLayout>
					<tr:spacer height="10"></tr:spacer>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_ROJO']}" styleClass="IndiceCatalogoColorRojo"></tr:outputText>
						<tr:spacer width="10"></tr:spacer>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_ROJO_INFORMACION']}"></tr:outputText>
					</tr:panelHorizontalLayout>
					<tr:spacer height="10"></tr:spacer>
					<tr:panelHorizontalLayout>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_AZUL']}" styleClass="IndiceCatalogoColorAzul"></tr:outputText>
						<tr:spacer width="10"></tr:spacer>
						<tr:outputText value="#{mensajesCat['CODIGO_COLOR_AZUL_INFORMACION']}"></tr:outputText>
					</tr:panelHorizontalLayout>
					</tr:panelBox>
					<tr:spacer height="10"></tr:spacer>				
				</tr:showDetailHeader>
				<tr:spacer height="10"></tr:spacer>
				<tr:tree var="node" value="#{catalogo_mostrarIndiceCatalogoBean.listaIndices}" disclosedRowKeys="#{catalogo_mostrarIndiceCatalogoBean.conjuntoNodosExpandidos}">
				  <f:facet name="nodeStamp">
				  		<tr:commandLink text="#{node.descripcion}" styleClass="#{node.valorCss}" 
				  		actionListener="#{catalogo_mostrarIndiceCatalogoBean.obtenerFamiliaSeleccionada}"
				  		action="#{catalogo_mostrarIndiceCatalogoBean.volver}"
				  		blocking="true"
				  		partialSubmit="true">
				  			<tr:attribute name="idNivelValor" value="#{node.idNivelValor}"/>
				  		</tr:commandLink>				  		
				  </f:facet>				   
				</tr:tree>				
				<tr:spacer width="10" height="10"/>
              <tr:commandButton text="#{mensajesCore['VOLVER']}"
                                 action="#{catalogo_mostrarIndiceCatalogoBean.volver}"/>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
