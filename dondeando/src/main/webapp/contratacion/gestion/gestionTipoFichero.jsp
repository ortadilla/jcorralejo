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
      <trh:head title="Gestión de tipos de documentos">
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
          <tr:panelHeader text="Gestión de tipos de documentos"/>
          <tr:messages />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  inlineStyle="width:100%">
            <tr:panelFormLayout>
              <f:facet name="footer">
                <h:panelGroup>
                  <tr:panelHorizontalLayout halign="right">
                    <tr:commandButton text="Aceptar" action="#{gestionTipoFichero.actualizarYSalir}"/>
                    <tr:commandButton text="Cancelar" action="#{gestionTipoFichero.cancelar}" immediate="true" />
                  </tr:panelHorizontalLayout>
                </h:panelGroup>
              </f:facet>
              <tr:panelLabelAndMessage label="descripcion">
                <tr:inputText value="#{gestionTipoFichero.descripcion}" simple="true"  />
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="prefijo">
                <tr:inputText value="#{gestionTipoFichero.prefijo}" simple="true" />
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="¿ Es Anexo ?">
					<tr:selectOneChoice value="#{gestionTipoFichero.esAnexo}" simple="true" >
                    	<f:selectItems value="#{gestionTipoFichero.listaElementosSiNo}"/>
        	      	</tr:selectOneChoice>
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="¿ Es PPT ?">
					<tr:selectOneChoice value="#{gestionTipoFichero.esPPT}" simple="true" >
                    	<f:selectItems value="#{gestionTipoFichero.listaElementosSiNo}"/>
        	      	</tr:selectOneChoice>
              </tr:panelLabelAndMessage>


			<tr:panelLabelAndMessage label="seccion">
					<tr:selectOneChoice value="#{gestionTipoFichero.seccion}" simple="true" >
                    	<f:selectItems value="#{gestionTipoFichero.listaSecciones}"/>
        	      	</tr:selectOneChoice>
              </tr:panelLabelAndMessage>

			<tr:panelLabelAndMessage label="¿ Es Documentación ?">
					<tr:selectOneChoice value="#{gestionTipoFichero.esDocumentacion}" simple="true" >
                    	<f:selectItems value="#{gestionTipoFichero.listaElementosSiNo}"/>
        	      	</tr:selectOneChoice>
              </tr:panelLabelAndMessage>
              
              <tr:panelLabelAndMessage label="¿ Nombre fichero Fijo ?">
					<tr:selectOneChoice value="#{gestionTipoFichero.esNombreFijo}" simple="true" >
                    	<f:selectItems value="#{gestorListaBooleano.listaElementos}"/>
        	      	</tr:selectOneChoice>
              </tr:panelLabelAndMessage>

            </tr:panelFormLayout>
          </tr:panelBox>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>