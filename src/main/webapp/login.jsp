<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE composition PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:p="http://primefaces.org/ui"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
    <h:head>
        <title>Acceso</title>
        <link href="resources/css/login.css" type="text/css" rel="stylesheet"/>
    </h:head>

    <h:body>

           <div class="conteainer">

               <h:form id="formlogin" class="login-block">            
      
                <p:growl id="mensaje" sticky="true" showDetail="true" life="1000" />

                <p:panel header="Ingrese su usuario y clave" widgetVar="dlg">
                    <h:panelGrid columns="2" cellpadding="5" style="width: 90%">
                        <h:outputLabel for="username" value="Usuario:"/>
                        <p:inputText id="username" value="#{loginBean.user.usuario}" 
                                     placeholder="Ingrese su usuario" required="true" label="Usuario:" />

                        <h:outputLabel for="password"  value="Clave:"/>
                        <p:password id="password" placeholder="Ingrese su contrasenia" 
                                    value="#{loginBean.user.clave}" required="true" label="Clave:" />

                        <f:facet name="footer">
                            <p:commandButton style="margin-left: 75px; width: 50%"
                                             value="Aceptar" update="formlogin" 
                                             actionListener="#{loginBean.login(event)}"
                                             icon="icon-login" oncomplete="ValidaRespuesta(xhr, status, args)"/>
                        </f:facet>  
                    </h:panelGrid>

                </p:panel>

            </h:form>

        </div>

        <script type="text/javascript">
            function ValidaRespuesta(xhr, status, args) {

                if (args.validationFailed || !args.loggedIn) {
                    jQuery('#formlogin').effect("shake", {times: 5}, 100);
                } else {
                    location.href = args.PathHomePages;
                }
            }
        </script>

    </h:body>


</html>
