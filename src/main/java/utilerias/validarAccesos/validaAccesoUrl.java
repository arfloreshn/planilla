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
    public validaAccesoUrl() {
    }

    @Override
    public void afterPhase(PhaseEvent pe) {
       
        String page403 = PathInicio() + PathApp() +"errorPages/page403.html";
       
        FacesContext fc = pe.getFacesContext();
       // Capturo la pagina actual
       String paginaActiva = fc.getViewRoot().getViewId();
       
       boolean snLoginFrm = paginaActiva.lastIndexOf("login.xhtml") > -1;
       
       HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
       
       Object usuario = session.getAttribute("usuario");
       
       if (!snLoginFrm && usuario == null) 
       {
          NavigationHandler nHandler = fc.getApplication().getNavigationHandler();
          nHandler.handleNavigation(fc, null, page403);
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