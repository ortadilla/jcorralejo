<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:geos="http://www.hp-cda.com/adf/faces">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=windows-1252"/>
  <f:view>
    <f:loadBundle basename="mensajesCore" var="resCore"/>
    <trh:html>
      <trh:head title="#{core_mtoGenericoCompararBean.objRespaldo.titulo}">
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"/>
         
        <trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
		</trh:script>
		
		<link rel="stylesheet" type="text/css" href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />				
		
		<tr:outputText escape="false" value="#{htmlHead}"/>
		
      </trh:head>
      <trh:body>
      			
		<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
<!--		<tr:statusIndicator id="indicador">-->
<!--			<f:facet name="busy">-->
<!--				<f:verbatim>-->
<!--						<div id="divEspera">-->
<!--							<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>-->
<!--						</div>-->
<!--				</f:verbatim>-->
<!--			</f:facet>-->
<!--			<f:facet name="ready">-->
<!--			</f:facet>-->
<!--		</tr:statusIndicator>-->
			
		<!-- Cabecerá estándar de las páginas SIGLO -->		
      	<geos:cabeceraPagina/>
      	
        <tr:form defaultCommand="botonBuscar" id="formMtoGenerico" onsubmit="bloquearPantalla(this);">
          <tr:panelPage>          	
	          <tr:panelHeader text="#{core_mtoGenericoCompararBean.objRespaldo.titulo}"/>
           		<f:facet name="messages">
					<tr:messages />
				</f:facet>              
				
              <tr:panelBox text="#{core_mtoGenericoCompararBean.objRespaldo.tituloPanel}"
                           background="medium" 
                           inlineStyle="width:100%">

                <trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">

                	<trh:rowLayout>
						<!--  Para mostrar los datos de la primera entidad  -->
						<trh:cellFormat valign="top">
							<tr:panelHorizontalLayout inlineStyle="width:100%" id="panelCamposEntidad1" binding="#{core_mtoGenericoCompararBinding.panelCamposEntidad1}"/>
						</trh:cellFormat>	 
					</trh:rowLayout>
					<trh:rowLayout>
						<!-- Para el texto con la leyenda -->
	        	     	<tr:panelFormLayout>
	              			<tr:outputText value="* #{resCore['INFO_CAMPOS_ROJOS']}"  styleClass="estiloDiferenteValorCambiado"/>
	              			<tr:outputText value="* #{resCore['INFO_CAMPOS_VERDES']}" styleClass="estiloDiferenteValorOriginal"/>
	             		</tr:panelFormLayout>
					</trh:rowLayout>
					
					
					
					</trh:tableLayout>
					<tr:panelHorizontalLayout rendered="#{core_mtoGenericoCompararBean.tablaRelacionRenderizable}" inlineStyle="width:100%" id="panelRelaciones" binding="#{core_mtoGenericoCompararBinding.panelRelaciones}"/>
					<tr:spacer height="15"/>				 
                      	<tr:panelHorizontalLayout halign="right">
	                      	<tr:commandButton text="#{resCore['VALIDAR']}"
	                                          id="botonValidar"
	  										  accessKey="#{resCore['VALIDAR_TA']}"   
	  										  rendered="#{core_mtoGenericoCompararBean.validable}"                                      
	                                          action="#{core_mtoGenericoCompararBean.accionValidar}"
	                                          returnListener="#{core_mtoGenericoCompararBean.returnConfirmValidar}"
	                                          useWindow="true"
	                        				  windowHeight="300"
	                        				  windowWidth="600"/>
	                        <tr:commandButton text="#{resCore['ACEPTAR_CAMBIOS']}"
	                                          id="botonAceptar"
	  										  accessKey="#{resCore['ACEPTAR_CAMBIOS_TA']}"                                         
	  										  rendered="#{core_mtoGenericoCompararBean.aceptable}"                                      
	                                          action="#{core_mtoGenericoCompararBean.accionAceptarCambios}"
	                                          returnListener="#{core_mtoGenericoCompararBean.returnConfirmConfirmar}"
	                                          useWindow="true"
	                        				  windowHeight="300"
	                        				  windowWidth="600"/>
	                        <tr:commandButton text="#{resCore['RECHAZAR']}"
	                        				  useWindow="true"
	                        				  partialSubmit="true"
	                        				  windowHeight="300"
	                        				  windowWidth="300"
	                                          id="botonRechazar"
	  										  accessKey="#{resCore['RECHAZAR_TA']}"   
	  										  rendered="#{core_mtoGenericoCompararBean.aceptable || core_mtoGenericoCompararBean.validable}"                                      
	  										  returnListener="#{core_mtoGenericoCompararBean.accionPostPopUpRechazo}"                          
	                                          action="#{core_mtoGenericoCompararBean.accionRechazar}"/>
<!--                            <tr:commandButton text="#{resCore['BORRAR']}"-->
<!--	                                          id="botonBorrar"-->
<!--	                                          rendered="#{core_mtoGenericoCompararBean.aceptable}"-->
<!--	  										  accessKey="#{resCore['BORRAR_TA']}"-->
<!--	                                          action="#{core_mtoGenericoCompararBean.accionBorrar}"/>-->
<!--	                        <tr:commandButton text="#{resCore['NOTIFICAR']}"-->
<!--	                                          id="botonNotificar"-->
<!--	                                          rendered="false"-->
<!--	  										  accessKey="#{resCore['NOTIFICAR_TA']}"-->
<!--	                                          action="#{core_mtoGenericoCompararBean.accionNotificar}"/>-->
                            <tr:spacer width="15"/>
                            <tr:commandButton text="#{resCore['DETALLES']}" 
                                              accessKey="#{resCore['DETALLES_TA']}"
                                              id="botonDetalles" 
                                              action="#{core_mtoGenericoCompararBean.accionDetalles}"/>                           
	                        <tr:commandButton text="#{resCore['VOLVER']}"
	                                          id="botonCancelar"
	  										  accessKey="#{resCore['VOLVER_TA']}"                         
	                                          action="#{core_mtoGenericoCompararBean.accionCancelar}"/>
                        </tr:panelHorizontalLayout>

              </tr:panelBox>
              
              <f:subview id="detallesSolicitud" rendered="#{core_solicitud_detalles != null}"> <!-- OJO: Cte. definida en NombresBean del core-->
            	<jsp:include page="/core/detallesSolicitud.jsp" />
            </f:subview>
            
          </tr:panelPage>
        </tr:form>
    
        <tr:panelHorizontalLayout inlineStyle="width: 100%;">
			<tr:outputText escape="false" value="#{htmlPie}"/>
		</tr:panelHorizontalLayout>
			
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
