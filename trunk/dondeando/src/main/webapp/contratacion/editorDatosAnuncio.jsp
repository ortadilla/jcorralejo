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
      <trh:head title="Datos del anuncio">
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
          <tr:panelHeader text="#{editorDatosAnuncio.titulo}"/>
          <tr:messages></tr:messages>
          <tr:panelButtonBar>
              <tr:commandButton text="Aceptar"
   			                   action="#{accionesCuadroResumen.aceptar}"
   			                   rendered="#{configuracionEditorAnuncio.botonAceptarVisible}"/>
              <tr:commandButton text="Cancelar"
                               action="#{accionesCuadroResumen.cancelar}"/>
              <tr:commandButton text="Guardar"
                                actionListener="#{accionesCuadroResumen.guardar}"
                                rendered="#{configuracionEditorAnuncio.botonGuardarVisible}"/>
            </tr:panelButtonBar>
          <tr:group binding="#{editorCuadroResumenBinding.fpanelForm}">
            </tr:group>
            <tr:panelButtonBar>
              <tr:commandButton text="Aceptar"
   			                   action="#{accionesCuadroResumen.aceptar}"
   			                   rendered="#{configuracionEditorAnuncio.botonAceptarVisible}"/>
              <tr:commandButton text="Cancelar"
                               action="#{accionesCuadroResumen.cancelar}"/>
              <tr:commandButton text="Guardar"
                                actionListener="#{accionesCuadroResumen.guardar}"
                                rendered="#{configuracionEditorAnuncio.botonGuardarVisible}"/>
            </tr:panelButtonBar>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
