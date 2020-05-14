/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.procesos.login;


import dao.interfaz.catalagos.usuarioDao;
import dao.implementar.catalagos.usuarioDaoImpl;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.PrimeRequestContext;
import modelo.Usuario;
import static utilerias.funciones.funciones.PathApp;
import static utilerias.funciones.funciones.PathInicio;


/**
 *
 * @author root
 */
@Named(value = "loginBean")
@SessionScoped
public class login_controller implements Serializable 
{
    private static final long serialVersionUID = -2152389656664659476L;
    
    private Usuario user;
    
      /**
     * Creates a new instance of loginBean
     */
    public login_controller() {
 
          this.user  = new Usuario();
    }

    
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
 
    
   public void login(ActionEvent event) 
    {
        
//        RequestContext context = RequestContext.getCurrentInstance();
        
        
      PrimeRequestContext context = PrimeRequestContext.getCurrentInstance();
        
        FacesMessage message = null;
        //boolean loggedIn = false;
        
        String path = "";
 
        usuarioDao usuarioDao = new usuarioDaoImpl();
    
        this.user = usuarioDao.validarlogin(this.user);
        
        if(this.user != null) 
        {
            //loggedIn = true;
            //Cargo la variable de session de usuario, que es la sera validada a lo largo de toda la aplicacion.
            
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getSessionMap().put("usuario", this.user.getUsuario());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.user.getUsuario());
           
           
            path = PathInicio() + PathApp() + "Bienvenido.xhtml";
        } 
        else {
            //loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "fallo de autenticacion", "autenticacion invalida");
             this.user = new Usuario();
        }
         
       FacesContext.getCurrentInstance().addMessage(null, message);
       //context.addCallbackParam("loggedIn", loggedIn);
       //context.addCallbackParam("PathHomePages", path);
       
       context.getCallbackParams().put("LoginOn", this.user.getUsuario());
       context.getCallbackParams().put("PathHomePages", path);
    } 


     public  String CerrarSession() 
    {
       
       this.user  = null;
       FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       return "/faces/login.xhtml";
    }
}

