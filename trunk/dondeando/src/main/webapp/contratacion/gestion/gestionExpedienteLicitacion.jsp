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
   <trh:head title="Gestión de expediente de contratación">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15"/>
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
     <tr:panelHeader text="Gestión de expediente de contratación"/>
     <tr:messages/>
     <tr:spacer width="10" height="10"/>

      <tr:panelBox background="medium" text="Cabecera" inlineStyle="width: 100%;" contentStyle="width: 100%;"  >

	  <tr:panelBorderLayout inlineStyle="width: 100%;" >
              <f:facet name="left">
                <h:panelGroup>
                  <tr:panelFormLayout inlineStyle="width: 100%;"  >
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Número del expediente:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.numeroExpediente}" />
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Fecha de inicio de resolución:">
                      <tr:inputDate value="#{frmCabeceraExpedienteBean.fechaResolucion}"
                                          readOnly="true"
                                          inlineStyle="border-style:none; font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Objeto del contrato:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.objetoContrato}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="CCA:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.codigoContratoAdministrativo}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Confeccionado por:">
                      <tr:outputText value="(usuario)"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:separator/>
                  </tr:panelFormLayout>
                  <tr:panelFormLayout labelWidth="100" fieldWidth="300">
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Órgano gestor:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionCentro}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                  </tr:panelFormLayout>
                </h:panelGroup>
              </f:facet>
              <f:facet name="right">
                <h:panelGroup>
                  <tr:panelFormLayout fieldWidth="310" labelWidth="160" partialTriggers="cambioTipoLicitacion">
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Tipo de contrato:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionTipoContrato}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Procedimiento de contratación:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionProcedimientoContratacion}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Forma de adjudicación:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionFormaAdjudicacion}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Modelo de Pliego:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionModeloPliego}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Tipo de licitación:" >
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionTipoLicitacion}"
                                     inlineStyle="font-size: small;"/>
                        <tr:spacer width="10"></tr:spacer>
						<tr:commandLink  id="cambioTipoLicitacion"
								action="#{dialogoSeleccionSimpleGestorLista.ejecuta}"
								actionListener="#{dialogoSeleccionSimpleGestorLista.configuraComponente}"
              					rendered="#{configuracionCabeceraExpedienteContratacion.botonCambiarTipoLicitacionVisible}"
              					returnListener="#{frmCabeceraExpedienteBean.volverCambioTipoLicitacion}"
              					>
              					<tr:setActionListener from="#{gestorListaTipoLicitacion}" to="#{dialogoSeleccionSimpleGestorLista.gestorLista}"/>
              					<tr:setActionListener from="#{'Seleccione el nuevo tipo de licitacion'}" to="#{dialogoSeleccionSimpleGestorLista.titulo}"/>
                           <tr:image source ="/imagenes/editar_4_16.png" shortDesc="Cambiar el tipo de licitación" />
                        </tr:commandLink>
                    </tr:panelLabelAndMessage>
                    <tr:separator/>
                  </tr:panelFormLayout>
                  <tr:panelFormLayout fieldWidth="450" labelWidth="30">
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Estado:">
                      <tr:iterator rows="25" var="estados"
                                   value="#{frmCabeceraExpedienteBean.estadosActuales}">
                        <tr:panelList>
                          <tr:outputText value="#{estados.label}"
                                         inlineStyle="font-size: 12px;"/>
                        </tr:panelList>
                      </tr:iterator>
                    </tr:panelLabelAndMessage>
                  </tr:panelFormLayout>
                </h:panelGroup>
              </f:facet>
              <f:facet name="top"/>
              <f:facet name="innerRight">
                <tr:panelFormLayout>
                  <f:facet name="footer"/>
                </tr:panelFormLayout>
              </f:facet>
            </tr:panelBorderLayout>

            <tr:panelHorizontalLayout halign="right">
              <f:facet name="footer"/>

              <tr:commandLink  action="#{gestionarNecesidadesExpediente.ejecuta}" >
 					<tr:setActionListener from="#{'atrasLicitacion'}" to="#{frmCabeceraExpedienteBean.outcomeVolverCabeceraExpediente}" />
 					<tr:image source ="/contratacion/imagenes/necesidades.gif" shortDesc="Necesidades" />
              </tr:commandLink>
              <tr:spacer width="30"></tr:spacer>


              <tr:commandLink  action="#{archivarPasivo.ejecuta}"
              		rendered="#{configuracionCabeceraExpedienteContratacion.botonArchivarEnPasivoVisible}">
                 	 <tr:image source ="/contratacion/imagenes/aep.gif" shortDesc="Archivar en pasivo" />
              </tr:commandLink>
              <tr:spacer width="30" rendered="#{configuracionCabeceraExpedienteContratacion.botonArchivarEnPasivoVisible}"></tr:spacer>

              <tr:commandLink
              					action="#{frmCabeceraExpedienteBean.verHistorial}"
              					>
					<tr:setActionListener from="#{'gestionExpedienteLicitacion'}" to="#{frmCabeceraExpedienteBean.outcomeVolverCabeceraExpediente}" />

				  <tr:image source ="/contratacion/imagenes/historial.gif" shortDesc="Historial" />
              </tr:commandLink>
              <tr:spacer width="30"></tr:spacer>
              <tr:commandLink onclick="noBloquearPantalla()" actionListener="#{lanzadorPDFCuadroResumen.lanzarPDF}"
              				  rendered="#{!frmCabeceraExpedienteBean.pliegoEspecifico}">
				<tr:image source ="/contratacion/imagenes/imprimir.gif" shortDesc="Imprimir cuadro resumen" />
				<tr:setActionListener from="#{frmCabeceraExpedienteBean.dibujanteImprimirCuadroResumen}" to="#{pageFlowScope.dibujanteCuadroResumen}"/>
              </tr:commandLink>
              <tr:spacer width="30" rendered="#{!frmCabeceraExpedienteBean.pliegoEspecifico}"></tr:spacer>
		      <tr:commandLink useWindow="true"
                        		action="#{frmCabeceraExpedienteBean.accederLogPorEmpresas}">
				<tr:image source ="/contratacion/imagenes/acceso_empresas.gif" shortDesc="Accesos a expediente por empresas" />
		      </tr:commandLink>
		      <tr:spacer width="30"></tr:spacer>
		      <tr:commandLink useWindow="true"
		      					action="#{frmCabeceraExpedienteBean.accederLogPorCentros}">
		      <tr:image source ="/contratacion/imagenes/acceso_centros.gif" shortDesc="Accesos a expediente por centros" />
		      </tr:commandLink>
		      <tr:spacer width="30"></tr:spacer>
				<tr:commandLink useWindow="true"
					    action="#{frmCabeceraExpedienteBean.consultarDocumentacionAsociada}"
						launchListener="#{frmCabeceraExpedienteBean.pasarConsultarDocumentacionAsociada}"
						>
					<tr:image source ="/contratacion/imagenes/documentos.gif" shortDesc="Documentación asociada" />
				</tr:commandLink>
				<tr:spacer width="20"></tr:spacer>
            </tr:panelHorizontalLayout>
          </tr:panelBox>

     <tr:panelBox  background="medium" id="bloqueAsesoriaJuridica"  inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  text="Asesoría jurídica"
                  rendered="#{configuracionGestionExpedienteContratacion.bloqueAsesoriaJuridicaVisible}">
      <tr:panelFormLayout labelWidth="150">
       <f:facet name="footer"/>
       <tr:panelLabelAndMessage label="Fecha de informe de asesoría jurídica">
        <tr:inputDate value="#{gestionExpedienteContratacionBean.fechaInformeAJ}"
                            disabled="#{!configuracionGestionExpedienteContratacion.campoFechaAsesoriaJuridicaEditable}"/>
       </tr:panelLabelAndMessage>
      </tr:panelFormLayout>
     </tr:panelBox>
     <tr:panelBox  background="medium" text="Intervención" inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  id="intervenciones"
                  rendered="#{configuracionGestionExpedienteContratacion.bloqueIntervencionesVisible}">
      <tr:panelFormLayout labelWidth="150">
       <f:facet name="footer"/>
       <tr:panelLabelAndMessage label="Fecha de fiscalización">
        <tr:inputDate value="#{gestionExpedienteContratacionBean.fechaIntervencion}"
                            disabled="#{!configuracionGestionExpedienteContratacion.campoFechaIntervencionEditable}"/>
       </tr:panelLabelAndMessage>
      </tr:panelFormLayout>
     </tr:panelBox>
     <tr:panelBox  background="medium"  inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  text="Acciones para el publicador"
                  rendered="#{configuracionGestionExpedienteContratacion.bloquePublicadorVisible}"
                  >
      <tr:commandButton text="Validar datos del anuncio"
                        rendered="#{configuracionGestionExpedienteContratacion.botonValidarDatosAnuncioVisible}"
                        action="#{validarAnuncio.ejecuta}"/>
      <tr:spacer width="10" height="10"/>
      <tr:panelFormLayout>
      	<tr:outputText rendered="#{gestionExpedienteContratacionBean.anuncioValidado}"
      		value="Los datos del anuncio tienen el visto bueno del publicador"  ></tr:outputText>
      	<tr:outputText rendered="#{!gestionExpedienteContratacionBean.anuncioValidado}"
      		 value="Los datos del anuncio aun no tienen el visto bueno del publicador"  ></tr:outputText>
	  </tr:panelFormLayout>
      <tr:spacer width="10" height="10"/>
      <tr:panelHeader text="B.O.J.A." partialTriggers="generarAnuncioBOJA editarAnuncioBOJA" >
      	<tr:switcher facetName="#{gestionAnuncioBoja.anuncioGenerado ?'generado':'nogenerado'}">
      	<f:facet name="generado">
		  <tr:group>
          	  <tr:outputText value="Plazo, en días naturales, para presentación de ofertas (BOJA): " />
          	  <tr:outputText value="#{gestionAnuncioBoja.plazoPresentacionOfertas}" />
	          <tr:spacer height="20" width="20" />
	          <tr:panelBorderLayout partialTriggers="generarAnuncioBOJA editarAnuncioBOJA">
	          	<f:facet name="innerStart">
		     	   <tr:commandButton text="Editar anuncio para BOJA"
		                          action="#{gestionAnuncioBoja.editarAnuncio}"
		                          launchListener="#{gestionAnuncioBoja.pasarParametrosDatosAnuncio}"
		                          returnListener="#{gestionAnuncioBoja.retornarDatosAnuncio}"
		                          useWindow="true" windowHeight="560" windowWidth="700"
		                          partialSubmit="true" id="editarAnuncioBOJA"
		                          rendered="#{configuracionGestionExpedienteContratacion.botonEditarAnuncioBojaVisible}"/>
	          	</f:facet>
	          	<f:facet name="left">
			      	<tr:commandLink text="* Anuncio BOJA"
			         		action="#{gestionAnuncioBoja.editarAnuncio}"
			                          launchListener="#{gestionAnuncioBoja.pasarParametrosDatosAnuncio}"
			                          useWindow="true" windowHeight="560" windowWidth="700"
			                          id="verAnuncioBOJA2"
			                         >
			        </tr:commandLink>
		        </f:facet>
			  </tr:panelBorderLayout>
		  </tr:group>
      	</f:facet>
      	<f:facet name="nogenerado">
      		<tr:switcher facetName="#{gestionExpedienteContratacionBean.anuncioValidado ? 'previsualizar':'nada'}" >
	      	<f:facet name="previsualizar">
		       <tr:panelHorizontalLayout halign="center"
		                           partialTriggers="generarAnuncioBOJA editarAnuncioBOJA">
		        <tr:commandButton text="Generar y previsualizar anuncio para BOJA"
		                          action="#{gestionAnuncioBoja.editarAnuncio}"
		                          launchListener="#{generarTextoAnuncioBojaAccion.pasarParametrosDatosAnuncio}"
		                          returnListener="#{gestionAnuncioBoja.retornarDatosAnuncio}"
		                          useWindow="true" windowHeight="560" windowWidth="700"
		                          partialSubmit="true" id="generarAnuncioBOJA"
		                          rendered="#{configuracionGestionExpedienteContratacion.botonGenerarAnuncioBojaVisible}"/>
		        <tr:outputText value="ESTE EXPEDIENTE NO SE PUBLICA EN EL BOJA"
		        				rendered="#{configuracionGestionExpedienteContratacion.textoNoSePublicaEnBoletinVisible}" />
	       </tr:panelHorizontalLayout>
	       </f:facet>
	       </tr:switcher>
      	</f:facet>


       </tr:switcher>
        <tr:panelHorizontalLayout halign="center">

       <tr:panelBox background="medium"  inlineStyle="width: 80%;" contentStyle="width: 100%;"
                  text="Evaluacion del auncio en el centro"
                  rendered="#{gestionAnuncioBoja.confirmado}" >
                  <tr:panelFormLayout >
                  		<tr:panelHorizontalLayout>
                  			<tr:outputText value="¿Esta de acuerdo con el anuncio?"></tr:outputText>
                  			<tr:outputText value="#{gestionAnuncioBoja.valorConfirmado}"></tr:outputText>
                  		</tr:panelHorizontalLayout>
                  		<tr:panelHorizontalLayout >
                  			<tr:outputText value="Notas:"></tr:outputText>
                  		</tr:panelHorizontalLayout>
                  		<tr:panelHorizontalLayout>
                  			<tr:inputText columns="100" rows="4" readOnly="true" value="#{gestionAnuncioBoja.notas}"></tr:inputText>
                  		</tr:panelHorizontalLayout>
                  </tr:panelFormLayout>
       </tr:panelBox>
         </tr:panelHorizontalLayout>
      </tr:panelHeader>
      <tr:panelHeader text="B.O.E." partialTriggers="generarAnuncioBOE editarAnuncioBOE">
      	<tr:switcher facetName="#{gestionAnuncioBoe.anuncioGenerado ?'generado':'nogenerado'}">
	      	<f:facet name="generado">
			  <tr:group>
	          	  <tr:outputText value="Plazo, en días naturales, para presentación de ofertas (BOE): " />
	          	  <tr:outputText value="#{gestionAnuncioBoe.plazoPresentacionOfertas}" />
		          <tr:spacer height="20" width="20" />
	              <tr:panelBorderLayout partialTriggers="generarAnuncioBOE editarAnuncioBOE" >
	            	<f:facet name="innerStart">
				        <tr:commandButton text="Editar anuncio para BOE"
				                          action="#{gestionAnuncioBoe.editarAnuncio}"
				                          launchListener="#{gestionAnuncioBoe.pasarParametrosDatosAnuncio}"
				                          returnListener="#{gestionAnuncioBoe.retornarDatosAnuncio}"
				                          useWindow="true" windowHeight="560" windowWidth="700"
				                          partialSubmit="true" id="editarAnuncioBOE"
				                          disabled="#{!gestionAnuncioBoe.anuncioGenerado}"
				                          rendered="#{configuracionGestionExpedienteContratacion.botonEditarAnuncioBoeVisible}"/>
					</f:facet>
	          		<f:facet name="left">
						<tr:commandLink text="* Anuncio BOE"
				         		action="#{gestionAnuncioBoe.editarAnuncio}"
				                          launchListener="#{gestionAnuncioBoe.pasarParametrosDatosAnuncio}"
				                          useWindow="true" windowHeight="560" windowWidth="700"
				                          id="verAnuncioBOE2"
				                          rendered="#{gestionAnuncioBoe.anuncioGenerado}">
				        </tr:commandLink>
					</f:facet>
			      </tr:panelBorderLayout>
			  </tr:group>
	      	</f:facet>
      	<f:facet name="nogenerado">
      		<tr:switcher facetName="#{gestionExpedienteContratacionBean.anuncioValidado ? 'previsualizar':'nada'}" >
	      	<f:facet name="previsualizar">
		       <tr:panelHorizontalLayout halign="center"
		                           partialTriggers="generarAnuncioBOE editarAnuncioBOE">
		        <tr:commandButton text="Generar y previsualizar anuncio para BOE"
		                          action="#{gestionAnuncioBoe.editarAnuncio}"
				                  launchListener="#{generarTextoAnuncioBoeAccion.pasarParametrosDatosAnuncio}"
		                          returnListener="#{gestionAnuncioBoe.retornarDatosAnuncio}"
		                          useWindow="true" windowHeight="560" windowWidth="700"
		                          partialSubmit="true" id="generarAnuncioBOE"
		                          rendered="#{configuracionGestionExpedienteContratacion.botonGenerarAnuncioBoeVisible}"/>
 				<tr:outputText value="ESTE EXPEDIENTE NO SE PUBLICA EN EL BOE"
	        				rendered="#{configuracionGestionExpedienteContratacion.textoNoSePublicaEnBoletinVisible}" />

		       </tr:panelHorizontalLayout>
	       </f:facet>
	       </tr:switcher>
      	</f:facet>
		</tr:switcher>
       <tr:panelHorizontalLayout halign="center">
        <tr:panelBox background="medium" inlineStyle="width: 80%;"
                     contentStyle="width: 100%;"
                     text="Evaluacion del auncio en el centro"
                     rendered="#{gestionAnuncioBoe.confirmado}">
         <tr:panelFormLayout>
          <tr:panelHorizontalLayout>
           <tr:outputText value="¿Esta de acuerdo con el anuncio?"/>
           <tr:outputText value="#{gestionAnuncioBoe.valorConfirmado}"/>
          </tr:panelHorizontalLayout>
          <tr:panelHorizontalLayout>
           <tr:outputText value="Notas:"/>
          </tr:panelHorizontalLayout>
          <tr:panelHorizontalLayout>
           <tr:inputText columns="100" rows="4" readOnly="true" value="#{gestionAnuncioBoe.notas}"/>
          </tr:panelHorizontalLayout>
         </tr:panelFormLayout>
        </tr:panelBox>
       </tr:panelHorizontalLayout>
      </tr:panelHeader>
      <tr:panelHeader text="D.O.C.E." partialTriggers="btnAdjuntarAnuncioDOCE ">

      	<tr:switcher facetName="#{gestionExpedienteContratacionBean.ficheroDoce.rowCount >0?'generado':'nogenerado'}">
      	<f:facet name="generado">
      	 <tr:iterator rows="25" var="fichero"
                      value="#{gestionExpedienteContratacionBean.ficheroDoce}">

		  <tr:group>
          	  <tr:outputText value="Plazo, en días naturales, para presentación de ofertas (DOCE): " />
          	  <tr:outputText value="#{gestionAnuncioDoce.plazoPresentacionOfertas}" />
	          <tr:spacer height="20" width="20" />
	          <tr:panelBorderLayout partialTriggers="btnAdjuntarAnuncioDOCE adjuntarArchivoDOCEEditar">
	          	<f:facet name="innerStart">
		            <tr:commandButton  text="Modificar"
		         				    rendered="#{configuracionGestionExpedienteContratacion.botonAdjuntarAnuncioDoceVisible}"
		            				action="#{gestionExpedienteContratacionBean.mostrarSubidaArchivoDOCE}"
		                            useWindow="true"
		                            launchListener="#{gestionExpedienteContratacionBean.pasarSubidaArchivoDOCEEdicion}"
		                            returnListener="#{gestionExpedienteContratacionBean.retornarSubidaArchivoDOCE}"
		                            windowHeight="560" windowWidth="700"
		                            partialSubmit="true" id="adjuntarArchivoDOCEEditar" >
		            	<tr:setActionListener from="#{fichero}" to="#{adjuntarArchivoExpedienteContratacionBean.fichero}"/>
		            </tr:commandButton>


	          	</f:facet>
	          	<f:facet name="left">
			        <tr:panelFormLayout partialTriggers="btnAdjuntarAnuncioDOCE adjuntarArchivoDOCEEditar"
			                      maxColumns="1">
			        <tr:panelHorizontalLayout halign="left">

			          <tr:commandLink onclick="noBloquearPantalla()" text="* Anuncio DOCE (#{fichero.nombre})"
			                          actionListener="#{muestraFicherosExpediente.descargaFichero}">
			            <f:param value="#{fichero.id}" name="idFicheroEnWorkflow"/>
			            <f:param value="#{fichero.nombreArchivo}" name="nombreFichero"/>
			          </tr:commandLink>
			        </tr:panelHorizontalLayout>
			        </tr:panelFormLayout>
		        </f:facet>
			  </tr:panelBorderLayout>
		  </tr:group>
		  </tr:iterator>
      	</f:facet>
      	<f:facet name="nogenerado">
	       <tr:panelHorizontalLayout halign="center"
	                           partialTriggers="btnAdjuntarAnuncioDOCE adjuntarArchivoDOCEEditar">
	        <tr:commandButton text="Adjuntar anuncio DOCE"
	        				rendered="#{configuracionGestionExpedienteContratacion.botonAdjuntarAnuncioDoceVisible}"
	                        action="#{generarTextoAnuncioDoceAccion.ejecuta}"
	                        useWindow="true"
	                        launchListener="#{gestionExpedienteContratacionBean.pasarSubidaArchivoDOCE}"
	                        returnListener="#{gestionExpedienteContratacionBean.retornarSubidaArchivoDOCE}"
	                        windowHeight="560" windowWidth="700"
	                        partialSubmit="true" id="btnAdjuntarAnuncioDOCE" >
	          	<tr:setActionListener from="#{ceroEntero.valor}" to="#{adjuntarArchivoExpedienteContratacionBean.idSeccion}"/>
	        </tr:commandButton>
	        <tr:outputText value="ESTE EXPEDIENTE NO SE PUBLICA EN EL DOCE"
	        				rendered="#{configuracionGestionExpedienteContratacion.textoNoSePublicaEnBoletinVisible}" />
	       </tr:panelHorizontalLayout>
      	</f:facet>
       </tr:switcher>
       <tr:panelHorizontalLayout halign="center">
        <tr:panelBox background="medium" inlineStyle="width: 80%;"
                     contentStyle="width: 100%;"
                     text="Evaluacion del auncio en el centro"
                     rendered="#{gestionAnuncioDoce.confirmado}">
         <tr:panelFormLayout>
          <tr:panelHorizontalLayout>
           <tr:outputText value="¿Esta de acuerdo con el anuncio?"/>
           <tr:outputText value="#{gestionAnuncioDoce.valorConfirmado}"/>
          </tr:panelHorizontalLayout>
          <tr:panelHorizontalLayout>
           <tr:outputText value="Notas:"/>
          </tr:panelHorizontalLayout>
          <tr:panelHorizontalLayout>
           <tr:inputText columns="100" rows="4" readOnly="true" value="#{gestionAnuncioDoce.notas}"/>
          </tr:panelHorizontalLayout>
         </tr:panelFormLayout>
        </tr:panelBox>
       </tr:panelHorizontalLayout>

      </tr:panelHeader>
      <tr:spacer width="10" height="10"/>
     </tr:panelBox>

     <tr:panelBox background="medium"  inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  id="bloqueConfirmacionAnuncio"
                  rendered="#{configuracionGestionExpedienteContratacion.bloqueConfirmacionDatosAnuncioVisible}">
      <tr:panelFormLayout labelWidth="260">

       <tr:panelHorizontalLayout>
       		<tr:outputLabel value="Este expediente proviene del Publicador, quien ha propuesto los siguientes documentos para publicar:"></tr:outputLabel>
       </tr:panelHorizontalLayout>
       <tr:spacer width="20px" height="10px"></tr:spacer>
       <tr:panelHorizontalLayout rendered="#{gestionAnuncioBoja.anuncioGenerado}">
       		<tr:commandLink text="* Anuncio BOJA"
         		action="#{gestionAnuncioBoja.editarAnuncio}"
                          launchListener="#{gestionAnuncioBoja.pasarParametrosDatosAnuncio}"
                          useWindow="true" windowHeight="560" windowWidth="700"
                          id="verAnuncioBOJA"
                          rendered="#{gestionAnuncioBoja.anuncioGenerado}">
        	</tr:commandLink>
        	<tr:spacer width="20px"></tr:spacer>
        	<tr:commandButton text="Aprobación e Incidencias"
        				  action="#{gestionAnuncioBoja.aprobacionIncidencias}"
                          launchListener="#{gestionAnuncioBoja.pasarParametrosDatosAnuncio}"
                          returnListener="#{gestionAnuncioBoja.retornarDatosAnuncio}"
                          useWindow="true" windowHeight="560" windowWidth="700"
                          partialSubmit="true" id="editarIncidenciasBOJA"
            	disabled="#{!configuracionGestionExpedienteContratacion.botonAprobacionIncidenciasVisible}">
            </tr:commandButton>
       </tr:panelHorizontalLayout>
       <tr:panelHorizontalLayout rendered="#{gestionAnuncioBoe.anuncioGenerado}">
       		<tr:commandLink text="* Anuncio BOE"
         		action="#{gestionAnuncioBoe.editarAnuncio}"
                          launchListener="#{gestionAnuncioBoe.pasarParametrosDatosAnuncio}"
                          useWindow="true" windowHeight="560" windowWidth="700"
                          id="verAnuncioBOE"
                          rendered="#{gestionAnuncioBoe.anuncioGenerado}" >
            </tr:commandLink>
            <tr:spacer width="25px"></tr:spacer>
            <tr:commandButton text="Aprobación e Incidencias"
             			  action="#{gestionAnuncioBoe.aprobacionIncidencias}"
                          launchListener="#{gestionAnuncioBoe.pasarParametrosDatosAnuncio}"
                          returnListener="#{gestionAnuncioBoe.retornarDatosAnuncio}"
                          useWindow="true" windowHeight="560" windowWidth="700"
                          partialSubmit="true" id="editarIncidenciasBOE"
            	disabled="#{!configuracionGestionExpedienteContratacion.botonAprobacionIncidenciasVisible}">
            </tr:commandButton>
       </tr:panelHorizontalLayout>

       <tr:iterator rows="25" var="fichero"
                      value="#{gestionExpedienteContratacionBean.ficheroDoce}">
       <tr:panelHorizontalLayout >
       		<tr:commandLink onclick="noBloquearPantalla()" text="* Anuncio DOCE (#{fichero.nombre})"
			                          actionListener="#{muestraFicherosExpediente.descargaFichero}">
			            <f:param value="#{fichero.id}" name="idFicheroEnWorkflow"/>
			            <f:param value="#{fichero.nombreArchivo}" name="nombreFichero"/>
			</tr:commandLink>
            <tr:spacer width="25px"></tr:spacer>
            <tr:commandButton text="Aprobación e Incidencias"
             			  action="#{gestionAnuncioDoce.aprobacionIncidencias}"
                          launchListener="#{gestionAnuncioDoce.pasarParametrosDatosAnuncio}"
                          returnListener="#{gestionAnuncioDoce.retornarDatosAnuncio}"
                          useWindow="true" windowHeight="560" windowWidth="700"
                          partialSubmit="true" id="editarIncidenciasDOCE"
            	disabled="#{!configuracionGestionExpedienteContratacion.botonAprobacionIncidenciasVisible}">
            </tr:commandButton>
       </tr:panelHorizontalLayout>
       </tr:iterator>

      </tr:panelFormLayout>
     </tr:panelBox>

    <tr:panelBox  background="medium" text="Datos anexos:"  inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  id="datosAnexos"
                  rendered="#{configuracionGestionExpedienteContratacion.bloqueDatosAnexosVisible}">
       <tr:spacer width="10" height="10"/>
 		   <tr:panelHorizontalLayout halign="left">
       <tr:table emptyText="CCA no generado" width="400" var="documentoContable"
         			value="#{gestionExpedienteContratacionBean.documentosContables}" autoSubmit="true"
         			rendered="#{configuracionGestionExpedienteContratacion.bloqueDatosAnexosDocumentosContablesVisible}" >
           <tr:column  headerText="Código CCA">
             <tr:outputText value="#{documentoContable.codigoCCA}"/>
           </tr:column>
           <tr:column  headerText="Nº documento contable (Júpiter)" width="200">
			 <tr:panelHorizontalLayout>
               <tr:outputText value="#{documentoContable.numeroDocumento}" />
	       </tr:panelHorizontalLayout>

           </tr:column>
	   </tr:table>
	   </tr:panelHorizontalLayout>
       <tr:spacer width="10" height="10"/>

      <tr:panelFormLayout labelWidth="260">

       <tr:panelLabelAndMessage label="Fecha de VoBo por Intervención:"
                                rendered="#{configuracionGestionExpedienteContratacion.bloqueDatosAnexosFechaIntervencionVisible}">
                <tr:inputDate disabled="true"
                              value="#{gestionExpedienteContratacionBean.fechaIntervencion}"/>
              </tr:panelLabelAndMessage>
       <tr:panelLabelAndMessage label="Fecha de informe por Asesoría Jurídica:"
                                rendered="#{configuracionGestionExpedienteContratacion.bloqueDatosAnexosFechaAJVisible}">
		  <tr:panelHorizontalLayout>
                <tr:inputDate disabled="true"
                              value="#{gestionExpedienteContratacionBean.fechaInformeAJ}"/>
		       <tr:spacer width="10" height="10"/>
                <tr:outputText value="#{gestionExpedienteContratacionBean.resultadoInformeAJ}" />
		  </tr:panelHorizontalLayout>
       </tr:panelLabelAndMessage>
        <tr:commandLink text="* Anuncio BOJA"
         		action="#{gestionAnuncioBoja.editarAnuncio}"
                          launchListener="#{gestionAnuncioBoja.pasarParametrosDatosAnuncio}"
                          useWindow="true" windowHeight="560" windowWidth="700"
                          id="verAnuncioBOJAAnexos"
                          rendered="#{gestionAnuncioBoja.anuncioGenerado}">
        </tr:commandLink>
		<tr:commandLink text="* Anuncio BOE"
         		action="#{gestionAnuncioBoe.editarAnuncio}"
                          launchListener="#{gestionAnuncioBoe.pasarParametrosDatosAnuncio}"
                          useWindow="true" windowHeight="560" windowWidth="700"
                          id="verAnuncioBOEAnexos"
                          rendered="#{gestionAnuncioBoe.anuncioGenerado}" >
        </tr:commandLink>

         <tr:iterator rows="25" var="fichero"
                      value="#{gestionExpedienteContratacionBean.ficheroDoce}">
         <tr:commandLink onclick="noBloquearPantalla()" text="* Anuncio DOCE (#{fichero.nombre})"
			                          actionListener="#{muestraFicherosExpediente.descargaFichero}">
			            <f:param value="#{fichero.id}" name="idFicheroEnWorkflow"/>
			            <f:param value="#{fichero.nombreArchivo}" name="nombreFichero"/>
		</tr:commandLink>
       </tr:iterator>

      </tr:panelFormLayout>
     </tr:panelBox>

     <tr:panelBox  background="medium" id="bloqueCuadroResumen"  inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  rendered="#{configuracionGestionExpedienteContratacion.bloqueCuadroResumenVisible}">
      <tr:panelGroupLayout layout="vertical">
       <tr:commandLink text="Datos complementarios del expediente y datos del anuncio"
                       action="#{editarAnuncio.ejecuta}"/>
     <tr:spacer width="10" height="10"/>

 	   <tr:panelHorizontalLayout>
       <tr:table emptyText="CCA no generado" width="450" var="documentoContable" varStatus="esta" id="tblDocumentosContables"
         			value="#{gestionExpedienteContratacionBean.documentosContables}" autoSubmit="true" 
         			partialTriggers="tblDocumentosContables:numeroDocumentoContable tblDocumentosContables:buscarDocumentoContable" >
           <tr:column  headerText="Código CCA">
             <tr:outputText value="#{documentoContable.codigoCCA}"/>
           </tr:column>
           <tr:column  headerText="Nº documento contable (Júpiter)" width="250" >
			 <tr:panelHorizontalLayout>
	             <tr:inputText value="#{documentoContable.numeroDocumento}"
							   valueChangeListener="#{documentoContable.cambiaValorJupiter}"
	             				disabled="#{!configuracionGestionExpedienteContratacion.campoNumeroDocumentoEditable}"
	             				autoSubmit="true" id="numeroDocumentoContable" />
			     <tr:spacer width="10" height="10"/>
      			 <tr:commandButton text="Buscar"
                        rendered="#{documentoContable.botonBuscarVisible}"
						partialSubmit="true" id="buscarDocumentoContable"
                        action="#{dialogoSeleccionAsociacionCCAJupiter.ejecuta}"  
                        actionListener="#{dialogoSeleccionAsociacionCCAJupiter.configuraComponente}" 
                        returnListener="#{documentoContable.ejecutaRetorno}" 
                        useWindow="true" windowWidth="400" windowHeight="300" > 
						<tr:setActionListener from="#{documentoContable.codigoCCA}" to="#{dialogoSeleccionAsociacionCCAJupiter.codigoCCA}" />
				 </tr:commandButton>
      			 <tr:commandButton text="Solicitar"
                        rendered="#{documentoContable.botonBuscarVisible}"
						partialSubmit="true" id="solicitarDocumentoContable"
						actionListener="#{solicitudJupiterMDC.configuraComponente}"  
                        action="#{solicitudJupiterMDC.ejecuta}" 
                        useWindow="true" windowWidth="400" windowHeight="300" > 
						<tr:setActionListener from="#{documentoContable.codigoCCA}" to="#{solicitudJupiterMDC.codigoCCA}" />
						<tr:setActionListener from="#{documentoContable.numeroDocumento}" to="#{solicitudJupiterMDC.numeroDocumento}" />
				 </tr:commandButton>

			     <tr:spacer width="10" height="10"/>

		     	<tr:commandLink action="#{solicitarNuevoCCAAccion.ejecuta}" rendered="#{configuracionGestionExpedienteContratacion.botonesSolicitudesDocumentoContablesVisibles}"
		                        returnListener="#{solicitarNuevoCCAAccion.retornarNuevoCCA}"
		                        useWindow="true" windowHeight="560" windowWidth="700"
		                        partialSubmit="true" id="nuevoCCA" >
	             <tr:image source="/imagenes/editar_4_16.png"
	                             shortDesc="Solicitud de nuevo CCA"/>
					<tr:setActionListener from="#{'Solicitud de nuevo CCA'}" to="#{pageFlowScope.titulo}" />
					<tr:setActionListener from="#{'Motivo: '}" 			  to="#{pageFlowScope.etiqueta}" />
					<tr:setActionListener from="#{documentoContable.codigoCCA}" to="#{solicitarNuevoCCAAccion.anteriorCodigoCCA}" />
		       </tr:commandLink>
  		       <tr:spacer width="10" height="10" />
  		       	<tr:switcher facetName="#{(esta.index == 0) ? 'nada' : 'botonBorrar'}" >
  		       		<f:facet name="botonBorrar">
			     	<tr:commandLink action="#{solicitarQuitarDocumentoContableAccion.ejecuta}"
			     					rendered="#{configuracionGestionExpedienteContratacion.botonesSolicitudesDocumentoContablesVisibles}"
			                        returnListener="#{solicitarQuitarDocumentoContableAccion.retornarQuitarDocumentoContable}"
			                        useWindow="true" windowHeight="560" windowWidth="700"
			                        partialSubmit="true" id="lnkQuitarDocumentoContable" >
		             <tr:image source="/imagenes/eliminar_16.png"
		                             shortDesc="Solicitud de quitar documento contable"/>
						<tr:setActionListener from="#{'Solicitud de quitar documento contable'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{'Motivo: '}" 			  to="#{pageFlowScope.etiqueta}" />
						<tr:setActionListener from="#{documentoContable.codigoCCA}" to="#{solicitarQuitarDocumentoContableAccion.codigoCCA}" />
			       </tr:commandLink>
			       </f:facet>
  		       	   <f:facet name="nada" />
		       </tr:switcher>
	       </tr:panelHorizontalLayout>

           </tr:column>
	  </tr:table>
     <tr:spacer width="10" height="10"/>
   	  <tr:switcher facetName="#{gestionExpedienteContratacionBean.tieneDocumentosContables ? 'botonAgregar' : 'nada'}" >
   		<f:facet name="botonAgregar">
	   	 <tr:commandLink action="#{solicitarAgregarDocumentoContableAccion.ejecuta}" rendered="#{configuracionGestionExpedienteContratacion.botonesSolicitudesDocumentoContablesVisibles}"
	                      returnListener="#{solicitarAgregarDocumentoContableAccion.retornarAgregarDocumentoContable}"
	                      useWindow="true" windowHeight="560" windowWidth="700"
	                      partialSubmit="true" id="lnkAgregarDocumentoContable" >
	          <tr:image source="/imagenes/anadir_16.png"
	                          shortDesc="Solicitud de agregar documento contable"/>
	     </tr:commandLink>
       </f:facet>
	   <f:facet name="nada" />
      </tr:switcher>
	  </tr:panelHorizontalLayout>

     <tr:spacer width="10" height="10"/>

	   <tr:commandLink text="Histórico de Datos" useWindow="true" windowHeight="800" windowWidth="800"
	   				   rendered="#{configuracionGestionExpedienteContratacion.botonHistoricoDatosVisible}"
                       action="#{verHistorialValorCamposExpediente.ejecuta}"
                       />


      </tr:panelGroupLayout>
     </tr:panelBox>
     <tr:panelBox  background="medium"  inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  text="Acciones sobre ficheros" id="accionesSobreFicheros"
                  rendered="#{configuracionGestionExpedienteContratacion.bloqueAccionesSobreFicherosVisible}">
      <!-- Lista de secciones visibles en el expediente -->
      <tr:iterator rows="25" var="seccion"
                   binding="#{gestionExpedienteContratacionBinding.iteradorSeccion}"
                   value="#{gestionExpedienteContratacionBean.listaSecciones}">
       <tr:panelFormLayout partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"
                     maxColumns="1">
        <tr:panelHeader text="Sección: #{seccion.descripcion==null?seccion.codigoSeccion:seccion.descripcion}"
                        rendered="#{seccion.seccionVisible}">
         <!-- enlaces relacionados con la secccion -->
         <tr:panelFormLayout maxColumns="2" rendered="#{!(!configuracionGestionExpedienteContratacion.navegacionCuadroResumenVisible || !seccion.tieneCuadroResumen)}">
          <tr:panelHorizontalLayout>
          <tr:commandLink text="PCAP.Cuadro Resumen"
                          action="#{editarCuadroResumen.ejecuta}"/>
          <tr:spacer width="10" height="10"/>
          <tr:commandButton text="Validación Técnica"
                          action="#{validarCuadroResumen.ejecuta}"
                          rendered="#{configuracionGestionExpedienteContratacion.botonValidacionTecnicaVisible}">
                   <tr:setActionListener to="#{configuracionGestionExpedienteContratacion.validacionTecnica}" from="#{true}"/>
          </tr:commandButton>
          <tr:spacer width="10" height="10"/>
          <tr:commandButton text="Validación Económica"
                          action="#{validarCuadroResumen.ejecuta}"
                          rendered="#{configuracionGestionExpedienteContratacion.botonValidacionEconomicaVisible}">
                   <tr:setActionListener to="#{configuracionGestionExpedienteContratacion.validacionTecnica}" from="#{false}"/>
          </tr:commandButton>

          </tr:panelHorizontalLayout>


         </tr:panelFormLayout>
         <!-- Documentacion asociada a la seccion en esta version de modelo de pliego -->
         <tr:iterator rows="25" var="documentacion"
                      value="#{gestionExpedienteContratacionBean.listaDocumentacionSeccion}">
          <tr:panelFormLayout maxColumns="1">
           <h:panelGrid columns="2">
            <tr:image source="/contratacion/imagenes/iconosExtension/#{documentacion.tipoIcono}.gif"/>
            <tr:commandLink onclick="noBloquearPantalla()" text="#{documentacion.descripcion}"
                            actionListener="#{muestraDocumentacion.descargaFichero}">
             <f:param value="#{documentacion.id}" name="id"/>
             <f:param value="#{documentacion.nombre}" name="nombreFichero"/>
            </tr:commandLink>
           </h:panelGrid>
          </tr:panelFormLayout>
         </tr:iterator>
         <!-- Lista de ficheros asociados a la seccion -->
         <tr:iterator rows="25" var="fichero"
                      value="#{gestionExpedienteContratacionBean.listaFicherosSeccion}">
          <tr:panelFormLayout partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"
                        maxColumns="1">
           <tr:panelHorizontalLayout halign="left">
            <tr:image source="/contratacion/imagenes/iconosExtension/#{fichero.tipoIcono}.gif"/>
            <tr:commandLink onclick="noBloquearPantalla()" text="#{fichero.nombre}"
                            actionListener="#{muestraFicherosExpediente.descargaFichero}">
             <f:param value="#{fichero.id}" name="idFicheroEnWorkflow"/>
             <f:param value="#{fichero.nombreArchivo}" name="nombreFichero"/>
            </tr:commandLink>
            <tr:spacer width="10" height="10"/>
            <tr:commandLink action="#{eliminarFicheroExpedienteContratacionBean.eliminarFichero}"
                            onclick="if(!confirm('¿Desea eliminar el fichero?')) return false"
                            rendered="#{seccion.ficherosEditables}">
             <tr:image source="/contratacion/imagenes/eliminar.gif"
                             shortDesc="Eliminar fichero"/>
             <f:param value="#{fichero.id}" name="idFichero"/>
            </tr:commandLink>
            <tr:spacer width="10" height="10"/>
            <tr:commandLink action="#{adjuntarArchivoExpedienteContratacionBean.mostrarSubidaArchivo}"
                            useWindow="true"
                            launchListener="#{adjuntarArchivoExpedienteContratacionBean.pasarSubidaArchivoEdicion}"
                            returnListener="#{adjuntarArchivoExpedienteContratacionBean.retornarSubidaArchivo}"
                            windowHeight="560" windowWidth="700"
                            partialSubmit="true" id="adjuntarArchivoEditar"
                            rendered="#{seccion.ficherosEditables}">
             <tr:image source="/contratacion/imagenes/ico_editar.jpg"
                             shortDesc="Modificar fichero"/>
             	<tr:setActionListener from="#{fichero}" to="#{adjuntarArchivoExpedienteContratacionBean.fichero}"/>
            </tr:commandLink>
           </tr:panelHorizontalLayout>
          </tr:panelFormLayout>
         </tr:iterator>
         <tr:commandLink text="Adjuntar un archivo"
                         action="#{adjuntarArchivoExpedienteContratacionBean.mostrarSubidaArchivo}"
                         useWindow="true"
                         launchListener="#{adjuntarArchivoExpedienteContratacionBean.pasarSubidaArchivo}"
                         returnListener="#{adjuntarArchivoExpedienteContratacionBean.retornarSubidaArchivo}"
                         windowHeight="560" windowWidth="700"
                         id="btnAdjuntarFichero"
                         rendered="#{seccion.ficherosEditables}">
              	<tr:setActionListener from="#{seccion.id}" to="#{adjuntarArchivoExpedienteContratacionBean.idSeccion}"/>
         </tr:commandLink>
        </tr:panelHeader>
       </tr:panelFormLayout>
      </tr:iterator>
     </tr:panelBox>
          <tr:group id="tramitacionWorkflow"
                     rendered="#{configuracionGestionExpedienteContratacion.bloqueTramitacionVisible}">

            		<tr:panelBox background="medium" text="Tramitación"
						inlineStyle="width: 100%;"
						rendered="#{workflowBeanLicitacion.frameHabilitado}">
						<tr:panelHeader text="Trámites posibles" />
						<tr:selectOneRadio value="#{workflowBeanLicitacion.idTramite}">
							<f:selectItems value="#{workflowBeanLicitacion.transicionesPosibles}" />
						</tr:selectOneRadio>
						<tr:panelFormLayout>
							<f:facet name="footer">
								<tr:panelHorizontalLayout halign="right">
									<tr:commandButton text="Tramitar"
													  action="#{gestionExpedienteContratacionBean.tramitar}" 
													  actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" />
								</tr:panelHorizontalLayout>
							</f:facet>

							<tr:inputText label="Observaciones" rows="4" columns="60"
								value="#{workflowBeanLicitacion.observaciones}"
								 />
							<tr:spacer height="10"
								rendered="#{workflowBeanLicitacion.muestraObservacionesTramiteAnterior}"></tr:spacer>
							<tr:inputText label="Observaciones del trámite anterior" rows="4"
								columns="60" value="#{workflowBeanLicitacion.observacionesTramiteAnterior}"
								disabled="true" rendered="#{workflowBeanLicitacion.muestraObservacionesTramiteAnterior}"
								 />
						</tr:panelFormLayout>
					</tr:panelBox>

          </tr:group>



     <tr:panelBox id="estadoValidacion"  background="medium" inlineStyle="width: 100%;" contentStyle="width: 100%;"
                  text="#{gestionMensajesValidacionLicitacionExpedienteBean.tituloValidacion}"
                  rendered="#{configuracionGestionExpedienteContratacion.bloqueEstadoValidacionVisible}"
                  partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar">
      <tr:panelFormLayout partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"
                    maxColumns="1">
       <tr:panelGroupLayout binding="#{gestionExpedienteContratacionBinding.pgPanelErrores}"
      			 partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar">
        <tr:switcher facetName="sinErrores"
                     binding="#{gestionExpedienteContratacionBinding.swErrores}">
         <f:facet name="errores">
          <h:panelGroup>
           <tr:panelFormLayout>
            <f:facet name="footer"/>

            <tr:panelHeader text="Datos complementarios del expediente y datos del anuncio"/>
            <tr:panelList maxColumns="100"
                          binding="#{gestionExpedienteContratacionBinding.plListaErroresAnuncio}"
                          partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"/>
            <tr:panelHeader text="Cuadro resumen"/>
            <tr:panelList maxColumns="100"
                          binding="#{gestionExpedienteContratacionBinding.plListaErroresExpediente}"
                          partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"/>
            <tr:panelHeader text="Ficheros"/>
            <tr:panelList maxColumns="100"
                          binding="#{gestionExpedienteContratacionBinding.plListaErroresFichero}"
                          partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"/>
 			<tr:panelHeader text="Otros"/>
            <tr:panelList maxColumns="100"
            			  binding="#{gestionExpedienteContratacionBinding.plListaErroresOtros}"
                          partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"/>

           </tr:panelFormLayout>
          </h:panelGroup>
         </f:facet>
         <f:facet name="sinErrores">
           <h:panelGroup>
           <tr:panelFormLayout>
         <tr:panelHorizontalLayout halign="center">
          <tr:outputText partialTriggers="btnAdjuntarFichero adjuntarArchivoEditar"
                         inlineStyle="font-size:120.0%;"
                         value="Los datos están correctos"/>
          </tr:panelHorizontalLayout>
          </tr:panelFormLayout>
          </h:panelGroup>
         </f:facet>
        </tr:switcher>

       </tr:panelGroupLayout>
      </tr:panelFormLayout>
     </tr:panelBox>
     <tr:separator/>
     <tr:panelHorizontalLayout halign="center">
      <tr:commandButton text="Guardar"
                        action="#{gestionExpedienteContratacionBean.guardar}"
                        rendered="#{configuracionGestionExpedienteContratacion.habilitadoGuardar}"
						actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" />
      <tr:commandButton text="Volver"
                        action="#{gestionExpedienteContratacionBean.volver}"
                        rendered="#{configuracionGestionExpedienteContratacion.habilitadoVolver}"/>
      <tr:commandButton text="Menú principal"
                        action="#{volverMenuPrincipalBean.ejecuta}"/>
     </tr:panelHorizontalLayout>
    </h:form>
   </trh:body>
  </trh:html>
 </f:view>
</jsp:root>
