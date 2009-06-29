<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOfe" var="mensajesOfe" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />

		<trh:html>
			<trh:head title="#{mensajesOfe['MODIFICAR_DENOMINACION_COMERCIAL_TITULO']}">
				<meta http-equiv="Content-Type"
					content="text/html; charset=windows-1252" />
				<trh:script source="/include/libreriaGEOS.js">
        			<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
				</trh:script>
			</trh:head>
			<trh:body>
				<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
				<tr:statusIndicator id="indicador">
					<f:facet name="busy">
						<f:verbatim>
							<div id="divEspera">
								<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>
							</div>
						</f:verbatim>
					</f:facet>
					<f:facet name="ready">
					</f:facet>
				</tr:statusIndicator>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelBox text="#{mensajesOfe['MODIFICAR_DENOMINACION_COMERCIAL_TITULO']}" inlineStyle="width: 100%;">
							<tr:panelHorizontalLayout halign="left"
													inlineStyle="border-width:1px; margin-top: 5px; padding: 7px">
								<tr:outputText value="#{mensajesOfe['AVISO_MODIFICAR_DENOMINACION_COMERCIAL']}"
													inlineStyle="font-weight: bolder"/>
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="left" valign="top">				
								<tr:outputText value="#{mensajesOfe['NUEVA_DENOMINACION']}"/>
								<tr:inputText id="nuevaDenominacion"
									value="#{ofertas_modificarDenominacionComercialBean.denominacionComercial}"
									columns="60" rows="3" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="left" 
										inlineStyle="margin-top: 7px; width:100%; color: white; background: #669966; font-weight: bolder; font-size: 110%; padding: 5px; padding-left: 2px; padding-right: 10px;">
								<tr:outputText value="#{ofertas_modificarDenominacionComercialBean.tituloCIP}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="5" />
							<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
								id="tablaResultados"
								value="#{ofertas_modificarDenominacionComercialBean.listaOfertasVista}"
								var="fila" 
								rows="10"
								rowBandingInterval="1"
	                    	    columnBandingInterval="0"
	                            width="100%">
	                            <tr:column headerText="">
	                            	<tr:selectBooleanCheckbox selected="#{fila.seleccionado}" disabled="true" />
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOrg['DENOMINACION_COMERCIAL']}">
	                            	<tr:outputText value="#{fila.denominacion}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['REFFABRICANTE']}">
	                            	<tr:outputText value="#{fila.refFabricante}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['PROVEEDOR']}">
	                            	<tr:outputText value="#{fila.proveedor}"/>
	                            </tr:column>
							</tr:table>
							<tr:panelHorizontalLayout halign="right">
								<tr:commandButton text="#{mensajesCore['CONFIRMAR']}" id="Aceptar"
									action="#{ofertas_modificarDenominacionComercialBean.confirmar}"/>
								<tr:spacer width="20" height="0" />
								<tr:commandButton text="#{mensajesCore['CANCELAR']}"
									action="#{ofertas_modificarDenominacionComercialBean.cancelar}" />
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:panelPage>
				</tr:form>
			</trh:body>
		</trh:html>
	</f:view>
</jsp:root>