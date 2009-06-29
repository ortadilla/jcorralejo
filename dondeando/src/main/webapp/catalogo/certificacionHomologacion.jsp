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
		<trh:html>
		<trh:head title="#{resCat['CERTIFICACION_HOMOLOGACION']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
		
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:panelPage>
				<tr:panelHeader text="#{resCat['CERTIFICACION_HOMOLOGACION']}" />
				<tr:panelHorizontalLayout halign="center">
					<tr:panelBox>
						<tr:form  onsubmit="bloquearPantalla(this);">
							<tr:spacer height="10" />
							<tr:commandButton text="#{resCat['CARGA_CERTIFICACIONES']}"
								action="#{catalogo_certificacionHomologacionBean.cargaCertificaciones}"/>
							<tr:spacer height="10" />
							<tr:commandButton text="#{resCat['CARGA_CERTIFICADOS_PDF']}"
								action="#{catalogo_certificacionHomologacionBean.cargaCertificadosPdf}"/>
							<tr:spacer height="10" />
							<tr:commandButton text="#{resCat['CARGA_CERTIFICADOS_PDF_MODIFICADOS']}"
								action="#{catalogo_certificacionHomologacionBean.cargaCertificadosPdfModificados}"/>
							<tr:spacer height="10" />
							<tr:commandButton text="#{resCat['CARGA_HOMOLOGACIONES']}"
								action="#{catalogo_certificacionHomologacionBean.cargaHomologaciones}"/>
							<tr:spacer height="10" />	
							<tr:commandButton text="#{resCat['CARGAR_CONCURSOS_FICTICIOS']}"
								action="#{catalogo_certificacionHomologacionBean.concursosFicticios}"/>
							<tr:spacer height="10" />
							<tr:commandButton text="#{resCat['CARGA_PROTOCOLOS_EVALUACION']}"
								action="#{catalogo_certificacionHomologacionBean.cargaProtocolosEvaluacion}"/>
						</tr:form>
					</tr:panelBox>
				</tr:panelHorizontalLayout>
				<f:facet name="messages">
					<tr:messages />
        	    </f:facet>
			</tr:panelPage>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
