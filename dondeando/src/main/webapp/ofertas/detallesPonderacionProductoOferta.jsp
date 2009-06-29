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

		<trh:html>
			<trh:head title="#{mensajesOfe['FICHA_PONDERACION_PRODUCTO']}">
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
			<tr:panelFormLayout>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelBox text="#{mensajesOfe['FICHA_PONDERACION_PRODUCTO']}" inlineStyle="width: 100%;">
						<tr:panelHorizontalLayout inlineStyle="width: 100%;">
							<tr:spacer width="3" height="20" />
							<tr:outputText inlineStyle="font-weight: bold" value="#{mensajesOfe['COMENTARIO_EVALUACION']}"/>
						</tr:panelHorizontalLayout>
						
						<tr:panelHorizontalLayout inlineStyle="width: 100%;">
							<tr:spacer width="3" height="30" />
							<tr:outputText id="comentarioEvaluacion"
												   value="#{ofertas_detallesPonderacionProductoOfertaBean.comentarioEvaluacion}"/>
						</tr:panelHorizontalLayout>
						<tr:panelHorizontalLayout inlineStyle="width: 100%;">
							<tr:spacer width="2" height="20" />
							<tr:outputText value="#{mensajesOfe['EVALUACION']}" inlineStyle="font-weight: bold"/>
							<tr:outputText  value="#{ofertas_detallesPonderacionProductoOfertaBean.procEvaluacion}" inlineStyle="font-weight: bold"/>
						</tr:panelHorizontalLayout>
						
						<trh:tableLayout>
							<tr:iterator value="#{ofertas_detallesPonderacionProductoOfertaBean.criteriosYNotas}" var="var">
									<trh:rowLayout>
										<tr:outputText value="#{var.indice}" />
										<tr:outputText value="#{var.titulo}" />
										<tr:outputText value="#{var.nota}" />
									</trh:rowLayout>
							</tr:iterator>
							<trh:rowLayout>
							<trh:cellFormat columnSpan="2" inlineStyle="background-color:  #669966">
								<tr:outputText  value="#{mensajesOfe['NOTA_TOTAL']}" inlineStyle="font-weight: bold"/>
							</trh:cellFormat>
							<trh:cellFormat inlineStyle="background-color:  #669966">
								<tr:outputText  value="#{ofertas_detallesPonderacionProductoOfertaBean.notaTotal}" />
							</trh:cellFormat>
							</trh:rowLayout>
						</trh:tableLayout>
						<tr:spacer width="5" height="20" />
						<tr:panelHorizontalLayout>
							<tr:commandButton text="#{mensajesCore['VOLVER']}" 
								action="#{ofertas_detallesPonderacionProductoOfertaBean.volver}" />
						</tr:panelHorizontalLayout>
				
						</tr:panelBox>
							
		
					</tr:panelPage>
					</tr:form>
					</tr:panelFormLayout>
			</trh:body>
		</trh:html>
	</f:view>
</jsp:root>