<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/strict.dtd"
              doctype-public="-//W3C//DTD HTML 4.01//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>

  <f:view>
    <trh:html>
      <trh:head title="Carga de necesidades de contratación" >
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
			<tr:panelHeader   text="Carga de necesidades de contratación" />
			<tr:messages />
            <tr:spacer width="10" height="10"/>
			<tr:panelBox background="medium" id="bloqueFicheroCarga" inlineStyle="width: 100%;">
	    	  <tr:panelFormLayout id="panelPage1">
   	   		   <tr:spacer width="10" height="10"/>
    	        	<tr:panelLabelAndMessage label="Archivo ">
        	          <tr:inputFile columns="80" valueChangeListener="#{cargaNecesidadesContratacion.cambiaInputFile}"
            	                    binding="#{cargaNecesidadesContratacion.componenteInputFile}"
                	                simple="true"/>
                	                 <tr:commandButton text="Aceptar"
                               action="#{cargaNecesidadesContratacion.aceptar}"/>
	                </tr:panelLabelAndMessage>
	                <tr:commandLink onclick="noBloquearPantalla()" id="descarga" text="Necesita un fichero xml con una estructura definida, que puede consultar aquí"
                               actionListener="#{muestraFicheroAyudaCargaMasiva.descargaFichero}"/>
   	   		   <tr:spacer width="10" height="10"/>
    	      </tr:panelFormLayout>
    	   </tr:panelBox>
           <tr:separator/>
		   <tr:spacer width="10" height="10"/>
           <tr:panelHorizontalLayout halign="center">

             <tr:commandButton text="Volver"
                               action="#{cargaNecesidadesContratacion.cancelar}"/>
           </tr:panelHorizontalLayout>
        </tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>