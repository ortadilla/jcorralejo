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
      <trh:head title="Edición del cuadro resumen">
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
          <tr:panelHeader text="#{editorCuadroResumenLicitacion.titulo}" id="titulo"  />
          <tr:messages></tr:messages>
          <tr:panelHorizontalLayout halign="center">
              <tr:commandButton text="Aceptar"
                                action="#{accionesCuadroResumen.aceptar}" id="aceptar"
                                rendered="#{configuracionEditorCuadroResumen.botonAceptarVisible}"/>
              <tr:commandButton text="Cancelar"
                                action="#{accionesCuadroResumen.cancelar}" id="cancelar" />
              <tr:commandButton text="Guardar"
                                actionListener="#{accionesCuadroResumen.guardar}" id="guardar"
                                rendered="#{configuracionEditorCuadroResumen.botonGuardarVisible}" />
          </tr:panelHorizontalLayout>
          <tr:separator/>

          <tr:group binding="#{editorCuadroResumenBinding.fpanelForm}" id="panelGeneral" />

          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
              <tr:commandButton text="Aceptar"
                                action="#{accionesCuadroResumen.aceptar}" id="aceptar2"
                                rendered="#{configuracionEditorCuadroResumen.botonAceptarVisible}"/>
              <tr:commandButton text="Cancelar"
                                action="#{accionesCuadroResumen.cancelar}" id="cancelar2" />
              <tr:commandButton text="Guardar"
                                actionListener="#{accionesCuadroResumen.guardar}" id="guardar2"
                                rendered="#{configuracionEditorCuadroResumen.botonGuardarVisible}" />
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>