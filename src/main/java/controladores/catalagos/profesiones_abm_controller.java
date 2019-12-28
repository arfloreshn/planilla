/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.catalagos;

import dao.interfaz.catalagos.profesionDao;
import dao.implementar.catalagos.profesionDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import modelo.Tprofesion;

/**
 *
 * @author root
 */
@Named(value = "profesionesBean")
@ViewScoped
public class profesiones_abm_controller implements java.io.Serializable {

    /**
     * Creates a new instance of profesionesBean
     */
    
    private Tprofesion pro;
    private List<Tprofesion> lstprofesiones;
    
    
    public profesiones_abm_controller() {
        this.pro = new Tprofesion();
    }
    
    public void instanciar() {
        this.pro = new Tprofesion();
    }

    public Tprofesion getPro() {
        return pro;
    }

    public void setPro(Tprofesion pro) {
        this.pro = pro;
    }

    public List<Tprofesion> lstprofesiones() {
        
        profesionDao pdao = new profesionDaoImpl();
        this.lstprofesiones = pdao.lstprofesion();
        
        return lstprofesiones;
    }
    
    
        public void btncrearCargo(ActionEvent actionevent) {
        String msg = "";
        profesionDao uDao = new profesionDaoImpl();
        if (uDao.crearProfesion(this.pro)) {
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.pro = new Tprofesion();

    }

    public void btneditarCargo(ActionEvent actionevent) {
        String msg = "";
        /*   Date hoy = new Date();
      String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
      this.rol.setFechacreacion(java.sql.Date.valueOf(fecha));
      this.rol.setId(this.rol.getId());*/

        profesionDao uDao = new profesionDaoImpl();

        if (uDao.editarProfesion(this.pro)) {
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
        profesionDao uDao = new profesionDaoImpl();
        if (uDao.borrarProfesiono(this.pro.getId())) {
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
