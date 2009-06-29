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
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<trh:html>
		<!--      <trh:head title="#{catalogo_editarAtributoArticuloBean.titulo}">-->
		<trh:head
			title="#{resCat['CABECERA_PAGINA_EDICION_ATRIBUTO_ARTICULO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:messages />
				
				<tr:panelHorizontalLayout rendered="#{catalogo_editarAtributoArticuloBean.mostrarPanelParalelo}"
					inlineStyle="width: 100%; background-color: #9b0000; font-weight: bolder; font-size: 100%; color: white; text-align: center;">
					<tr:outputText value="#{resCat['CATALOGO_PARALELO']}"/>
				</tr:panelHorizontalLayout>	
					   
				<tr:panelBox id="panelAtributos"
					binding="#{catalogo_editarAtributoArticuloBinding.panelAtributos}" />
				<tr:panelHorizontalLayout>
					<tr:commandButton text="#{resCor['ACEPTAR']}"
						action="#{catalogo_editarAtributoArticuloBean.aceptar}"
						rendered="#{catalogo_editarAtributoArticuloBean.renderizarAceptar}" />
					<tr:commandButton text="#{resCor['CONFIRMAR']}"
						action="#{catalogo_editarAtributoArticuloBean.confirmar}"
						rendered="#{catalogo_editarAtributoArticuloBean.renderizarConfirmar}" />
					<tr:spacer width="10" height="10" />
					<tr:commandButton text="#{resCor['CANCELAR']}"
						action="#{catalogo_editarAtributoArticuloBean.cancelar}" />
				</tr:panelHorizontalLayout>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
