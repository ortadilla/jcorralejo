<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Consulta de ámbitos">
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
          <tr:panelHeader text="Ámbitos definidos en el sistema"/>
          <tr:table emptyText="No items were found" width="100%" rows="10"
                    selectionListener="#{tablaAmbitosBean.eventoSeleccion}"
                    selectedRowKeys="#{tablaAmbitosBean.seleccionadas}"
                    value="#{tablaAmbitosBean.modelo}" rowSelection="single" autoSubmit="true"
                    var="ambito" partialTriggers="cbSelEstadosAsociados" id="tblAmbitos" >

            <tr:column  headerText="Descripción">
              <tr:outputText value="#{ambito.descripcion}"/>
            </tr:column>
            <tr:column  headerText="Afecta bloqueo">
                <tr:selectBooleanCheckbox value="#{ambito.afectaBloqueo}" />
            </tr:column>
            <tr:column  headerText="Estados asociados" >
              <tr:selectOneListbox label="Estado asociados	"
                                   size="3"
                                   rendered="#{ambito.pintaTablaEstados}" >
                <f:selectItems value="#{ambito.descripcionEstados}"/>
              </tr:selectOneListbox>
            </tr:column>

            <f:facet name="actions">
             <tr:panelButtonBar>
              <tr:commandButton text="Agregar" action="#{agregarAccionTabla.ejecuta}" />
                <tr:commandButton text="Modificar" action="#{modificarAmbito.ejecuta}" />
 				  <tr:commandButton text="Borrar" action="#{borrarAmbito.ejecuta}"
  				  					onclick="if(!confirm('¿Desea borrar el ámbito?')) return false" />
                </tr:panelButtonBar>
            </f:facet>
          </tr:table>
          <tr:panelGroupLayout partialTriggers="tblAmbitos">
			<tr:commandButton text="Modificar estados"
			                  action="#{modificarEstadosAsociadosAmbito.ejecuta}"
			                  useWindow="true"
			                  launchListener="#{modificarEstadosAsociadosAmbito.parametrosEstadosAsociadosAmbito}"
			                  returnListener="#{modificarEstadosAsociadosAmbito.retornaEstadosSeleccionados}"
			                  partialSubmit="true" id="cbSelEstadosAsociados"
			                  rendered="#{(tablaAmbitosBean.seleccionado != null)}" >
			  <f:attribute name="accion" value="editarValores"/>
			</tr:commandButton>
		  </tr:panelGroupLayout>

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
