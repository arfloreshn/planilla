package controladores.procesos.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import utilerias.funciones.funciones;

/**
 *
 * @author root
 */
@Named(value = "homeBean")
@SessionScoped
public class home_controller implements java.io.Serializable {

    private String homePath;

    FacesContext contex = FacesContext.getCurrentInstance();
  
           
    private String var_usuario = ""; 
    /**
     * Creates a new instance of homeBean
     */
    public home_controller() {
       
        getHomeUrl();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.var_usuario = context.getSessionMap().get("usuario").toString();
        
    }

    public final String getHomeUrl() {
        return funciones.PathInicio();
    }

    public String getForms() {
        String path = funciones.PathApp();
        return path;
    }

    public String getVar_usuario() {
        return var_usuario;
    }


    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public String mostrarusuario() {
        String usuario = (String) this.contex.getExternalContext().getSessionMap().get("usuario").toString();
        return usuario;
    }

    public void cerrarSesion() {
        this.contex.getExternalContext().invalidateSession();
    }

}
