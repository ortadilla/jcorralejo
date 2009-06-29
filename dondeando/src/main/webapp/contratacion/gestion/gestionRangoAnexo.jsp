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
      <trh:head title="Gestión de Documentación">
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
			<tr:messages />
          <tr:spacer width="10" height="10"/>
           <tr:panelFormLayout partialTriggers="insertarRango">

          	<tr:panelBox text="Insertar Rango" background="medium" >
                <tr:panelLabelAndMessage label="Desde:">
                  <tr:panelHorizontalLayout>
                    <tr:inputText value="#{gestionRangoAnexo.desde}"/>
                  </tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>

                <tr:panelLabelAndMessage label="Hasta:">
                  <tr:panelHorizontalLayout>
                    <tr:inputText value="#{gestionRangoAnexo.hasta}"/>
                  </tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>

                <tr:panelLabelAndMessage label="Tipo Representación:">
					<tr:selectOneChoice value="#{gestionRangoAnexo.tipo}">
                    	<f:selectItem itemLabel="Arábigo" itemValue="A"/>
                    	<f:selectItem itemLabel="Romano" itemValue="R"/>
                    	<f:selectItem itemLabel="Letra" itemValue="L"/>
        	      	</tr:selectOneChoice>
	            </tr:panelLabelAndMessage>

                <tr:commandButton id="insertarRango" text="Añadir" action="#{gestionRangoAnexo.insertarRango}" partialSubmit="true"/>
			</tr:panelBox>

          	<tr:spacer width="10" height="10"/>

            <tr:table emptyText="No se ha encontrado tipos de rangos disponibles" var="rango" width="100%"
                   value="#{gestionRangoAnexo.tablaRangos}" partialTriggers="insertarRango">
				<tr:column headerText="Desde" sortable="true" sortProperty="desde">
					<tr:outputText value="#{rango.desde}"/>
				</tr:column>
				<tr:column headerText="Hasta" sortable="true" sortProperty="hasta">
					<tr:outputText value="#{rango.hasta}"/>
				</tr:column>
				<tr:column headerText="Hasta" sortable="true" sortProperty="hasta">
					<tr:outputText value="#{rango.tipo}"/>
				</tr:column>
				<tr:column headerText="Accion">
					<tr:commandLink action="#{gestionRangoAnexo.parametrosEliminarRango}"  onclick="if(!confirm('¿Desea eliminar el rango?')) return false">
						<tr:image source="../imagenes/eliminar.gif" shortDesc="Eliminar Rango"/>
						<f:param value="#{rango.desde}" name="desde"/>
						<f:param value="#{rango.hasta}" name="hasta"/>
						<f:param value="#{rango.tipo}" name="tipo"/>
					</tr:commandLink>
				</tr:column>
            </tr:table>

	        <tr:spacer width="10" height="10"/>
         </tr:panelFormLayout>

	<tr:panelHorizontalLayout halign="center">
                <h:panelGroup>
		           <tr:panelHorizontalLayout halign="center">
        		    <tr:commandButton text="Aceptar"
                             action="#{gestionRangoAnexo.aceptar}" />
		            <tr:commandButton text="Cancelar"
	                         action="#{gestionRangoAnexo.cancelar}" />

    		      </tr:panelHorizontalLayout>
                </h:panelGroup>
    </tr:panelHorizontalLayout>


		</h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>