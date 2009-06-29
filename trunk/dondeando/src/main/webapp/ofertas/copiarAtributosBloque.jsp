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
			<trh:head title="#{mensajesOfe['COPIAR_ATRIBUTOS_DINAMICOS']}">
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
						<tr:panelBox text="#{mensajesOfe['COPIAR_ATRIBUTOS_DINAMICOS']}" inlineStyle="width: 100%;">
							<tr:panelHorizontalLayout halign="left">
								<tr:outputText value="#{mensajesOfe['ORIGEN']}" rendered="#{ofertas_copiarAtributosBloqueBean.verOrigen}"/>
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="10" />
							<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
								id="tablaOrigen"
								value="#{ofertas_copiarAtributosBloqueBean.listaOrigen}"
								var="filaOrigen" 
								rows="1"
								rowBandingInterval="1"
	                    	    columnBandingInterval="0"
	                            rowSelection="none" width="100%"
	                            rendered="#{ofertas_copiarAtributosBloqueBean.verOrigen}">
	                            <tr:column headerText="#{mensajesOfe['MODELO']}">
	                            	<tr:outputText value="#{filaOrigen.modelo}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['REFFABRICANTE']}">
	                            	<tr:outputText value="#{filaOrigen.refFabricante}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['FECHAOFERTA']}">
	                            	<tr:outputText value="#{filaOrigen.fechaOferta}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['ESTADOOFERTA']}">
	                            	<tr:outputText value="#{mensajesOfe[filaOrigen.estadoOferta]}"/>
	                            </tr:column>
							</tr:table>
							<tr:panelBox inlineStyle="width: 100%;">
								<tr:iterator id="itBloque" var="bloque" varStatus="varStatus"
									value="#{ofertas_copiarAtributosBloqueBean.listaBloques}">
									<tr:panelHorizontalLayout>
										<tr:outputText value="#{bloque.nombreBloque}" inlineStyle="font-weight:bolder;"/>
										<tr:selectBooleanCheckbox id="select" selected="#{bloque.activado}" />
									</tr:panelHorizontalLayout>
									<trh:tableLayout>
										<tr:iterator id="itAtributos" var="atributo" varStatus="varStatus"
											value="#{bloque.listaAtributosVista}">
											<trh:rowLayout rendered="#{atributo.visible}">
												<tr:spacer width="30" height="0" />
												<trh:cellFormat valign="TOP">
													<tr:outputText value="#{atributo.descripcion}"/>
												</trh:cellFormat>
												<tr:spacer width="10" height="0" />
												<tr:outputText value="#{atributo.valor}" rendered="#{ofertas_copiarAtributosBloqueBean.verOrigen}"/>
											</trh:rowLayout>
										</tr:iterator>
									</trh:tableLayout>
								</tr:iterator>
							</tr:panelBox>
							<tr:spacer width="20" height="10" />							
							<tr:panelHorizontalLayout halign="left">
								<tr:outputText value="#{ofertas_copiarAtributosBloqueBean.verOrigen?mensajesOfe['COPIAR_A']:mensajesOfe['COPIAR_DE']}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="10" />
							<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
								id="tablaResultados"
								value="#{ofertas_copiarAtributosBloqueBean.listaResultados}"
								var="fila" 
								rows="10"
								rowBandingInterval="1"
	                    	    columnBandingInterval="0"
								binding="#{ofertas_copiarAtributosBloqueBinding.tabla}"
	                            rowSelection="multiple" width="100%">
	                            <tr:column headerText="#{mensajesOfe['MODELO']}">
	                            	<tr:outputText value="#{fila.modelo}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['REFFABRICANTE']}">
	                            	<tr:outputText value="#{fila.refFabricante}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['FECHAOFERTA']}">
	                            	<tr:outputText value="#{fila.fechaOferta}"/>
	                            </tr:column>
	                            <tr:column headerText="#{mensajesOfe['ESTADOOFERTA']}">
	                            	<tr:outputText value="#{mensajesOfe[fila.estadoOferta]}"/>
	                            </tr:column>
							</tr:table>
							<tr:panelHorizontalLayout halign="right">
								<tr:commandButton text="#{mensajesCore['ACEPTAR']}" id="Aceptar"
									action="#{ofertas_copiarAtributosBloqueBean.aceptar}"
									 disabled="#{ofertas_copiarAtributosBloqueBean.desactivarAceptar}"/>
								<tr:spacer width="20" height="0" />
								<tr:commandButton text="#{mensajesCore['CANCELAR']}"
									action="#{ofertas_copiarAtributosBloqueBean.cancelar}" />
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:panelPage>
				</tr:form>
			</trh:body>
		</trh:html>
	</f:view>
</jsp:root>