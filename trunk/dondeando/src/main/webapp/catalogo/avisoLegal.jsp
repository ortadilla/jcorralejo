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
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resPed['TOTALES_PROCEDIMIENTO'] }">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<h:form>
				<tr:panelFormLayout>
					<tr:panelBox text="#{resCat['AVISO_LEGAL']}" inlineStyle="width:100%;" >
						<f:verbatim>
							<p>
							<p align="justify">Los derechos de propiedad intelectual de esta base de datos se encuentran protegidos y 
												pertenecen al Servicio Andaluz de Salud.</p>
							<p align="justify">El usuario podrá visualizar su contenido e incluso imprimirlo 
												siempre para su uso personal y privado.</p>
							<p align="justify">Queda prohibida la comercialización del derecho de acceso y 
											la reproducción y/o comercialización parcial o total, por cualquier medio físico, lógico o 
											electrónico, de sus contenidos sin autorización previa y escrita del Servicio Andaluz de 
											Salud, quien se reserva el derecho a su modificación y las condiciones para acceder a ellos 
											en cualquier momento y sin aviso previo.</p>
							</p>
						</f:verbatim>
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['CERRAR']}">
								<tr:returnActionListener/>
							</tr:commandButton>
						</tr:panelHorizontalLayout>
					</tr:panelBox>
				</tr:panelFormLayout>
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
