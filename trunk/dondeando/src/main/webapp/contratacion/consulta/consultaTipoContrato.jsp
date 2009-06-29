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
      <trh:head title="Gestión de tipos de contrato">
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
          <tr:panelHeader text="Gestión de tipos de contrato"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>
         
           <tr:spacer width="10" height="10"/>
          <tr:table emptyText="No items were found" width="100%" selectionListener="#{tablaTipoContratoBean.eventoSeleccion}"
          	var="fila" autoSubmit="true" rowSelection="single"
          	value="#{tablaTipoContratoBean.modelo}"
          	selectedRowKeys="#{tablaTipoContratoBean.seleccionadas}">

            <tr:column  headerText="Código">
              <tr:outputText value="#{fila.codigo}"/>
            </tr:column>
             <tr:column  headerText="Descripción">
              <tr:outputText value="#{fila.descripcion}"/>
            </tr:column>
            <f:facet name="actions">
            <tr:panelButtonBar>
              <tr:commandButton text="Agregar" action="#{agregarAccionTabla.ejecuta}" />
               <tr:commandButton text="Modificar"  action="#{modificarTipoContrato.ejecuta}" >
                </tr:commandButton>
                <tr:commandButton id="cbBaja" text="Baja" action="#{borrarTipoContrato.ejecuta}"/>
                   </tr:panelButtonBar>
            </f:facet>
          </tr:table>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
           <tr:commandButton text="Menú principal"
                              action="#{botonMenuAdminBean.ejecuta}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>