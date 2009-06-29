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
      <trh:head>
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

        <h:form title="Listado histórico de valores" onsubmit="bloquearPantalla(this)">
          <tr:panelHeader text="Listado histórico de valores"/>

            <tr:panelBox background="medium" inlineStyle="width: 100%;" contentStyle="width: 100%;" text="Listado histórico de valores"   >
            <tr:panelFormLayout inlineStyle="width: 100%;" ></tr:panelFormLayout>
            	<tr:table value="#{consultaHistoricoValorCamposCuadroResumenBean.modelo}" var="fila" width="100%" rows="20" >

            		<tr:column headerText="Campo" rendered="#{consultaHistoricoValorCamposCuadroResumenBean.muestraColumnaCampo}" >
            			<tr:outputText value="#{fila.campo}"></tr:outputText>
            		</tr:column>
            		<tr:column headerText="Fecha" >
            			<tr:outputText value="#{fila.fecha}">
                  			<tr:convertDateTime pattern="dd/MM/yyyy hh:mm:ss aa" />
                  		</tr:outputText>
            		</tr:column>
            		<tr:column headerText="Valor" >
            			<tr:outputText value="#{fila.valor}"></tr:outputText>
            		</tr:column>

            	</tr:table>


            </tr:panelBox>
            <tr:separator/>
            <tr:panelHorizontalLayout halign="center">
              <tr:panelButtonBar>
              	<tr:commandButton text="Cerrar"
              					  action="#{botonCerrarPopUpBean.volver}"/>

              </tr:panelButtonBar>
            </tr:panelHorizontalLayout>

        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
