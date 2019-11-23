package catalogos.controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import catalogos.dao.Interfaces.deptoPaisDao;
import catalogos.dao.Implementar.deptoPaisDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import modelo.TdeptoPais;
import modelo.Tpais;

/**
 *
 * @author root
 */
@Named(value = "deptoPaisBean")
@ManagedBean
@SessionScoped
public class deptoPaisBean implements java.io.Serializable {

    /**
     * Creates a new instance of deptoPaisBean
     */
    private static final long serialVersionUID = 1L;
  //  private List<Paises> lstPais = new ArrayList<Paises>();
    private List<TdeptoPais> lstdeptos;
    private TdeptoPais depto;
    private Tpais var_pais;
    private String var_nom_depto;
    

    public deptoPaisBean() 
    {
       this.depto = new TdeptoPais();
 }
    
    public void instanciar() {
        this.var_nom_depto = "";
        this.depto = new TdeptoPais(var_pais,this.var_nom_depto);
    }


    public String getVar_nom_depto() {
        return var_nom_depto;
    }

    public void setVar_nom_depto(String var_nom_depto) {
        this.var_nom_depto = var_nom_depto;
    }

    
    public List<TdeptoPais> lstdeptos() {
        return lstdeptos;
    }

    public void setLstdeptos(List<TdeptoPais> lstdeptos) {
        this.lstdeptos = lstdeptos;
    }

    public TdeptoPais getDepto() {
        return depto;
    }

    public void setDepto(TdeptoPais depto) {
        this.depto = depto;
    }

   

    
    
    public void btncrearDepto(ActionEvent actionevente) {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();

         this.depto = new TdeptoPais(this.var_pais,this.var_nom_depto);
         
        if (uDao.crearDepto(this.depto)) {
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.depto = new TdeptoPais();
    }

    public void btneditarDepto(ActionEvent actionevente) {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();
        if (uDao.editarDepto(this.depto)) {
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.depto = new TdeptoPais();

    }

}
