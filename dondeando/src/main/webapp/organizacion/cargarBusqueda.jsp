<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:geos="http://www.hp-cda.com/adf/faces">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=windows-1252"/>
  <f:view>
    <f:loadBundle basename="mensajesCore" var="resCore"/>
    <trh:html>
      <trh:head title="Cargar datos de Búsqueda guardados">
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"/>
        <trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
		</trh:script>
		<tr:outputText escape="false" value="#{htmlHead}"/>		
      </trh:head>
      <trh:body>
       <geos:cabeceraPagina/>
        <tr:form defaultCommand="botonAceptar" onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<tr:panelHeader text="#{resCore['CARGAR_DATOS_BUSQUEDA']}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:spacer width="10" height="10" />				
						<tr:panelBox background="medium" inlineStyle="100%;">
							<tr:panelFormLayout id="panelCriterios" partialTriggers="listaSeleccionPerfiles">
								<tr:spacer width="10" height="10" />
								<tr:table   emptyText="#{resCore['NO_ELEMENTOS']}" 
											var="busqueda"
											id="tabla" 
											value="#{pruebaBD.busquedasVisibles}" 
											binding="#{organizacion_busquedasPrecargadasBinding.tablaListadoBusquedas}"
											rows="10"
											rowBandingInterval="1"
					                        columnBandingInterval="0"											
											first="0"
											selectedRowKeys="#{organizacion_busquedasPrecargadasBean.estadoDeSeleccionTabla}"
											width="100%"
											rowSelection="multiple">
											
<!--											<f:facet name="selection">-->
<!--												<af:tableSelectMany text="#{resCat['SELECCION_VALOR']}"-->
<!--													id="seleccion" />-->
<!--											</f:facet>-->
											<tr:column sortable="true" headerText="#{resCore['DESCRIPCION']}"
												sortProperty="descripcion">
												<tr:outputText value="#{busqueda.descripcion}" />
											</tr:column>
								</tr:table>
								<f:facet name="footer">
									<tr:panelHorizontalLayout halign="left">
										<tr:commandButton id="botonAceptar"
														  text="#{resCore['ACEPTAR']}"
														  action="#{organizacion_busquedasPrecargadasBean.accionAceptar}" />
										<tr:commandButton id="botonCancelar"
														  text="#{resCore['CANCELAR']}"
										 				  action="#{organizacion_busquedasPrecargadasBean.accionCancelar}" />
									</tr:panelHorizontalLayout>
								</f:facet>
							</tr:panelFormLayout>
						</tr:panelBox>					
				</tr:panelPage>
			</tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
