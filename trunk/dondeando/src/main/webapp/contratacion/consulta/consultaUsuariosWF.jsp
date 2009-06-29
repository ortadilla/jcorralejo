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
      <trh:head title="Gestión de usuarios de workflow">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
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
          <tr:panelHeader text="Gestión de roles de usuarios en Workflow"/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%">
            <tr:messages/>
            <tr:showDetailHeader id="detalle" text="Criterios de búsqueda" disclosed="#{!consultaUsuariosWFBean.haRecuperadoDatos}">
	          <tr:spacer width="10" height="10"/>
              <tr:panelFormLayout >
                <tr:panelLabelAndMessage label="Login:" labelStyle="width: 150px;" >
	                <tr:inputText rows="1"
	                              columns="10"
	                              value="#{formBusquedaUsuariosWF.login}"/>
				</tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Con rol/es asignados:" labelStyle="width: 150px;" >
                	<tr:panelHorizontalLayout>
	                    <tr:selectBooleanCheckbox
	                                simple="true"
	                                value="#{formBusquedaUsuariosWF.busquedaCombinadaWFSoloConRoles}" text="Si"
	                                valueChangeListener="#{formBusquedaUsuariosWF.cambiaValorBusquedaCombinadaCon}"
	                                binding="#{formBusquedaUsuariosWFBinding.chkBusquedaCombinadaCon}"
	                                partialTriggers="chkBusquedaCombinadaCon chkBusquedaCombinadaSin" id="chkBusquedaCombinadaCon"
	                                autoSubmit="true"
	                            />
	                    <tr:selectBooleanCheckbox
	                                simple="true"
	                                value="#{formBusquedaUsuariosWF.busquedaCombinadaWFSoloSinRoles}" text="No"
	                                valueChangeListener="#{formBusquedaUsuariosWF.cambiaValorBusquedaCombinadaSin}"
	                                binding="#{formBusquedaUsuariosWFBinding.chkBusquedaCombinadaSin}"
	                                partialTriggers="chkBusquedaCombinadaCon chkBusquedaCombinadaSin" id="chkBusquedaCombinadaSin"
	                                autoSubmit="true"
	                            />
                    </tr:panelHorizontalLayout>
				</tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Rol:">
                  <tr:selectOneChoice simple="true" value="#{formBusquedaUsuariosWF.rol}" partialTriggers="chkBusquedaCombinadaSin"
                  	  binding="#{formBusquedaUsuariosWFBinding.socRol}"
                  	  unselectedLabel=" "  >
                	   <f:selectItems value="#{gestorListaPerfilesWF.listaElementos}" />
                  </tr:selectOneChoice>
                </tr:panelLabelAndMessage>

			  </tr:panelFormLayout>
              <tr:spacer width="10" height="10"/>
              <tr:panelHorizontalLayout halign="center">
                <tr:commandButton text="Buscar"
                                  action="#{consultaUsuariosWFBean.encontrar}"/>
              </tr:panelHorizontalLayout>
            </tr:showDetailHeader>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
          <tr:table emptyText="No items were found" width="100%" var="usuario" rowSelection="single"
          			value="#{tablaUsuariosWFBean.modelo}" autoSubmit="true"
                    selectionListener="#{tablaUsuariosWFBean.eventoSeleccion}">
            <tr:column  headerText="Nombre">
              <tr:outputText value="#{usuario.login}"/>
            </tr:column>
            <tr:column  headerText="Persona">
              <tr:outputText value="#{usuario.nombrePersona}"/>
            </tr:column>
            <tr:column  headerText="Activo">
              <tr:outputText value="#{usuario.activo}"/>
            </tr:column>
			<tr:column  headerText="Roles">
				<tr:iterator rows="25" var="roles" value="#{usuario.roles}">
					<tr:panelList>
						<tr:outputText value="#{roles.label}" />
					</tr:panelList>
				</tr:iterator>
			</tr:column>
            <f:facet name="actions">
            <tr:panelButtonBar>
               <tr:commandButton text="Modificar roles" action="#{modificarUsuarioWF.ejecuta}" />
            </tr:panelButtonBar>
            </f:facet>
          </tr:table>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
	          <tr:commandButton text="Menú principal"
    	                          action="#{botonMenuAdminBean.ejecuta}"/>
		  </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>