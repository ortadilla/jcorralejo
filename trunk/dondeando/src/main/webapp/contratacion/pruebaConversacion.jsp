<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core">

  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <html>
      <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
      </head>
      <body><h:form>
          <p> 
            <h:outputText value="Prueba  conversaciones"/>
          </p>
          <p>
            <h:commandButton value="Inicia la conversacion"
                             actionListener="#{pruebaConversacion.iniciaConversacion}"/><h:commandButton value="Continua la conversacion"
                                                                                                         actionListener="#{pruebaConversacion.continuaConversacion}"/><h:commandButton value="Fin de la conversacion"
                                                                                                                                                                                       actionListener="#{pruebaConversacion.finalizaConversacion}"/>
          </p>
          <h:messages></h:messages>
        </h:form>
        
        </body>
    </html>
  </f:view>
</jsp:root>