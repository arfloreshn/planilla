package controladores.procesos.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import utilerias.funciones.funciones;
import static utilerias.funciones.funciones.PathApp;
import static utilerias.funciones.funciones.PathInicio;

/**
 *
 * @author root
 */
@Named(value = "homeBean")
@ApplicationScoped
public class home_controller implements java.io.Serializable {

    
    FacesContext contex = FacesContext.getCurrentInstance();
    
    /**
     * Creates a new instance of homeBean
     */
    public home_controller() {
         //verificarSesion();
         getHomeUrl();
    }
    
    public final String getHomeUrl()
    {
     return funciones.PathInicio();
    }
    
    
    public String getForms()
    {
        String path = funciones.PathApp();
        return path;
    }
   
    
    public void verificarSesion()
    {
      try
      {
       this.contex =  FacesContext.getCurrentInstance();
       Usuario us = (Usuario) this.contex.getExternalContext().getSessionMap().get("usuario");
      
      if(us==null)
      {
        String path = PathInicio() + PathApp() +"errorPages/page403.html";
        this.contex.getExternalContext().redirect(path);
        this.contex.renderResponse();
      }
      
      } 
      catch(Exception e)
      {
      
      }
      
    }
    

     
    public String mostrarusuario() 
    {
         String usuario = (String) this.contex.getExternalContext().getSessionMap().get("usuario").toString();
         return usuario;
    }
    
   
    public void cerrarSesion()
    {
      this.contex.getExternalContext().invalidateSession();
    }
    
 
}
