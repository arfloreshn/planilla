/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.catalagos;

import dao.interfaz.catalagos.rolDao;
import dao.implementar.catalagos.rolDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import modelo.Rol;
import modelo.TestadosApp;

/**
 *
 * @author root
 */
@Named(value = "rolBean")
@RequestScoped
public class rol_abm_controller implements java.io.Serializable {

    /**
     * Creates a new instance of rolBean
     */
    private Rol rol;
    private List<Rol> lstrol;
    private List<SelectItem> lstEstados;
    

    public rol_abm_controller() {
        this.rol = new Rol();
        
    }

    
   
     public List<Rol> lstroles() {
        rolDao rdao = new rolDaoImpl();
        this.lstrol = rdao.lstrol();
        return lstrol;
    }

  
    public Rol getRol() {
        return rol;
    }

   
    public void setRol(Rol rol) {
        this.rol = rol;
    }

        /* 
       Inicializa el constructor maestro del metodo modelo, que representa al usuario
       sin esta mendiga inicializacion no guarda ningun registro y se llama en el boton de nuevo
    */
    public void Instanciar()
    {
    this.rol = new Rol();
    }

    
     public List<SelectItem> getListaEstados() {
        
        this.lstEstados = new ArrayList<SelectItem>();
        rolDao rdao = new rolDaoImpl();
        List<TestadosApp> r = rdao.lstEstado();
        
        lstEstados.clear();
        
        for (TestadosApp obj:r)
        {
        SelectItem itemEstado = new SelectItem(obj.getId(),obj.getDesc());
        this.lstEstados.add(itemEstado);
        }
        return lstEstados;
    }
    
    public void btncrearRol(ActionEvent actionevent) {
        String msg = "";
        rolDao uDao = new rolDaoImpl();
        if (uDao.crearRol(this.rol)) {
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.rol = new Rol();

    }

    public void btneditarRol(ActionEvent actionevent) {
        String msg = "";
        /*   Date hoy = new Date();
      String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
      this.rol.setFechacreacion(java.sql.Date.valueOf(fecha));
      this.rol.setId(this.rol.getId());*/

        rolDao uDao = new rolDaoImpl();

        if (uDao.editarRol(this.rol)) {
            msg = "Datos actualizados";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en actualizacion";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
    }

    public void btnborrarRol(ActionEvent actionevent) {
        String msg = "";
        rolDao uDao = new rolDaoImpl();
        if (uDao.borrarRol(this.rol.getId())) {
            msg = "Datos eliminado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo de eliminacion";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        };
    }

}
