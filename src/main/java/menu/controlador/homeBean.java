/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.controlador;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import utilerias.funciones.funciones;
import static utilerias.funciones.funciones.PathInicio;

/**
 *
 * @author root
 */
@Named(value = "homeBean")
@ApplicationScoped
public class homeBean implements java.io.Serializable {

    
     FacesContext facesContext = FacesContext.getCurrentInstance();
    ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
    String path =  servletContext.getRealPath(PathInicio()); 
    String contexto = servletContext.getRealPath(""); 
    String path2 = this.getClass().getClassLoader().getResource("").getPath();
    
    /**
     * Creates a new instance of homeBean
     */
    public homeBean() {
         String path2 = this.getClass().getClassLoader().getResource("").getPath();
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
    
 
}
