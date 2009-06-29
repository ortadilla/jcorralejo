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
    <f:loadBundle basename="mensajesCat" var="mensajesCat" />
    <f:loadBundle basename="mensajesCore" var="mensajesCore" />
    <f:loadBundle basename="mensajesOrg" var="mensajesOrg"/>
    <f:loadBundle basename="mensajesPed" var="mensajesPed"/>
    <trh:html>
      <trh:head title="#{mensajesPed['CABECERA_PAGINA_REGISTRO_ENTRADAS_MATERIAL']}">
        <meta http-equiv="Content-Type"
              content="text/html; "/>
        <trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
		</trh:script>
      </trh:head>
      <trh:body initialFocusId="inputNumeroMatricula">
       <geos:cabeceraPagina/>
        <tr:form defaultCommand="aceptarNumeroExpedicion" id="formRegistrarEntradasMaterial1" onsubmit="bloquearPantalla(this);">
          <tr:panelPage>
          	<tr:panelHeader  text="#{mensajesPed['REGISTRO_ENTRADAS_MATERIAL']}"/>            
            <tr:messages/>
            <tr:panelBox background="medium" inlineStyle="width: 100%;">
              <tr:panelFormLayout>
                <f:facet name="footer"/>
                <trh:tableLayout cellSpacing="10">
                  <trh:rowLayout>
                    <tr:outputText value="#{mensajesPed['MATRICULA_BULTO']}"/>
                    <tr:inputText
                                  id="inputNumeroMatricula"
                                  value="#{pedidos_registrarEntradasMaterialBean.numeroMatricula}"/>
                    <tr:outputText value="#{mensajesPed['NUMERO_EXPEDICION']}"/>
                    <tr:inputText
                                  rows="1"
                                  value="#{pedidos_registrarEntradasMaterialBean.avisoExpedicion}"
                                  id="avisoExpedicion"/>
                    <tr:commandLink action="#{pedidos_registrarEntradasMaterialBean.buscarNumExpediciones}">
                       <tr:image source="../imagenes/lupa3.gif"/>
                    </tr:commandLink>
                  </trh:rowLayout>
                  <trh:rowLayout>
                    <tr:outputText value="#{mensajesPed['PEDIDO']}"/>
                    <tr:panelHorizontalLayout>
                      <tr:inputText rows="1" columns="10" id="inputNumeroPedido"
                                    value="#{pedidos_registrarEntradasMaterialBean.numeroPedido}"
                                    valueChangeListener="#{pedidos_registrarEntradasMaterialBean.buscarPedido}"
                                    autoSubmit="true"/>
                      <tr:commandLink returnListener="#{pedidos_registrarEntradasMaterialBean.devolucionPopUpPedido}"
                                      disabled="false" id="commandLink" 
                                      binding="#{pedidos_registrarEntradasMaterialBinding.commandLink}">
                      </tr:commandLink>
                      <tr:spacer width="10" height="10"/>
                      <tr:commandLink action="#{pedidos_registrarEntradasMaterialBean.buscarPedidoConsulta}">
                        <tr:image source="../imagenes/lupa3.gif"/>
                      </tr:commandLink>
                    </tr:panelHorizontalLayout>
                  </trh:rowLayout>
                </trh:tableLayout>
              </tr:panelFormLayout>
              <tr:panelHorizontalLayout halign="right">
             		 <tr:commandButton text="#{mensajesCore['ACEPTAR']}"
                                      id="aceptarNumeroExpedicion"
                                      action="#{pedidos_registrarEntradasMaterialBean.buscarEntradasAlmacen}"/>
                    <tr:commandButton text="#{mensajesCore['LIMPIAR']}"
                                      action="#{pedidos_registrarEntradasMaterialBean.limpiarPagina}"
                                      id="limpiar"/>
              </tr:panelHorizontalLayout>
            </tr:panelBox> 
          </tr:panelPage>
        </tr:form>
              
        
        
        <tr:spacer width="10" height="10"/>
        <tr:form defaultCommand="botonAceptarCodigoEAN"
                 partialTriggers="inputNumeroPedido"
                 id="formRegistrarEntradasMaterial2">
         <tr:panelPage>
          <tr:panelBox inlineStyle="width: 100%;" background="medium" >
            <tr:panelFormLayout partialTriggers="inputNumeroPedido">
              <f:facet name="footer"/>
              <trh:tableLayout cellSpacing="10">
                <trh:rowLayout>
                  <tr:outputText value="#{mensajesPed['ALBARAN']}"/>
                  <tr:inputText columns="10"
                                value="#{pedidos_registrarEntradasMaterialBean.albaran}"
                                id="albaran"/>
                  <tr:outputText value="#{mensajesPed['FECHAALBARAN']}"/>
                  <tr:inputDate
                                      value="#{pedidos_registrarEntradasMaterialBean.fechaAlbaran}"
                                      id="fechaAlbaran"/>
                  <tr:outputText value="#{mensajesPed['TRANSPORTISTA']}"/>
                  <tr:panelHorizontalLayout>
                    <tr:inputText rows="1" columns="30" id="inputTransportista"
                                  value="#{pedidos_registrarEntradasMaterialBean.nombreTransportista}"/>
                    <tr:commandLink action="#{pedidos_registrarEntradasMaterialBean.buscarEmpresa}">
                      <tr:image source="../imagenes/lupa3.gif"/>
                    </tr:commandLink>
                  </tr:panelHorizontalLayout>
                </trh:rowLayout>
                <trh:rowLayout>
                  <tr:outputText value="#{mensajesPed['FECHAENTRADA']}"/>
                  <tr:inputDate
                                      value="#{pedidos_registrarEntradasMaterialBean.fechaEntrada}"
                                      id="fechaEntrada"/>
                  <tr:outputText value="#{mensajesPed['PERSONA_TRANSPORTISTA']}"/>
                  <tr:inputText columns="20"
                                value="#{pedidos_registrarEntradasMaterialBean.persona}"
                                id="transportista"/>
                  <tr:outputText value="#{mensajesPed['NUMEROBULTOS']}"/>
                  <tr:inputText
                                columns="10"
                                value="#{pedidos_registrarEntradasMaterialBean.nbultos}"
                                id="numeroBultos"/>
                </trh:rowLayout>
                <trh:rowLayout>
                  <tr:outputText value="#{mensajesPed['NUMERO_EXPEDICION']}"/>
                  <tr:inputText value="#{pedidos_registrarEntradasMaterialBean.avisoExpedicion}"
                                readOnly="#{pedidos_registrarEntradasMaterialBean.avisoExpedicionReadOnly}"
                                id="numeroExpedicion"/>
                </trh:rowLayout>
             
              </trh:tableLayout>
            </tr:panelFormLayout>
 				<tr:panelHorizontalLayout halign="right" >
   			           <tr:commandButton text="#{mensajesCore['LIMPIAR']}"
	                                action="#{pedidos_registrarEntradasMaterialBean.limpiarAlbaran}"
	                                id="limpiarAlbaran"/>
 	            </tr:panelHorizontalLayout>
          </tr:panelBox>
          </tr:panelPage>
          
          
          
          
          
          <tr:spacer width="10" height="10"/>
          <tr:panelBox inlineStyle="width: 100%;"
                       partialTriggers="inputNumeroPedido">
            <tr:panelFormLayout rendered="#{pedidos_registrarEntradasMaterialBean.mostrarPanelCodigoEAN}"
                          id="panelCodigoEAN">
              <f:facet name="footer"/>
              <tr:panelHorizontalLayout>
                <tr:inputText label="#{mensajesPed['CODIGO_EAN']}"
                              value="#{pedidos_registrarEntradasMaterialBean.codigoEANArticulo}"
                              columns="50" id="codigoEANArticulo"
                              onkeypress="leer(event, this)"/>
                <tr:spacer width="10" height="10"/>
                <tr:commandButton text="#{mensajesCore['ACEPTAR']}"
                                  action="#{pedidos_registrarEntradasMaterialBean.buscarArticulo}"
                                  id="botonAceptarCodigoEAN"/>
                <tr:spacer width="10" height="10"/>
                <tr:commandButton text="#{mensajesCore['LIMPIAR']}"
                                  action="#{pedidos_registrarEntradasMaterialBean.borrarCodigoEan}"
                                  id="limpiarCodigoEan"/>
                </tr:panelHorizontalLayout>   
            </tr:panelFormLayout>
            <tr:spacer width="10" height="10"/>
            <tr:panelHorizontalLayout> 
            <tr:commandButton text="#{mensajesCore['AGREGAR']} #{mensajesPed['LINEAPEDIDO']}"
                                  action="#{pedidos_registrarEntradasMaterialBean.buscarPedidoLinea}"
                                  id="buscarPedidoLinea"/>
            <tr:spacer width="10" height="10"/>
            <!-- 
            <tr:commandButton text="#{mensajesCore['INCIDENCIAS']}"
                                  action="#{pedidos_registrarEntradasMaterialBean.registrarIncidencias}"
                                  rendered="#{pedidos_registrarEntradasMaterialBean.mostrarBotonIncidencias}"/>
            -->
            </tr:panelHorizontalLayout>
            <tr:spacer width="10" height="10"/>
             <tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}" 
					  rowBandingInterval="1"
					  columnBandingInterval="0"
                      partialTriggers="inputNumeroPedido" 
                      var="var" rows="10"
                      binding="#{pedidos_registrarEntradasMaterialBinding.tablaResultados}"
                      id="tablaResultados"
                      value="#{pedidos_registrarEntradasMaterialBean.listaResultados}"
                      immediate="false"
                      rowSelection="multiple">
