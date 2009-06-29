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
    <f:loadBundle basename="mensajesOrg" var="resOrg" />
    <f:loadBundle basename="mensajesCore" var="resCore" />
  <trh:html>
    <trh:head title="#{resOrg['TITULO_RESUMEN_SOLICITUDES']}">
    	<meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
	      <trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
		</trh:script>
		<tr:outputText escape="false" value="#{htmlHead}"/>
    </trh:head>
    <trh:body>
      <geos:cabeceraPagina/>
      <tr:form onsubmit="bloquearPantalla(this);">
        <tr:panelPage>
        	<tr:panelHeader text="#{resOrg['TITULO_RESUMEN_SOLICITUDES']}"/>
            <tr:panelGroupLayout rendered="#{organizacion_resumenSolicitudesBean.mostrarRadioButton}">
              <tr:panelHorizontalLayout>
                <tr:outputText value="#{resOrg['CONSULTAR_POR_ESTADO']}"/>
                <tr:selectOneRadio value="#{organizacion_resumenSolicitudesBean.valorRadio}"
                                   autoSubmit="true" id="radioButton"
                                   valueChangeListener="#{organizacion_resumenSolicitudesBean.cambiarConsulta}">
                  <f:selectItem itemLabel="#{resOrg['PENDVALIDAR']}"
                                itemValue="PENDVALIDAR"/>
                  <f:selectItem itemLabel="#{resOrg['PENDCONFIRMAR']}"
                                itemValue="PENDCONFIRMAR"/>
                </tr:selectOneRadio>
              </tr:panelHorizontalLayout>
            </tr:panelGroupLayout>
            <tr:table emptyText="#{resCore['NO_ELEMENTOS']}"
                    binding="#{organizacion_resumenSolicitudesBinding.tabla}"
                    value="#{organizacion_resumenSolicitudesBean.listaAgrupadores}" partialTriggers="radioButton" var="agrupador"
                      rowBandingInterval="1">
            <tr:column sortable="false" headerText="" id="entidades">
              <tr:outputText value="#{agrupador.descripcion}" truncateAt="0"/>
            </tr:column>
          </tr:table>
          <f:facet name="messages">
            <tr:messages/>
          </f:facet>
          </tr:panelPage>
      </tr:form>
	    <tr:panelHorizontalLayout inlineStyle="width: 100%;">
			<tr:outputText escape="false" value="#{htmlPie}"/>
		</tr:panelHorizontalLayout>
    </trh:body>
  </trh:html>
</f:view>
</jsp:root>