/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.controladores;

import catalogos.dao.Implementar.deptoEmpDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.TdeptoEmp;
import catalogos.dao.Interfaces.deptoEmpDao;

/**
 *
 * @author root
 */
@Named(value = "deptoempBean")
@RequestScoped
public class deptoEmpBean implements java.io.Serializable {

    /**
     * Creates a new instance of deptoEmpBean
     */
    private TdeptoEmp depto;
    private List<TdeptoEmp> lstdeptos;
    
    public deptoEmpBean() {
     this.depto = new TdeptoEmp();
    }

    public void instanciar() 
    {
        this.depto = new TdeptoEmp();
    }

    public TdeptoEmp getDepto() {
        return depto;
    }

    public void setDepto(TdeptoEmp depto) {
        this.depto = depto;
    }

    public List<TdeptoEmp> lstdeptos() {
        deptoEmpDao edao = new deptoEmpDaoImpl();
        this.lstdeptos = edao.lstdep();
        return lstdeptos;
    }
    
    
      public void btncrearCargo(ActionEvent actionevent) {
        String msg = "";
        deptoEmpDao uDao = new deptoEmpDaoImpl();
        if (uDao.crearDepto(this.depto)) {
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.depto = new TdeptoEmp();

    }

    public void btneditarCargo(ActionEvent actionevent) {
        String msg = "";
        /*   Date hoy = new Date();
      String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
      this.rol.setFechacreacion(java.sql.Date.valueOf(fecha));
      this.rol.setId(this.rol.getId());*/

        deptoEmpDao uDao = new deptoEmpDaoImpl();

        if (uDao.editarDepto(this.depto)) {
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
        deptoEmpDao uDao = new deptoEmpDaoImpl();
        if (uDao.borrarDepto(this.depto.getId())) {
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