<!--              <f:facet name="selection">-->
<!--                <tr:tableSelectMany/>-->
<!--              </f:facet>-->


				<tr:column sortable = "false" headerText="">
			  		<tr:commandLink action="#{pedidos_registrarEntradasMaterialBean.consultarIncidencias}" 
			  			rendered="#{var.hayIncidencias}" shortDesc="Hay incidencias">
                      <tr:image source="../imagenes/exclamacion.gif" shortDesc="Hay incidencias" />
                    </tr:commandLink>
			  </tr:column>
			  
			  
			  
              <tr:column sortable="false"
                         headerText="#{mensajesCat['ARTICULO']}">
                <tr:outputText value="#{var.articulo}"/>
              </tr:column>
              
              <tr:column sortable="false" headerText="Incidencias">
	              <tr:outputText value="#{var.incidencias}" rendered="false" id="incidenciasLinea" shortDesc="Agregar Incidencias"/>
	              <tr:commandLink 
		              action="#{pedidos_registrarEntradasMaterialBean.linkIncidencias}" 
		              launchListener="#{pedidos_registrarEntradasMaterialBean.asociarIncidenciasLinea}"
		              returnListener="#{pedidos_registrarEntradasMaterialBean.volverRegistrarIncidencias}"
		              useWindow="true" partialSubmit="true"
		              windowHeight="450" windowWidth="500"
 					  inlineStyle="vertical-align:middle;" shortDesc="Agregar Incidencias">
									<h:graphicImage url="/imagenes/agregar.gif" 
										style="border-color:rgb(255,255,255);" alt="Incidencias"
										title="#{pedidos.incidencias}" longdesc="Agregar Incidencias"/>
				  </tr:commandLink>
              </tr:column>
              
              
              <tr:column sortable="false" headerText="#{mensajesPed['EAN']}">
                <tr:outputText value="#{var.EANProducto}"/>
              </tr:column>
              <tr:column sortable="false"
                         headerText="#{mensajesPed['CANTIDADPREVISTA']}">
                <tr:outputText value="#{var.cantidadEnviada}"/>
              </tr:column>
              <tr:column sortable="false"
                         headerText="#{mensajesPed['CANTIDADACEPTADA']}">
                <tr:inputText value="#{var.cantidadRecibida}" rows="1"
                              columns="5" autoSubmit="true"
                              valueChangeListener="#{pedidos_registrarEntradasMaterialBean.actualizarImporte}"
                              id="inputCantidadRecibida" immediate="false"/>
              </tr:column>
              <tr:column sortable="false"
                         headerText="#{mensajesPed['CANTIDADDEVUELTA']}">
                <tr:inputText value="#{var.cantidadDevuelta}" columns="5"
                              partialTriggers="inputCantidadRecibida"
                              id="inputCantidadDevuelta"/>
              </tr:column>
              <tr:column sortable="false"
                         headerText="#{mensajesPed['FECHACADUCIDAD']}"
                         noWrap="true">
                <tr:inputDate value="#{var.fechaCaducidad}"
                				id="fechaCaducidad"/>
              </tr:column>
              <tr:column sortable="false" headerText="#{mensajesPed['LOTE']}">
                <tr:inputText value="#{var.lote}" columns="5"/>
              </tr:column>
              <tr:column sortable="false" headerText="#{mensajesPed['SERIE']}">
                <tr:inputText value="#{var.numeroSerie}" columns="5" id="serie"/>
              </tr:column>
              <tr:column sortable="false" headerText="#{mensajesCat['PRECIO']}">
                <tr:outputText value="#{var.precio}">
                  <tr:convertNumber/>
                </tr:outputText>
              </tr:column>
              <tr:column sortable="false"
                         headerText="#{mensajesPed['IMPORTE']}">
                <tr:outputText value="#{var.importeLinea}"
                               partialTriggers="inputCantidadRecibida">
                  <tr:convertNumber/>
                </tr:outputText>
              </tr:column>
              <tr:column sortable="false"
                         headerText="#{mensajesPed['SSCCBULTO']}">
                <tr:outputText value="#{var.ssccBulto}"/>
              </tr:column>
              	
				
            </tr:table>
            <tr:spacer width="10" height="10"/>
            <tr:panelHorizontalLayout>
            
			  <tr:spacer width="10" height="10" />
                <tr:commandLink id="mostrarDocsAsociados"
                                rendered="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[26] != null}"
                                shortDesc="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[26]}"
                                actionListener="#{organizacion_seleccionarDocumentoPerfilBean.guardarEntidad}"
                                action="dialog:mostrarDocumentacion"
                                useWindow="true" windowWidth="600" windowHeight="300">
                    <f:attribute name="entidadMostrada" value="26"/>
                    <tr:image source="/imagenes/info_20.png"/>
                </tr:commandLink>       
              <tr:commandButton text="#{mensajesCore['ACEPTAR']}"
                                action="#{pedidos_registrarEntradasMaterialBean.guardarEntradaAlmacen}"
                                returnListener="#{pedidos_registrarEntradasMaterialBean.confirmarAlbaran}"
                                partialSubmit="true" windowHeight="450"
                                windowWidth="450" useWindow="true"
                                id="aceptarTabla"/>
              <tr:spacer width="10" height="10"/>
              <tr:commandButton text="#{mensajesCore['CANCELAR']}"
                                action="#{pedidos_registrarEntradasMaterialBean.cancelar}"
                                id="cancelarTabla"/>
            </tr:panelHorizontalLayout>
            <tr:spacer width="10" height="10"/>
          </tr:panelBox>
        </tr:form>
        <trh:script text="function leer(e, obj)
                            {
                            var aux=(document.all) ? event.keyCode : e.which; 
                               var letra = String.fromCharCode(aux);
                                if(aux == 13 || aux == 29 || aux == 8596){
	                                if (aux == 13){
    	                               obj.value = obj.value + letra;
        	                           if (verificaLineaRepetida(obj)) {
            	                            alert('Ha introducido una linea repetida por lo que va a ser borrada. Por favor intentelo de nuevo.');
                	                   }
                    	            } else if (aux == 29 || aux == 8596){
                        	            obj.value = obj.value + '#{pedidos_registrarEntradasMaterialBean.separador}';
                            	    } else {
                                	    obj.value = obj.value + letra;
                                	}                               
	                                (document.all) ? event.returnValue = false : e.preventDefault();
	                            }
                            }
                           
                            function verificaLineaRepetida(obj)
                            {
                                var contenido = obj.value;
                                var res = false;
                                if (contenido.indexOf('\n') > 0) {
                                    var pos,inipos;
                                    pos = contenido.indexOf('\n');
                                   inipos = 0;
                                   while (pos > 0) {
                                       linea = contenido.substring(inipos,pos);
                                       if (contenido.lastIndexOf(linea) > 0) {
                                               pos = 0;
                                               res = true;
                                               contenido = obj.value;
                                               contenido = contenido.substring(0,contenido.length - linea.length - 1);
                                               obj.value = contenido;
                                       } else {
                                               contenido = contenido.substring(pos + 1);
                                               pos = contenido.indexOf('\n');
                                       }
                                    }
                                }
                                return res;
                            }
                            "/>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
