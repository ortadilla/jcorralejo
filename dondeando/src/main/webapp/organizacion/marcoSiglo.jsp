<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html"
    xmlns:ui="http://www.sun.com/web/ui"
    xmlns:c="http://java.sun.com/jstl/core"
    xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
    <f:loadBundle basename="mensajesCore" var="resCore"/>
    <trh:html>
      <trh:head title="#{organizacion_marcoSigloBean.tituloMarco}">
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"/>
        <trh:script source="/include/libreriaGEOS.js">
     		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
		</trh:script>
		<tr:outputText escape="false" value="#{htmlHead}"/>
      </trh:head>
      <trh:body onload = "document.getElementById('marcoExterno').src=document.getElementById('urlReal').value">
      	<geos:cabeceraPagina />
      	<tr:panelBox text="#{organizacion_marcoSigloBean.tituloMarco}" inlineStyle="width:100%;height:100%">
			<tr:inputHidden id="urlReal"
				value="#{organizacion_marcoSigloBean.urlReal}"/>
			<f:verbatim>
				<iframe id="marcoExterno" frameborder="0"  style="border:1px solid #000000" width="100%" height="500px"/>
			</f:verbatim>
		</tr:panelBox>
		<tr:panelHorizontalLayout inlineStyle="width: 100%;">
			<tr:outputText escape="false" value="#{htmlPie}"/>
		</tr:panelHorizontalLayout>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>