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
   <f:loadBundle basename="mensajesCat" var="mensajesCat" />
   <f:loadBundle basename="mensajesCore" var="mensajesCore" />
   <f:loadBundle basename="mensajesPed" var="mensajesPed" />
   <f:loadBundle basename="mensajesOrg" var="mensajesOrg" />
   <f:loadBundle basename="mensajesOfe" var="mensajesOfe" />
 
   <trh:html>
   <trh:head title="Modificación en la Prioridad de Ofertas">
    <meta http-equiv="Content-Type"
     content="text/html; charset=windows-1252" />
     <trh:script source="/include/libreriaGEOS.js">
     	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
	 </trh:script>
   </trh:head>
   <trh:body>
    <geos:cabeceraPagina/>
    <tr:form onsubmit="bloquearPantalla(this);">
 
     <tr:panelPage id="panelPage1">
      <tr:panelHeader text="#{mensajesPed['PRIORIZACION_OFERTAS']}"/>
      <f:facet name="messages">
       <tr:messages />
      </f:facet>
      <f:facet name="actions" />
<!--      <tr:spacer width="10" height="20" />-->
<!--      text="#{mensajesCore['RESULTADOS']}"-->
      <tr:panelBox  inlineStyle="width: 100%;">
       <tr:inputText label="#{mensajesCat['ARTICULO']}" disabled="true"  wrap="off" rows="3" columns="100"
       value="#{pedidos_prioridadOfertaBean.articuloSeleccionado}"/>
       <tr:spacer width="10" height="20" />
       <tr:panelHorizontalLayout>
           <tr:commandButton text="#{mensajesCore['ACEPTAR']}"
            action="#{pedidos_prioridadOfertaBean.botonAceptar}"
            actionListener="#{pedidos_prioridadOfertaBean.listenerBoton}"
            returnListener="#{pedidos_prioridadOfertaBean.guardarCambios}"
            useWindow="true" partialSubmit="true" windowHeight="500"
            windowWidth="620"
            rendered="#{!pedidos_prioridadOfertaBean.verTodasOfertas}"/>
           <tr:spacer width="10" height="0" />
           <tr:commandButton text="#{mensajesCore['CANCELAR']}"
            action="#{pedidos_prioridadOfertaBean.botonCancelar}" />
           <tr:spacer width="20" height="0" />
           <tr:commandButton text="#{mensajesPed['VER_TODAS']}"
            action="#{pedidos_prioridadOfertaBean.botonVerTodas}"
             onclick="#{pedidos_prioridadOfertaBean.javascriptIgnorarCambios}"
             rendered="#{!pedidos_prioridadOfertaBean.verTodasOfertas}"/>
           <tr:spacer width="10" height="0" />
         </tr:panelHorizontalLayout>
       <tr:table emptyText="" id="tabla" rows="10" var="var" varStatus="varStatus" width="100%"
           rowBandingInterval="1" columnBandingInterval="0" value="#{pedidos_prioridadOfertaBean.lista}"
           rowSelection="single"
           selectedRowKeys="#{pedidos_prioridadOfertaBean.estadoTabla}"
           partialTriggers="tabla">
        <f:facet name="actions">
         <tr:panelGroupLayout layout="vertical">
         <tr:spacer width="10" height="10" />
         <tr:panelHorizontalLayout>
           <tr:commandButton text="#{mensajesPed['OFERTA_TECNICA']}"
            action="#{pedidos_prioridadOfertaBean.botonMantenimientoOfertas}"
            actionListener="#{pedidos_prioridadOfertaBean.listenerBoton}"/>
           <tr:spacer width="10" height="0" />
           <tr:commandButton text="#{mensajesPed['INFLOGISTICA']}"
            action="#{pedidos_prioridadOfertaBean.botonMantenimientoInfLog}"
            actionListener="#{pedidos_prioridadOfertaBean.listenerBoton}"/>
           <tr:spacer width="10" height="0" />
           <tr:commandButton text="#{mensajesPed['TARIFA']}"
            action="#{pedidos_prioridadOfertaBean.botonMantenimientoTarifas}"
            actionListener="#{pedidos_prioridadOfertaBean.listenerBoton}"/>
          </tr:panelHorizontalLayout>
          <tr:spacer width="10" height="0" />
          </tr:panelGroupLayout>
        </f:facet>
