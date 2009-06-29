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
      <trh:head title="Gestión de bloques del menú">
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
          <tr:panelHeader text="Gestión de bloques del menú"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%">
            <tr:spacer width="10" height="10"/>
              <tr:panelHorizontalLayout halign="right">
	              <tr:commandButton text="Buscar" action="#{consultaBloqueMenuRol.encontrar}"/>
              </tr:panelHorizontalLayout>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
          <tr:table emptyText="No items were found" width="100%" var="bloqueMenuRol"
          			partialTriggers="cbBaja" value="#{tablaBloqueMenuRolBean.modelo}"
                    selectionListener="#{tablaBloqueMenuRolBean.eventoSeleccion}"
                    rowSelection="single">
			<f:facet name="actions">
            <tr:panelButtonBar>
              	<tr:commandButton id="cbAgregar" text="Agregar"
              					  action="#{agregarAccionTabla.ejecuta}"/>
                <tr:commandButton id="cbBaja" text="Borrar"
                				  action="#{borrarBloqueMenuRol.ejecuta}"
                				  onclick="if(!confirm('¿Desea borrar la asociación del rol con el bloque del menú?')) return false"/>
            </tr:panelButtonBar>
            </f:facet>
            <tr:column headerText="Rol" sortable="true" sortProperty="codigoRol">
              <tr:outputText value="#{bloqueMenuRol.codigoRol}"/>
            </tr:column>

            <tr:column headerText="Bloque" sortable="true" sortProperty="bloqueMenu">
              <tr:outputText value="#{bloqueMenuRol.bloqueMenu}"/>
            </tr:column>
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