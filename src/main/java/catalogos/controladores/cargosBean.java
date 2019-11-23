/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.controladores;

import catalogos.dao.Interfaces.cargosDao;
import catalogos.dao.Implementar.cargosDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Tcargo;

/**
 *
 * @author root
 */
@Named(value = "cargosBean")
@RequestScoped
public class cargosBean implements java.io.Serializable{

    /**
     * Creates a new instance of cargosBean
     */
    private Tcargo cargo;
    private List<Tcargo> lstCargos;
    
    
    public cargosBean() {
        this.cargo = new Tcargo();
    }
    
    public void instanciar()
    {
     this.cargo = new Tcargo();
    }

    public Tcargo getCargo() {
        return cargo;
    }

    public void setCargo(Tcargo cargo) {
        this.cargo = cargo;
    }

    public List<Tcargo> lstCargos() {
        
        cargosDao cDao = new cargosDaoImpl();
        this.lstCargos = cDao.lstcargos();
        return lstCargos;
    }
    

    
    public void btncrearCargo(ActionEvent actionevent) {
        String msg = "";
        cargosDao uDao = new cargosDaoImpl();
        if (uDao.crearCargo(this.cargo)) {
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.cargo = new Tcargo();

    }

    public void btneditarCargo(ActionEvent actionevent) {
        String msg = "";
        /*   Date hoy = new Date();
      String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
      this.rol.setFechacreacion(java.sql.Date.valueOf(fecha));
      this.rol.setId(this.rol.getId());*/

        cargosDao uDao = new cargosDaoImpl();

        if (uDao.editarCargo(this.cargo)) {
            msg = "Datos actualizados";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en actualizacion";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
    }

    public void btnborrarCargo(ActionEvent actionevent) {
        String msg = "";
        cargosDao uDao = new cargosDaoImpl();
        if (uDao.borrarCargo(this.cargo.getId())) {
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
