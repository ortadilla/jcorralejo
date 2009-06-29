<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<trh:html>
		<trh:head title="Consulta de BD">
			<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body initialFocusId="#{organizacion_consultarBD.autenticado ? 'sql' : 'password'}">
<!--		    <geos:cabeceraPagina />-->
            <trh:tableLayout width="100%" cellPadding="0" cellSpacing="0">
                <trh:rowLayout>
                    <trh:cellFormat>
                        <tr:image source="/imagenes/logo_sal_cabecera.gif" inlineStyle="height:60px" 
                                  shortDesc="#{organizacion_paginasBean.versionYbd}"/>
                    </trh:cellFormat>
                </trh:rowLayout>
                <trh:rowLayout>
                    <trh:cellFormat inlineStyle="background-image: url(../skins/geos2/skin_images/menuBarBackground.png);">
                        <tr:goButton text="Menú" accessKey="M" destination="#{core_utilAplicacion.urlMenu}"/>
                    </trh:cellFormat>
                </trh:rowLayout>
            </trh:tableLayout>
			<tr:form rendered="#{!organizacion_consultarBD.autenticado}" defaultCommand="botonAceptar">
                <tr:spacer height="20"/>
		        <tr:panelHorizontalLayout halign="center">
                    <tr:inputText id="password" label="Contraseña:"
                                  value="#{organizacion_consultarBD.password}" secret="true" />
                    <tr:commandButton id="botonAceptar" text="Aceptar"
                                      actionListener="#{organizacion_consultarBD.accionAutenticar}" />
				</tr:panelHorizontalLayout>
			</tr:form>
			<tr:form rendered="#{organizacion_consultarBD.autenticado}">
				<tr:navigationPane hint="tabs">
					<tr:commandNavigationItem id="tab1" text="Base de Datos" 
					                          actionListener="#{organizacion_consultarBD.cambiarFacet}" 
											  selected="#{organizacion_consultarBD.facet == 'bd'}"/>
					<tr:commandNavigationItem id="tab2" text="Archivos"      
					                          actionListener="#{organizacion_consultarBD.cambiarFacet}" 
											  selected="#{organizacion_consultarBD.facet == 'archivos'}"/>
				</tr:navigationPane>
			</tr:form>
			<tr:messages />
			<tr:switcher defaultFacet="bd" 
			             facetName="#{organizacion_consultarBD.facet}"
			             rendered="#{organizacion_consultarBD.autenticado}"> 
			<f:facet name="bd"> 
			<tr:form defaultCommand="ejecutar">
				<tr:panelBox text="Base de Datos">
					<tr:showDetailHeader id="contenedorDesplegable" text="Cargar previa">
	                    <tr:table value="#{organizacion_consultarBD.previos}" var="fila" varStatus="vs">
                    		<tr:column>
                    			<tr:commandLink text="#{fila}" actionListener="#{organizacion_consultarBD.copiarPrevio}">
                    				<f:attribute name="pos" value="#{vs.index}"/>
                    			</tr:commandLink>
                    		</tr:column>
	                    </tr:table>
					</tr:showDetailHeader>
					<tr:inputText id="sql" label="SQL:" value="#{organizacion_consultarBD.sql}" columns="100" rows="6" />
					<tr:commandButton id="ejecutar" text="Ejecutar"
									  actionListener="#{organizacion_consultarBD.accionEjecutarSql}" />
	                <tr:outputText value="#{organizacion_consultarBD.texto}" rendered="#{organizacion_consultarBD.busqueda}"/>
                    <tr:inputText  value="#{organizacion_consultarBD.texto}" rendered="#{!organizacion_consultarBD.busqueda}"
                                   columns="100" rows="5" readOnly="true"/>
                    <tr:table value="#{organizacion_consultarBD.resultados}" var="fila"
                              rendered="#{organizacion_consultarBD.busqueda}">
                    	<tr:forEach items="#{organizacion_consultarBD.cabeceras}" var="col" varStatus="vs">
                    		<tr:column headerText="#{col}">
                    			<tr:outputText value="#{fila[vs.index]}"/>
                    		</tr:column>
                    	</tr:forEach>
                    </tr:table>
				</tr:panelBox>
			</tr:form>
			</f:facet> 
			<f:facet name="archivos"> 
			<tr:form defaultCommand="ir">
				<tr:panelBox text="Archivos">
					<tr:panelHorizontalLayout>
						<tr:inputText id="dir" label="Dir:" value="#{organizacion_consultarBD.directorio}" columns="100"/>
						<tr:commandButton id="ir" text="->"
										  actionListener="#{organizacion_consultarBD.accionCargarDir}" />
                    </tr:panelHorizontalLayout>
                    <tr:table value="#{organizacion_consultarBD.archivos}" var="fila"
					          partialTriggers="dir">
                   		<tr:column headerText="Archivo">
                   			<tr:commandLink text="#{fila.nombre}" 
                   			                actionListener="#{organizacion_consultarBD.descargar}">
                   				<tr:attribute name="archivo" value="#{fila}"/>
                			</tr:commandLink>
                			
                   		</tr:column>
                   		<tr:column headerText="KBs" align="end">
                   			<tr:outputText value="#{fila.kbs}" />
                   		</tr:column>
                   		<tr:column headerText="Descargar">
							<tr:goButton text="Descargar"
                                         destination="#{fila.enlace}"
                                         targetFrame="_blank"
                                         rendered="#{fila.enlace != null}"/>					
                   		</tr:column>
                    </tr:table>
				</tr:panelBox>
			</tr:form>
			</f:facet> 
			</tr:switcher> 
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
