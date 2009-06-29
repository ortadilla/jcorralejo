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
      <trh:head title="Menú Administración Contratación">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
      </trh:head>
      <trh:body>
      	<geos:cabeceraPagina />
        <h:form>
          <tr:spacer width="10" height="10" />
          <tr:panelFormLayout maxColumns="2" rows="10" >
            <f:facet name="footer"/>
            <tr:panelBox text="Publiline" background="medium" >
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="[Configuración]"/>
              <tr:panelList rows="11" maxColumns="1">
                <tr:panelHorizontalLayout rendered="#{gestionPendientes.visible}">
                  <tr:commandLink text="Gestión de Centros"
                                  action="#{menuAdminBean.mantCentro}"
                                  rendered="#{gestionPendientes.visible}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de modelos de pliego"
                                  action="#{menuAdminBean.gestionModelosPliego}"/>
                </tr:panelHorizontalLayout>
               </tr:panelList>
              <tr:panelHeader text="[Administración]"/>
              <tr:panelList rows="11" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Condiciones de flujo" action="#{menuAdminBean.consultaTransiciones}" />
                  </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                 <tr:commandLink text="Consultar expedientes"
                    			action="#{listadoGeneralExpedientesAdmin.ejecuta}" />
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                 <tr:commandLink text="Administración de plazos de bloqueo"
                    	action="#{menuAdminBean.adminPlazosBloqueo}" />
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                 <tr:commandLink text="Ampliar plazos de bloqueo"
                    			action="#{ampliacionPlazosBloqueo.ejecuta}" />
                </tr:panelHorizontalLayout>

                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de tipos de bonificación"
                                  action="#{menuAdminBean.gestionTiposBonificacion}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de solicitudes de documento contable"
                                  action="#{menuAdminBean.gestionSolicitudesDocumentoContable}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de roles de usuarios en Workflow"
                                  action="#{menuAdminBean.gestionUsuariosWF}"/>
                </tr:panelHorizontalLayout>
               <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de secciones"
                                  action="#{menuAdminBean.gestionSeccion}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de tipos de ficheros"
                                  action="#{menuAdminBean.gestionTipoFichero}"/>
                </tr:panelHorizontalLayout>
				<tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de ámbitos"
                                  action="#{menuAdminBean.gestionAmbitos}"/>
                </tr:panelHorizontalLayout>
               	<tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de organos de contratación"
                                  action="#{menuAdminBean.mantOrganoContratacion}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Selección de clasificaciones"
                                  action="#{menuAdminBean.gestionClasificacionesSeleccionables}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de tipo/s de contrato por artículo"
                                  action="#{menuAdminBean.gestionTipoContratoArticulo}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de tipos de necesidad por artículo"
                                  action="#{menuAdminBean.gestionClasificacionArticuloTipoNecesidad}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de dias de exposición de necesidades por artículo"
                                  action="#{menuAdminBean.gestionClasificacionArticuloDiasExposicionNecesidades}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                    <tr:commandLink text="Gestión de bloques del menú"
                                  action="#{menuAdminBean.gestionBloqueMenuRol}"/>
                  </tr:panelHorizontalLayout>
				<tr:panelHorizontalLayout>
                  <tr:commandLink text="Elementos configurables por momentos"
                                  action="#{menuAdminBean.gestionElementosConfigurablesPorMomento}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Consultas configuradas por Rol-Estados"
                                  action="#{menuAdminBean.consultasConfiguradas}"/>
                </tr:panelHorizontalLayout>
                
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de tipos de contrato"
                                  action="#{menuAdminBean.gestionTipoContrato}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de procedimientos de contratacion"
                                  action="#{menuAdminBean.gestionProcedimientoContratacion}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de formas de adjudicacion"
                                  action="#{menuAdminBean.gestionFormaAdjudicacion}"/>
                </tr:panelHorizontalLayout>
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Gestión de procedimientos Complementarios de seleccion"
                                  action="#{menuAdminBean.gestionProcedimientoComplementarioSeleccion}"/>
                </tr:panelHorizontalLayout>
                
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="Informe de situación"
                                  action="#{menuAdminBean.consultaInformeSituacion}"/>
                </tr:panelHorizontalLayout>
                
                <tr:panelHorizontalLayout>
                 <tr:commandLink text="Correcciones y modificaciones"
                    			action="#{correccionesYModificaciones.ejecuta}" />
                </tr:panelHorizontalLayout>
                
              </tr:panelList>
              <tr:spacer width="10" height="1"/>
            </tr:panelBox>
            <tr:spacer width="10" height="10"/>
            <tr:spacer width="10" height="10"/>
            <tr:panelBox text="Común" inlineStyle="" background="medium"
                         rendered="#{gestionPendientes.visible}">
              <tr:spacer width="10" height="10"/>
              <tr:panelHeader text="[Gestión común]"/>
              <tr:panelList rows="11" maxColumns="1">
                <tr:panelHorizontalLayout>
                  <tr:commandLink text="TODO"/>
                </tr:panelHorizontalLayout>
              </tr:panelList>
              <tr:spacer width="10" height="1"/>
            </tr:panelBox>
            <tr:spacer width="10" height="10"/>
          </tr:panelFormLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
