/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.validarAccesos;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import static utilerias.funciones.funciones.PathApp;
import static utilerias.funciones.funciones.PathInicio;

/**
 *
 * @author root
 */
@Named(value = "validaAccesoUrl")
@RequestScoped
public class validaAccesoUrl implements PhaseListener {

    /**
     * Creates a new instance of escuchaListener
     */
    
    private FacesContext contex = FacesContext.getCurrentInstance();
        
    public validaAccesoUrl() {
    }

    @Override
    public void afterPhase(PhaseEvent event) {
       
        String page403 = PathInicio() + PathApp() +"errorPages/page403.html";
       
        FacesContext context = event.getFacesContext();
       // Capturo la pagina actual
       String paginaActual = context.getViewRoot().getViewId();
       
       //boolean snLoginFrm = paginaActual.lastIndexOf("login.xhtml") > -1;
       
       boolean snLoginFrm = paginaActual.endsWith("login.xhtml");
       HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
       
       //El valor autenticado viene del loginControler
       Object snAutenticado = session.getAttribute("snLogueado");
       
       // Si es verdadero va entrar a validarpasado por el login
       if (!snLoginFrm && snAutenticado == null  ) 
       {
            NavigationHandler nHandler = context.getApplication().getNavigationHandler();
                nHandler.handleNavigation(context, null, page403);
           
       }
       
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
