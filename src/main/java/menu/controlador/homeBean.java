/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.controlador;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import utilerias.funciones.funciones;

/**
 *
 * @author root
 */
@Named(value = "homeBean")
@ApplicationScoped
public class homeBean implements java.io.Serializable {

    /**
     * Creates a new instance of homeBean
     */
    public homeBean() {
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
