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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<trh:html>

		<trh:head>
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>

		<trh:body initialFocusId="nombre">

			<geos:cabeceraPagina />

			<tr:form defaultCommand="btnAceptar" id="formCrearUsuario"
				usesUpload="true">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{editarUsuarioBean.tituloPagina}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_USUARIO']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['USUARIO']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="20" value="#{editarUsuarioBean.login}"
										id="login" maximumLength="20"/>
									<trh:cellFormat rowSpan="3">
										<tr:image source="#{editarUsuarioBean.urlImagenUsuario}"
											shortDesc="#{resCore['USUARIO']}  #{editarUsuarioBean.login}"
											inlineStyle="height: 70px;border-style: solid; border-width: 1px;" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout rendered="#{editarUsuarioBean.mostrarPass}" >
									<tr:outputText value="#{resCore['CONTRASENA']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="20"
										value="#{editarUsuarioBean.password}" id="pass" 
										maximumLength="20" secret="true" />
								</trh:rowLayout>
								<trh:rowLayout rendered="#{editarUsuarioBean.mostrarPass} *">
									<tr:outputText value="#{resCore['REPETIR_CONTRASENA']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="20"
										value="#{editarUsuarioBean.password2}" id="pass2"
										 maximumLength="20" secret="true" />
								</trh:rowLayout>
								<trh:rowLayout/>
								<trh:rowLayout/> <!-- Dejar estos huecos para que quepa bien la imagen -->
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NOMBRE']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText columns="30" value="#{editarUsuarioBean.nombre}"
											id="nombre"  maximumLength="50" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['APELLIDOS']}" 
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText columns="60" 
											value="#{editarUsuarioBean.apellidos}" id="apellidos"/>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['DIRECCION']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText columns="80" 
											value="#{editarUsuarioBean.direccion}" id="direccion" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['EMAIL']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText columns="80" value="#{editarUsuarioBean.email}"
											id="email" styleClass="form">
											<tr:validator binding="#{editarUsuarioBean.validatorEmail}" />
										</tr:inputText>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout
									rendered="#{editarUsuarioBean.mostrarTipoUsuario}">
									<tr:outputText value="#{resCore['TIPO_USUARIO']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:selectOneChoice value="#{editarUsuarioBean.tipoUsuario}" >
											<f:selectItems id="selectTipoUsuario"
												value="#{editarUsuarioBean.selectTipoUsuario}" />
										</tr:selectOneChoice>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['AVATAR']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:inputFile value="#{editarUsuarioBean.fileAvatar}" />
									</trh:cellFormat>
								</trh:rowLayout>
							</trh:tableLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{editarUsuarioBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true"
									action="#{editarUsuarioBean.cancelar}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="20" />

						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