<!-- <f:facet name="selection">-->
<!-- <tr:tableSelectOne text="Select items and ..." />-->
<!-- </f:facet>-->
        <tr:column sortable="false"
         rendered="#{pedidos_prioridadOfertaBean.modificar}">
         <tr:commandLink rendered="#{var.subibleOfe}"
          actionListener="#{pedidos_prioridadOfertaBean.botonSubir}">
          <tr:attribute name="idxFila" value="#{varStatus.index}" />
          <tr:image source="/imagenes/flecha_arriba.gif" />
         </tr:commandLink>
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesPed['DENOMINACION_COMERCIAL']}">
         <tr:outputText value="#{var.modelo}"/>
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesCat['CIP']}" >
         <tr:outputText value="#{var.CIP}"/>
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesPed['REF_FABRICANTE']}">
         <tr:outputText value="#{var.refFabricante}"/>
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesPed['PROVEEDOR']}">
         <tr:outputText value="#{var.proveedor}"/>
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesPed['REFDISTRIBUIDOR']}">
         <tr:outputText value="#{var.refDistribuidor}"/>
        </tr:column>
        <tr:column sortable="false">
            <f:facet name="header">
                 <tr:outputText value="#{mensajesPed['DT']}" shortDesc="#{mensajesPed['DETERMINACION_TIPO']}"/>
               </f:facet>
         <tr:outputText value="#{var.DT}"/>
        </tr:column>
        <tr:column sortable="false" rendered="#{pedidos_prioridadOfertaBean.modificar}">
            <tr:commandLink rendered="#{var.subibleInf}"
                         actionListener="#{pedidos_prioridadOfertaBean.botonSubir}">
             <tr:attribute name="idxFila" value="#{varStatus.index}"/>
             <tr:image source="/imagenes/flecha_arriba.gif" />
            </tr:commandLink>
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesCat['PRESENTACION']}" >
         <tr:outputText value="#{var.presentacion}"/>
        </tr:column>
        <tr:column sortable="false" align="right" headerText="#{mensajesOfe['PRECIOUNIDAD']}" >
         <tr:outputText value="#{var.precioUnidad}"/>
        </tr:column>
        <tr:column sortable="false" rendered="#{pedidos_prioridadOfertaBean.modificar}">
            <tr:commandLink rendered="#{var.subibleTar}"
                         actionListener="#{pedidos_prioridadOfertaBean.botonSubir}">
             <tr:attribute name="idxFila" value="#{varStatus.index}"/>
             <tr:image source="/imagenes/flecha_arriba.gif" />
            </tr:commandLink>
        </tr:column>
        <tr:column sortable="false" align="right" headerText="#{mensajesCat['PRECIO']}">
         <tr:outputText value="#{var.precio}" />
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesPed['CANTIDAD']}" align="right" >
         <tr:outputText value="#{var.cantidad}" />
        </tr:column>
        <tr:column sortable="false" headerText="#{mensajesPed['PROCEDIMIENTO_EXPEDIENTE']}" >
         <tr:outputText value="#{var.procedimientoExpediente}" />
        </tr:column>
<!--        <tr:column sortable="false" headerText="#{mensajesPed['EXPEDIENTE']}" >-->
<!--         <tr:outputText value="#{var.expediente}" />-->
<!--        </tr:column>-->
<!-- <tr:column sortable="false" headerText="PO" id="po">-->
<!-- <tr:outputText value="#{var.prioridadOferta}" id="outputText14" />-->
<!-- </tr:column>-->
<!-- <tr:column sortable="false" headerText="PIL" id="pil">-->
<!-- <tr:outputText value="#{var.prioridadInfLogistica}" id="outputText15" />-->
<!-- </tr:column>-->
<!-- <tr:column sortable="false" headerText="PT" id="pt">-->
<!-- <tr:outputText value="#{var.prioridadTarifa}" id="outputText16" />-->
<!-- </tr:column>-->
<!-- <tr:column sortable="false" headerText="idOfer" id="idO">-->
<!-- <tr:outputText value="#{var.idOferta}" id="outputText17" />-->
<!-- </tr:column>-->
<!-- <tr:column sortable="false" headerText="idInfL" id="idi">-->
<!-- <tr:outputText value="#{var.idInfLogistica}" id="outputText18" />-->
<!-- </tr:column>-->
<!-- <tr:column sortable="false" headerText="idTar" id="idt">-->
<!-- <tr:outputText value="#{var.idTarifa}" id="outputText19" />-->
<!-- </tr:column>-->
 
       </tr:table>
      </tr:panelBox>
     </tr:panelPage>
 
    </tr:form>
 
   </trh:body>
   </trh:html>
  </f:view>
</jsp:root>