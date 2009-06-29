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
    <f:loadBundle basename="mensajesOrg" var="resOrg"/>
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
					<tr:panelHeader text="#{resOrg['GUARDAR_DATOS_BUSQUEDA']}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:spacer width="10" height="10" />				
						<tr:panelBox background="medium" inlineStyle="100%;">
							<tr:panelFormLayout id="panelDatos" 
										  partialTriggers="listaSeleccionPerfiles"
										  binding="#{organizacion_busquedasPrecargadasBinding.panelDatos}">
								<tr:spacer width="10" height="10" />
								<tr:panelLabelAndMessage label="#{resCore['DESCRIPCION']}">
									<tr:inputText value="#{organizacion_busquedasPrecargadasBean.descripcion}" rows="2" columns="50"
												  simple="true"/>
								</tr:panelLabelAndMessage>
								<tr:spacer width="10" height="10" />								
								<tr:panelLabelAndMessage label="#{resOrg['SELECCIONAR_PARA_QUIEN_GUARDAR']}">
									<tr:selectOneRadio id="listaSeleccionPerfiles"
													   value="#{organizacion_busquedasPrecargadasBean.seleccionGuardarPara}"
													   valueChangeListener="#{organizacion_busquedasPrecargadasBean.listenerCambioValor}"	
													   simple="true"												   
													   autoSubmit="true">
													   
					                    <tr:selectItem label="Usuario" value="0"/>
					                    <tr:selectItem label="Perfiles" value="1"/>
					                    <tr:selectItem label="Todos" value="2"/>
					                </tr:selectOneRadio>
								</tr:panelLabelAndMessage>
								<tr:spacer width="10" height="10" />								
								 <tr:panelLabelAndMessage 
								 		 partialTriggers="listaSeleccionPerfiles"
								 		 label="Seleccione el perfil o los perfiles para los que desea guardar esta búsqueda"
                                         rendered="#{organizacion_busquedasPrecargadasBean.mostrarPerfiles}">
                                         
				                   <tr:selectManyListbox value="#{organizacion_busquedasPrecargadasBean.seleccionPerfiles}" simple="true">
				                     <f:selectItems value="#{organizacion_busquedasPrecargadasBean.listaPerfiles}"/>
				                   </tr:selectManyListbox>
				                   
 				                 </tr:panelLabelAndMessage>
 				                 
								<!-- Campos -->
								<f:facet name="footer">
									<h:panelGroup>
										<tr:panelHorizontalLayout halign="right">
											<tr:commandButton text="#{resCore['ACEPTAR']}"
												id="botonAceptar"
												action="#{organizacion_busquedasPrecargadasBean.accionAceptar}" />
											<tr:commandButton text="#{resCore['CANCELAR']}"
												id="botonCancelar"
												action="#{organizacion_busquedasPrecargadasBean.accionCancelar}"  />
										</tr:panelHorizontalLayout>										
									</h:panelGroup>
								</f:facet>
							</tr:panelFormLayout>
						</tr:panelBox>					
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
