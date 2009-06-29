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
      <trh:head title="Gestión de documentación">
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

        <tr:form usesUpload="true" onsubmit="bloquearPantalla(this)">
        	<tr:panelHeader text="Gestión documentación" />

			<tr:messages />
			          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" inlineStyle="width:100%" >
            <tr:panelFormLayout>
              <f:facet name="footer">
                <h:panelGroup>
		           <tr:panelHorizontalLayout halign="center">
        		    <tr:commandButton text="Aceptar"
                             action="#{gestionDocumentacionBean.actualizarYSalir}" />
		            <tr:commandButton text="Cancelar"
	                         action="#{gestionDocumentacionBean.cancelar}" />

    		      </tr:panelHorizontalLayout>
                </h:panelGroup>
              </f:facet>
              <tr:panelLabelAndMessage label="Tipo de fichero">
	              <tr:selectOneChoice value="#{gestionDocumentacionBean.tipoFichero}" simple="true">
                    <f:selectItems value="#{gestionDocumentacionBean.listaTipoFichero}"/>
        	      </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

			  <tr:panelLabelAndMessage label="Descripción">
                <tr:inputText rows="2" columns="80" simple="true" value="#{gestionDocumentacionBean.descripcion}" />
              </tr:panelLabelAndMessage>

			  <tr:panelLabelAndMessage label="Nombre de archivo actual" rendered="#{!gestionDocumentacionBean.modoEdicion.agregar}">
                <tr:outputText value="#{gestionDocumentacionBean.nombre}" />
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="Plantilla">
                <tr:selectOneChoice value="#{gestionDocumentacionBean.plantilla}" simple="true">
                  <f:selectItems value="#{gestorListaBooleano.listaElementos}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Archivo">
					<tr:inputFile value="#{gestionDocumentacionBean.archivo}" simple="true" />
              </tr:panelLabelAndMessage>
            </tr:panelFormLayout>
          </tr:panelBox>

		</tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>