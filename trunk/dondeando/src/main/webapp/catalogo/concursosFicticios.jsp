<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:geos="http://www.hp-cda.com/adf/faces">
 <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
 <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
	<f:view>
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<trh:html>
		<trh:head title="#{resCat['CARGAR_CONCURSOS_FICTICIOS']}">
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
					<tr:panelHeader text="#{resCat['CARGAR_CONCURSOS_FICTICIOS']}" />
					<tr:panelBox inlineStyle="width: 100%;">
						<tr:form onsubmit="bloquearPantalla(this);">
							<tr:panelHorizontalLayout>
								<tr:outputText value="#{resCat['ZONA_CATALOGO']}" /> 
								<tr:spacer width="10" />
								<tr:inputText wrap="off" disabled="true" rows="1" columns="100"
									value="#{catalogo_concursoFicticiosBean.clasificacionSeleccionada}" 
									partialTriggers="seleccionarClasificacion"/>
								<tr:commandLink id="seleccionarClasificacion"
									action="#{catalogo_concursoFicticiosBean.seleccionarClasificacion}"
									returnListener="#{catalogo_concursoFicticiosBean.returnClasificacion}"
									useWindow="true" windowHeight="600" windowWidth="800" >
									<tr:image source="../imagenes/lupa3.gif" />
								</tr:commandLink>
								<tr:commandLink id="botonBorrarDestino"
									action="#{catalogo_concursoFicticiosBean.borrarClasificacion}">
									<tr:image source="../imagenes/btnEliminar.gif" />
									</tr:commandLink>
							</tr:panelHorizontalLayout>
					
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout>
								<tr:outputText value="#{resCat['FECHA_INICIO_HOMOLOGACION']}" /> 
								<tr:inputDate value="#{catalogo_concursoFicticiosBean.fechaInicio}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout>
								<tr:outputText value="#{resCat['FECHA_FIN_HOMOLOGACION']}" /> 
								<tr:inputDate value="#{catalogo_concursoFicticiosBean.fechaFin}" />
							</tr:panelHorizontalLayout>
					
							<tr:spacer width="10" height="20" />
							<tr:panelHorizontalLayout>
								<tr:commandButton text="#{resCore['ACEPTAR']}" 
									action="#{catalogo_concursoFicticiosBean.aceptar}" />
								<tr:spacer width="10"/>
								<tr:commandButton text="#{resCore['VOLVER']}" 
									action="#{catalogo_concursoFicticiosBean.volver}" />
							</tr:panelHorizontalLayout>
						</tr:form>
					</tr:panelBox>
				<f:facet name="messages">
					<tr:messages />
            	</f:facet>
			</tr:panelPage>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;" rendered="#{!catalogo_gestionClasificacionesAlternativa.mostrarComoPopUp}">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
