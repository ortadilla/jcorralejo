<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">

	<f:loadBundle basename="mensajesCore" var="resCore" />
	<!-- Se añaden aquí todos los mensajes para las traducciones automáticas de la 
    	tabla de resultados -->
	<f:loadBundle basename="mensajesCat" var="resCat" />
	<f:loadBundle basename="mensajesEmp" var="resEmp" />
	<f:loadBundle basename="mensajesOrg" var="resOrg" />
	<f:loadBundle basename="mensajesOfe" var="resOfe" />
	<f:loadBundle basename="mensajesPed" var="resPed" />
	<f:loadBundle basename="mensajesNec" var="resNec" />
	<f:loadBundle basename="mensajesCon" var="resCon" />
	<f:loadBundle basename="mensajesAcr" var="resAcr" />

	<tr:spacer width="10" height="10" />
	
	<tr:panelBox
		text="#{core_detallesSolicitud.objRespaldo.tituloPanel}"
		background="light" inlineStyle="width: 100%;"
		contentStyle="width: 100%;">

		<tr:panelHorizontalLayout id="panelCriterios"
			binding="#{core_detallesSolicitudBinding.panelDetalles}"
			inlineStyle="width: 100%; " />

	</tr:panelBox>

</jsp:root>
