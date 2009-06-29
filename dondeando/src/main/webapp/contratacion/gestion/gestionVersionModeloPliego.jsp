<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:c="http://java.sun.com/jstl/core">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=ISO-8859-15" />
	<f:view>
		<trh:html>
		<trh:head title="Gestión de versión de modelo de pliego">
			<meta http-equiv="Content-Type"
				content="text/html; charset=ISO-8859-15" />
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
     <tr:panelHeader text="Detalles de la versión del modelo de pliego" >
      <tr:messages/>
      <tr:panelBox text="Seleciona la versión de la cual quieres copiar la estructura" rendered="#{configuracionGestionVersionModeloPliego.clonando}"
          background="medium"         >
       <tr:panelFormLayout>
        <f:facet name="footer"/>
        <tr:tree value="#{gestionVersionModeloPliegoBean.modeloArbol}" var="nodo"  >
         <f:facet name="nodeStamp">
          <h:panelGroup>

                <tr:selectBooleanRadio text="#{nodo.descripcion}" group="Arbol" valueChangeListener="#{gestionVersionModeloPliegoBean.cambioVersion}"
                rendered="#{nodo.hoja}" autoSubmit="true" label="#{nodo.codigo}" simple="true"  >
                </tr:selectBooleanRadio>
                <tr:outputText value="#{nodo.descripcion}"  rendered="#{!nodo.hoja}"  ></tr:outputText>
          </h:panelGroup>
         </f:facet>
        </tr:tree>
       </tr:panelFormLayout>
      </tr:panelBox>
      <tr:panelBox  background="medium">
       <tr:panelFormLayout>
        <f:facet name="footer"/>
              <tr:panelLabelAndMessage label="Fecha inicio validez" >
                <tr:inputDate value="#{gestionVersionModeloPliegoBean.fechaInicioValidez}" simple="true" />
              </tr:panelLabelAndMessage>
        <tr:panelLabelAndMessage label="Fecha fin validez">
         <tr:inputDate value="#{gestionVersionModeloPliegoBean.fechaFinValidez}" simple="true" />
        </tr:panelLabelAndMessage>
        <tr:panelLabelAndMessage label="Procedimiento">
         <tr:selectOneChoice value="#{gestionVersionModeloPliegoBean.identificadorProcedimiento}" simple="true" >
          <f:selectItems value="#{gestorListaProcedimientosWorkflowExpedientes.listaElementos}"/>
         </tr:selectOneChoice>
        </tr:panelLabelAndMessage>
        <tr:spacer width="10" height="10"/>
        <tr:panelHorizontalLayout halign="center">
         <tr:commandButton text="Aceptar"
                           action="#{gestionVersionModeloPliegoBean.actualizarYSalir}"
                           rendered="#{!gestionVersionModeloPliegoBean.modoEdicion.ver}"/>
         <tr:commandButton text="Cancelar" immediate="true"
                           action="#{gestionVersionModeloPliegoBean.cancelar}"/>
        </tr:panelHorizontalLayout>
       </tr:panelFormLayout>
      </tr:panelBox>
     </tr:panelHeader>
    </h:form>
   </trh:body>
		</trh:html>
	</f:view>
</jsp:root>
