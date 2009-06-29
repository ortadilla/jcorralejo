<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"

          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:tr="http://myfaces.apache.org/trinidad">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Validación datos del anuncio">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
      </trh:head>
      <trh:body>

<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
            <tr:statusIndicator id="indicador">
                <f:facet name="busy">
                    <f:verbatim>
                            <div id="divEspera">
                                <p style="margin-top: 60px; text-align:center; width: 100%;">
                                [    Cargando datos, por favor espere...    ]</p>
                            </div>
                    </f:verbatim>
                </f:facet>
                <f:facet name="ready">
                </f:facet>
            </tr:statusIndicator>

			<trh:script source="/include/libreriaGEOS.js">
			                <!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>

        <h:form onsubmit="bloquearPantalla(this)">
          <tr:panelHeader text="#{editorDatosAnuncioValidacion.titulo}"/>
          <tr:messages></tr:messages>
           <tr:panelButtonBar>
              <tr:commandButton text="Aceptar" id="btn-1"
   			                  action="#{accionesValidacionDatosAnuncio.aceptar}"
   			                  rendered="#{configuracionEditorAnuncio.botonAceptarVisible}"
							  actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" />
              <tr:commandButton text="Cancelar" id="btn-2"
                               action="#{accionesValidacionDatosAnuncio.cancelar}"/>
              <tr:commandButton text="Guardar" id="btn-3"
                                rendered="#{configuracionEditorAnuncio.botonGuardarVisible}"
  							    actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" >
				<tr:setActionListener from="#{'#{accionesValidacionDatosAnuncio.guardar}'}" 
									  to="#{actionListenerCompruebaBloqueo.expresionActionListenerDecorado}" />
			  </tr:commandButton>

            </tr:panelButtonBar>
          <tr:group binding="#{editorCuadroResumenBinding.fpanelForm}">
          </tr:group>
          <tr:panelButtonBar>
            <tr:commandButton text="Aceptar" id="btn-12"
   			                  action="#{accionesValidacionDatosAnuncio.aceptar}"
   			                  rendered="#{configuracionEditorAnuncio.botonAceptarVisible}"
							  actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" />
              <tr:commandButton text="Cancelar" id="btn-22"
                               action="#{accionesValidacionDatosAnuncio.cancelar}"/>
              <tr:commandButton text="Guardar" id="btn-32"
                                rendered="#{configuracionEditorAnuncio.botonGuardarVisible}"
  							    actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" >
				<tr:setActionListener from="#{'#{accionesValidacionDatosAnuncio.guardar}'}" 
									  to="#{actionListenerCompruebaBloqueo.expresionActionListenerDecorado}" />
			  </tr:commandButton>

            </tr:panelButtonBar>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
