<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=windows-1252"/>
  <f:view>
    <f:loadBundle basename="mensajesOrg" var="resOrg" />
    <f:loadBundle basename="mensajesCore" var="resCore" />
    <trh:html>
      <trh:head title="#{resOrg['FECHABAJA_PERSONA']}">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
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
			
      	<tr:panelBox inlineStyle="width: 100%;">
        <tr:form onsubmit="bloquearPantalla(this);">
           	<tr:outputText value="#{organizacion_fechaBajaPersonaBean.mensajeBaja}"/>	
            <tr:spacer width="0" height="20"/>            
            <tr:panelHorizontalLayout>     
	           	<tr:outputText value="#{resOrg['FECHABAJA']}"/>	
                <tr:inputDate value="#{organizacion_fechaBajaPersonaBean.fechaBaja}" />
			</tr:panelHorizontalLayout>

            <tr:spacer width="10" height="10"/>
            <tr:panelHorizontalLayout  halign="center">
            	<tr:commandButton text="#{resCore['ACEPTAR']}"
              	 	action="#{organizacion_fechaBajaPersonaBean.botonAceptarPopUP}"/>
              	<tr:spacer width="10" height="10"/>             
              	<tr:commandButton text="#{resCore['CANCELAR']}"
              		action="#{organizacion_fechaBajaPersonaBean.botonCancelarPopUP}"/>
            </tr:panelHorizontalLayout>           
          </tr:form>
       </tr:panelBox>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>