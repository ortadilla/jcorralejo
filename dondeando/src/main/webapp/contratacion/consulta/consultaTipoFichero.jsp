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
      <trh:head title="Gestión de tipos de ficheros">
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
          <tr:panelHeader text="Gestión de tipos de ficheros"/>
          <tr:messages />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%">
            <tr:showDetailHeader text="Criterios de búsqueda" id="detalle" disclosed="#{!consultaTipoFichero.haRecuperadoDatos}">
            <tr:spacer width="10" height="10"/>
			<tr:panelFormLayout>
             	 <tr:panelLabelAndMessage label="Descripción">
                  <tr:inputText simple="true" value="#{formBusquedaTipoFichero.descripcion}"  inlineStyle="width:365px"/>
                  </tr:panelLabelAndMessage>
                  <tr:panelLabelAndMessage label="Prefijo" >
                  <tr:inputText simple="true" value="#{formBusquedaTipoFichero.prefijo}" inlineStyle="width:365px"/>
                  </tr:panelLabelAndMessage>
                  <tr:panelLabelAndMessage label="Sección">
					<tr:selectOneChoice simple="true" value="#{formBusquedaTipoFichero.seccion}"  inlineStyle="width:370px">
                    	<f:selectItems value="#{formBusquedaTipoFichero.listaSecciones}"/>
        	      	</tr:selectOneChoice>
                  </tr:panelLabelAndMessage>
			</tr:panelFormLayout>
            <tr:spacer width="10" height="10"/>
            <tr:panelHorizontalLayout halign="right">
                <tr:commandButton text="Buscar" action="#{consultaTipoFichero.encontrar}"/>
            </tr:panelHorizontalLayout>
           </tr:showDetailHeader>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
          <tr:table emptyText="No items were found" width="100%" selectionListener="#{tablaTipoFicheroBean.eventoSeleccion}"
          	var="fila"  autoSubmit="true" rowSelection="single"
          	value="#{tablaTipoFicheroBean.modelo}">
            <f:facet name="actions">
                <tr:panelButtonBar>
                      <tr:commandButton text="Agregar"  action="#{agregarAccionTabla.ejecuta}"/>
                      <tr:commandButton text="Modificar" action="#{modificarTipoFichero.ejecuta}" />
                      <tr:commandButton id="cbBaja" text="Baja" action="#{borrarTipoFichero.ejecuta}"/>
                </tr:panelButtonBar>
            </f:facet>

            <tr:column  headerText="Descripción">
              <tr:outputText value="#{fila.descripcion}"/>
            </tr:column>
            <tr:column  headerText="Prefijo">
              <tr:outputText value="#{fila.prefijo}"/>
            </tr:column>
            <tr:column  headerText="Seccion">
              <tr:outputText value="#{fila.seccion}"/>
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