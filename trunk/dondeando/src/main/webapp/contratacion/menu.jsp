<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
           xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:geos="http://www.hp-cda.com/adf/faces">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Men� Principal Contrataci�n">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
      </trh:head>
      <trh:body>
       <geos:cabeceraPagina  />
        <h:form>
          <tr:spacer width="10" height="10"/>
          <tr:panelFormLayout maxColumns="2" rows="10" >
            <f:facet name="footer"/>
            <tr:panelBox text="Gestor Centros" background="medium" inlineStyle="width: 450px"
            	rendered="#{configuracionMenu.muestraBloqueGestor}">
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="[Necesidades]" rendered="#{configuracionMenu.muestraBloqueGestorNecesidades}"/>
              <tr:panelList rows="1" maxColumns="1" rendered="#{configuracionMenu.muestraBloqueGestorNecesidades}">

                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Carga necesidades de contrataci�n desde archivo"
                  				  action="#{cargaNecesidadesContratacionAccionMenu.ejecuta}" />
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gesti�n de necesidades de contrataci�n "
	                  action="#{gestionNecesidadesGestor.ejecuta}"
	                                  blocking="true"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Necesidades en elaboraci�n (#{necesidadesEnElaboracion.numeroNecesidades})"
	                  action="#{necesidadesEnElaboracion.ejecuta}"
	                                  blocking="true"/>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:panelHeader text="[Licitaci�n]"/>
              <tr:panelList rows="6">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Nuevo expediente"
                                  action="#{nuevoExpediente.ejecuta}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestionar expediente (#{consultaExpedientesGestorCentro.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesGestorCentro.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:panelHeader text="[Adjudicaci�n]"/>
              <tr:panelList rows="6">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestionar expedientes de adjudicaci�n (#{consultaExpedientesAdjudicacionGestorCentro.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesAdjudicacionGestorCentro.ejecuta}">
                   </tr:commandLink>
                </tr:panelHorizontalLayout>

              </tr:panelList>
              <tr:spacer width="10" height="1"/>
              <tr:panelHeader text="[General]"/>
              <tr:panelList rows="2" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Listado general de expedientes "
                  		 action="#{listadoGeneralExpedientes.ejecuta}" >
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
			  <tr:panelHeader text="[Pr�rrogas]" rendered="#{configuracionMenu.muestraSeccionProrrogas}" />
                <tr:panelHorizontalLayout  rendered="#{configuracionMenu.muestraSeccionProrrogas}" >
                  <tr:commandLink rendered="#{!configuracionMenu.muestraInfoBloqueo}"
	               		action="#{consultaExpedientesProrrogables.ejecuta}">
	           			<tr:outputText inlineStyle="color: red" value="
	               					(*) Atenci�n! En su 'Carpeta de Tareas' existen expedientes que han agotado el plazo establecido
									para garantizar la continuidad en su tramitaci�n. Esta circunstancia ha activado la funci�n de bloqueo,
									de forma que s�lo ser� posible iniciar la tramitaci�n de nuevos expedientes cuando se prosiga la de aqu�llos que,
									por su demora, provocan tal bloqueo.
									Consulte aqu� la situaci�n de sus expedientes y contacte con la Central Log�stica de Compras y
									Servicios si existieran problemas que le impidieran reanudar su tramitaci�n." >
						</tr:outputText>
                  </tr:commandLink>
                  <tr:commandLink rendered="#{configuracionMenu.muestraInfoBloqueo}"
	               		action="#{consultaExpedientesProrrogables.ejecuta}">
	               		<tr:outputText inlineStyle="color: red" value="
	               				En su 'Carpeta de Tareas' existen expedientes que deben ser tramitados sin demora para
								evitar bloqueos en esta aplicaci�n.Consulte aqu� sus expedientes y contin�e cuanto antes
								la tramitaci�n de los que tienen m�s demora.
								Contacte con la Central Log�stica de Compras y Servicios si existieran problemas que le impidieran hacerlo." >
						</tr:outputText>
                  </tr:commandLink>

                </tr:panelHorizontalLayout>

            </tr:panelBox>
            <tr:spacer width="10" height="10"/>
            <tr:panelBox text="Asesor�a Jur�dica" background="medium" inlineStyle="width: 450px"
            	rendered="#{configuracionMenu.muestraBloqueAsesoria}">
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="[General]"/>
              <tr:panelList rows="3" maxColumns="1">
                <tr:panelHorizontalLayout>
                <tr:commandLink text="Informe de asesor�a jur�dica (#{consultaExpedientesAsesoriaJuridica.numeroExpedientesPendientes})"
                  	action="#{consultaExpedientesAsesoriaJuridica.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Consulta de expedientes informados"
                   action="#{consultaExpedientesAsesoriaJuridicaConFiltroFecha.ejecuta}"/>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:spacer width="10" height="1"/>
            </tr:panelBox>
            <tr:spacer width="10" height="10"/>
            <tr:panelBox text="Intervenci�n" background="medium" inlineStyle="width: 450px"
            rendered="#{configuracionMenu.muestraBloqueIntervencion}">
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="[General]"/>
              <tr:panelList rows="2" maxColumns="1">
                <tr:panelHorizontalLayout>
                   <tr:commandLink text="Fiscalizar (#{consultaExpedientesIntervencion.numeroExpedientesPendientes})"
                  	action="#{consultaExpedientesIntervencion.ejecuta}">
                   </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Consulta de expedientes fiscalizados"
                    action="#{consultaExpedientesIntervencionConFiltroFecha.ejecuta}"/>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:spacer width="10" height="1"/>
            </tr:panelBox>
            <tr:spacer width="10" height="10"/>
            <tr:panelBox text="Validaci�n T�cnica/Econ�mica"  background="medium" inlineStyle="width: 450px"
            	rendered="#{configuracionMenu.muestraBloqueValidorTecEco}">
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="[General]"/>
              <tr:panelList rows="1" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Listado general de expedientes"
                                  action="#{listadoGeneralExpedientesTodosLosCentros.ejecuta}">
                   </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Validaci�n t�cnica/econ�mica (#{consultaExpedientesValidacionTecnicaEconomica.numeroExpedientesPendientes})"
                  		action="#{consultaExpedientesValidacionTecnicaEconomica.ejecuta}">
                   </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Consultar expediente"
                                  action="#{consultaExpedientesValidacionTecnicaEconomica.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
            </tr:panelBox>
            <tr:spacer width="10" height="10"/>
            <tr:panelBox text="Publicador"  background="medium" inlineStyle="width: 450px"
            	rendered="#{configuracionMenu.muestraBloquePublicador}">
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="[General]"/>
              <tr:panelList rows="2" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Consulta de expedientes publicados"
                                  action="#{consultaExpedientesPublicados.ejecuta}">
                 </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:panelHeader text="[Nuevos expedientes]"/>
              <tr:panelList rows="6">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Pendientes de publicar y en proceso de publicaci�n (#{consultaExpedientesProcesoPublicacion.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesProcesoPublicacion.ejecuta}">
                   </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Pendiente de enviar a los DDOO (#{consultaExpedientesPendienteEnviarDDOO.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesPendienteEnviarDDOO.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Pendientes de aparecer en los DDOO (#{consultaExpedientesPendientesAparecerDDOO.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesPendientesAparecerDDOO.ejecuta}">
                 </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Pre-Finalizados (#{consultaExpedientesPreFinalizados.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesPreFinalizados.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:panelHeader text="[Adjudicaci�n]"/>
              <tr:panelList rows="6">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Adjudicaciones pendientes de publicar (#{consultaAdjudicacionesPendientesPublicar.numeroExpedientesPendientes})"
                                  action="#{consultaAdjudicacionesPendientesPublicar.ejecuta}">
                 </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Adjudicaciones pendientes que no se van a publicar (#{consultaAdjudicacionesNoSeVanPublicar.numeroExpedientesPendientes})"
                                  action="#{consultaAdjudicacionesNoSeVanPublicar.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Adjudicaciones enviadas a BBOO y pendientes de publicar (#{consultaAdjudicacionesEnviadasBBOO.numeroExpedientesPendientes})"
                                  action="#{consultaAdjudicacionesEnviadasBBOO.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
            </tr:panelBox>
            <tr:spacer width="10" height="10"/>
            <tr:panelBox text="Validador Centros" background="medium" inlineStyle="width: 450px"
            	rendered="#{configuracionMenu.muestraBloqueValidador}">
              <tr:spacer width="10" height="10"/>




              <tr:panelHeader text="[Necesidades]" rendered="#{configuracionMenu.muestraBloqueValidadorNecesidades}"/>
              <tr:panelList rows="1" maxColumns="1" rendered="#{configuracionMenu.muestraBloqueValidadorNecesidades}" >
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Necesidades pendientes de validar (#{necesidadesPendientesValidar.numeroNecesidades})"
                  			 action="#{necesidadesPendientesValidar.ejecuta}" >
                  			 </tr:commandLink>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                 <tr:commandLink text="Gesti�n de necesidades de contrataci�n "
                  			 action="#{gestionNecesidadesValidador.ejecuta}" >
   			  	</tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>




              <tr:panelHeader text="[Licitaci�n]"/>
              <tr:panelList rows="6" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestionar expediente (#{consultaExpedientesValidadorCentro.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesValidadorCentro.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:panelHeader text="[Adjudicaci�n]"/>
              <tr:panelList rows="6">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestionar expedientes de adjudicaci�n (#{consultaExpedientesAdjudicacionValidadorCentro.numeroExpedientesPendientes})"
                                  action="#{consultaExpedientesAdjudicacionValidadorCentro.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:panelHeader text="[General]"/>
              <tr:panelList rows="1" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Listado general de expedientes"
                                  action="#{listadoGeneralExpedientes.ejecuta}">
                  </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
            </tr:panelBox>

            <tr:panelBox text="Otros"  background="medium" inlineStyle="width: 450px"
            	rendered="#{configuracionMenu.muestraMenuAdministracion}">
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="Accesos"/>
              <tr:panelList rows="2" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Men� administraci�n"
                                  action="#{botonMenuAdminBean.ejecuta}">
                 </tr:commandLink>
                </tr:panelHorizontalLayout>
              </tr:panelList>
            </tr:panelBox>
            <tr:spacer width="10" height="10"/>

          </tr:panelFormLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
