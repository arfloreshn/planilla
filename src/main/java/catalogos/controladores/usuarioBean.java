/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tm_usuariolate file, choose Tools | Tm_usuariolates
 * and open the tm_usuariolate in the editor.
 */
package catalogos.controladores;

import catalogos.dao.Interfaces.usuarioDao;
import catalogos.dao.Implementar.usuarioDaoImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import modelo.Rol;
import modelo.Usuario;
/**
 *
 * @author root
 */
@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean implements java.io.Serializable {

    private Usuario usuario;
    private Rol rol;
    private List<Usuario>  lstUsuarios;
    private List<SelectItem> listaRoles;
    
    public usuarioBean() 
    {
     this.lstUsuarios = new ArrayList<Usuario>();
     this.usuario = new Usuario();
    
    }

    //Listamos todos los usuarios
    public List<Usuario> listarUsuarios() {
        
        usuarioDao udao = new usuarioDaoImpl();
        this.lstUsuarios = udao.Listar();
        return lstUsuarios;
    }
    
    //Lista todos los Roles del usuario

    public List<SelectItem> getListaRoles() {
        
        this.listaRoles = new ArrayList<SelectItem>();
        usuarioDao udao = new usuarioDaoImpl();
        List<Rol> r = udao.ListarRoles();
        
        listaRoles.clear();
        
        for (Rol rol:r)
        {
        SelectItem itemRol = new SelectItem(rol.getId(),rol.getNombre());
        this.listaRoles.add(itemRol);
        }
        return listaRoles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
   
    /* 
       Inicializa el constructor maestro del metodo modelo, que representa al usuario
       sin esta mendiga inicializacion no guarda ningun registro y se llama en el boton de nuevo
    */
    public void Instanciar()
    {
    this.usuario = new Usuario();
    }
  
    public void btncrearUsuario(ActionEvent actionevent)
    {
      String msg = "";
      usuarioDao uDao = new usuarioDaoImpl();
      if (uDao.crearUsuario(this.usuario)) 
      {
       msg = "Registro creado";
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,null);
       FacesContext.getCurrentInstance().addMessage(msg, message);

      }
      else
      {
       msg = "Fallo en registro";
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null);
       FacesContext.getCurrentInstance().addMessage(msg, message);
    };
      this.usuario = new Usuario();

    }
    
    public void btneditarUsuario(ActionEvent actionevent)
    {
      String msg = "";
   /*   Date hoy = new Date();
      String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
      this.usuario.setFechacreacion(java.sql.Date.valueOf(fecha));
      this.usuario.setId(this.usuario.getId());*/
 
      usuarioDao uDao = new usuarioDaoImpl();
      
      
      
      if (uDao.editarUsuario(this.usuario)) 
      {
       msg = "Datos actualizados";
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,null);
       FacesContext.getCurrentInstance().addMessage(msg, message);

      }
      else
      {
       msg = "Fallo en actualizacion";
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null);
       FacesContext.getCurrentInstance().addMessage(msg, message);
      };
    }

    public void btnborrarUsuario(ActionEvent actionevent)
    {
      String msg = "";
      usuarioDao uDao = new usuarioDaoImpl();
      if (uDao.borrarUsuario(this.usuario.getId())) 
      {
       msg = "Datos eliminado";
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,null);
       FacesContext.getCurrentInstance().addMessage(msg, message);
      
      }
      else
      {
       msg = "Fallo de eliminacion";
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null);
       FacesContext.getCurrentInstance().addMessage(msg, message);
      
      };
    }
 
    
  }
