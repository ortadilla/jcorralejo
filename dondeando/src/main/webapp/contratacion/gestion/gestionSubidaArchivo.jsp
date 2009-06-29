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
      <trh:head title="Adjuntar un fichero al expediente">
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
          <tr:panelHeader text="Adjuntar un fichero al expediente"/>
          <tr:messages />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  >
            <tr:panelFormLayout partialTriggers="comboTipoFichero">
              <f:facet name="footer">
                <h:panelGroup>
                  <tr:panelHorizontalLayout halign="right">
                    <tr:commandButton text="Aceptar" action="#{gestionSubidaArchivo.aceptar}" />
                    <tr:commandButton text="Cancelar" action="#{gestionSubidaArchivo.cancelar}" />
                  </tr:panelHorizontalLayout>
                </h:panelGroup>
              </f:facet>

              <tr:panelLabelAndMessage label="Tipo de Fichero:" partialTriggers="comboTipoFichero">
	              <tr:selectOneChoice id="comboTipoFichero" value="#{gestionSubidaArchivo.tipoFichero}" autoSubmit="true" valueChangeListener="#{gestionSubidaArchivo.actualizaTipoFichero}">
                    <f:selectItems value="#{gestionSubidaArchivo.listaTipoFichero}"/>
        	      </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

			  <tr:panelLabelAndMessage label="Nombre del fichero actual:" rendered="#{gestionSubidaArchivo.editar}">
                <tr:outputText value="#{gestionSubidaArchivo.nombreFichero}" />
              </tr:panelLabelAndMessage>

				<!-- binding="#{gestionSubidaArchivoBinding.panelLabelRestringido}" -->

			  <tr:panelLabelAndMessage rendered="#{gestionSubidaArchivo.ficheroPpt}" label="Restringir el acceso a este documento:" partialTriggers="comboTipoFichero">
	              <tr:selectOneChoice value="#{gestionSubidaArchivo.restringido}">
                    <f:selectItems value="#{gestionSubidaArchivo.listaElementosSiNo}"/>
        	      </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="Anexo:" rendered="#{gestionSubidaArchivo.ficheroAnexo}" partialTriggers="comboTipoFichero">
	              <tr:selectOneChoice value="#{gestionSubidaArchivo.anexo}" partialTriggers="comboTipoFichero">
                    <f:selectItems value="#{gestionSubidaArchivo.listaAnexos}"/>
        	      </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

			  <tr:panelLabelAndMessage label="Descripción:" rendered="#{gestionSubidaArchivo.ficheroAnexo}" partialTriggers="comboTipoFichero">
                <tr:inputText rows="10" columns="80" value="#{gestionSubidaArchivo.descripcion}" />
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="Fichero:">
					<tr:inputFile value="#{gestionSubidaArchivo.archivo}" />
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="Guardar en histórico:" rendered="#{gestionSubidaArchivo.mantenerHistorico}">
	              <tr:selectOneChoice value="#{gestionSubidaArchivo.historico}">
                    <f:selectItems value="#{gestionSubidaArchivo.listaElementosSiNo}"/>
        	      </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

              <tr:table var="ficheroHistorico" value="#{gestionSubidaArchivo.ficherosEnHistorico}"
              emptyText="No existen ficheros en el historico"
        	   rows="5" rendered="#{gestionSubidaArchivo.hayFicherosEnHistorico}">

                <f:facet name="header">
                    <tr:outputText value="Lista de ficheros en histórico" />
                </f:facet>

       			<tr:column headerText="Fecha">
          			<tr:outputText value="#{ficheroHistorico.fechaAlta}"/>
       			</tr:column>

       			<tr:column headerText="Fichero">
	       			<tr:commandLink onclick="noBloquearPantalla()" text="#{ficheroHistorico.nombre}" actionListener="#{muestraFicherosExpediente.descargaFichero}">
						<f:param value="#{ficheroHistorico.id}" name="idFicheroEnWorkflow"/>
						<f:param value="#{ficheroHistorico.nombre}" name="nombreFichero"/>
					</tr:commandLink>
       			</tr:column>

			  </tr:table>

            </tr:panelFormLayout>
          </tr:panelBox>
        </tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>