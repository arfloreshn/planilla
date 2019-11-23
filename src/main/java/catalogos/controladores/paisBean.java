/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.controladores;

import catalogos.dao.Interfaces.paisesDao;
import catalogos.dao.Implementar.paisesDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Tpais;

/**
 *
 * @author root
 */
@Named(value = "paisBean")
@RequestScoped
public class paisBean implements java.io.Serializable {

    /**
     * Creates a new instance of paisBean
     */
    private Tpais pais;
    private List<Tpais> lstpais;
    
    
    public paisBean() {
        this.pais = new Tpais();
    }
    
    public void instanciar()
    {
      this.pais = new Tpais();
    }

    public Tpais getPais() {
        return pais;
    }

    public void setPais(Tpais pais) {
        this.pais = pais;
    }

    public List<Tpais> lstpais() {
        
        paisesDao pdao = new paisesDaoImpl();
        this.lstpais = pdao.lstPaises();
        return lstpais;
    }
    
    
        public void btncrearCargo(ActionEvent actionevent) {
        String msg = "";
        paisesDao uDao = new paisesDaoImpl();
        if (uDao.crearPais(this.pais)) {
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.pais = new Tpais();

    }

    public void btneditarCargo(ActionEvent actionevent) {
        String msg = "";
        /*   Date hoy = new Date();
      String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
      this.rol.setFechacreacion(java.sql.Date.valueOf(fecha));
      this.rol.setId(this.rol.getId());*/

        paisesDao uDao = new paisesDaoImpl();

        if (uDao.editarPais(this.pais)) {
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
        paisesDao uDao = new paisesDaoImpl();
        if (uDao.borrarPais(this.pais.getId())) {
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
