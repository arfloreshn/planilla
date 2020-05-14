package controladores.catalagos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.interfaz.catalagos.deptoPaisDao;
import dao.implementar.catalagos.deptoPaisDaoImpl;
import dao.implementar.catalagos.paisesDaoImpl;
import dao.interfaz.catalagos.paisesDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.TdeptoPais;
import modelo.Tpais;
import utilerias.funciones.msgbox;

/**
 *
 * @author root
 */
@Named(value = "deptoPaisBean")
@ViewScoped
public class deptoPais_abm_controller implements java.io.Serializable {

    /**
     * Creates a new instance of deptoPaisBean
     */
    private static final long serialVersionUID = 1L;
  //  private List<Paises> lstPais = new ArrayList<Paises>();
  
    private List<SelectItem> seleccionarPais;
   
    private List<TdeptoPais> ListaDeptos;

    private TdeptoPais depto;
    private Tpais paises ;
   
    private String item_pais;
    private String item_depto;
   
    public deptoPais_abm_controller() 
    {
           this.paises = new Tpais();
           this.depto = new TdeptoPais(this.paises,"");
      }
 
    
    @PostConstruct
    public void init() {
      
          this.item_pais = "";

          this.seleccionarPais = new ArrayList<>();
    
            paisesDao pdao = new paisesDaoImpl();
            List<Tpais> r =  pdao.lstPaises();
            this.seleccionarPais.clear();

            for (Tpais obj : r) {
                SelectItem item = new SelectItem(obj.getId(), obj.getNomPais());
                this.seleccionarPais.add(item);
            }

        //  getListarPais();
    }
    
    public void instanciar() {
        this.depto = new TdeptoPais();

    }

    public Tpais getPaises() {
        return paises;
    }

    public void setPaises(Tpais paises) {
        this.paises = paises;
    }

    public String getItem_pais() {
        return item_pais;
    }

    public void setItem_pais(String item_pais) {
        this.item_pais = item_pais;
    }

    public String getItem_depto() {
        return item_depto;
    }

    public void setItem_depto(String item_depto) {
        this.item_depto = item_depto;
    }

    
    

    public TdeptoPais getDepto() {
        return depto;
    }

    public void setDepto(TdeptoPais depto) {
        this.depto = depto;
    }

    
    
     /*
     DEVUELVE EL CBO DEL PAISES
     */
    public List<SelectItem> getListarPais() {
        return seleccionarPais;
    }


    
      public void oncboPais(ValueChangeEvent e) {
     
          this.ListaDeptos = new ArrayList<TdeptoPais>();
        
            if (e.getNewValue() != this.item_pais) {
                this.item_pais =  e.getNewValue().toString();
                 deptoPaisDao pdao = new deptoPaisDaoImpl();
                 this.ListaDeptos = pdao.lstdepto(this.item_pais);
                 getListaDeptos();
            }
         
         
}
 
    private void listartodo() 
    {
           this.ListaDeptos = new ArrayList<TdeptoPais>();
           deptoPaisDao pdao = new deptoPaisDaoImpl();
           this.ListaDeptos = pdao.lstdepto(this.item_pais);
           getListaDeptos();
       
    }  
          
    public List<TdeptoPais> getListaDeptos() {
        return ListaDeptos;
    }

    
    public void btncrearDepto() {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();

         int id =  Integer.valueOf (this.item_pais);
         this.paises.setId(id);
         this.depto.setTpais(this.paises);
         
        if (uDao.crearDepto(this.depto)) {
            msg = "Registro creado";
            msgbox.vbInfo(msg);
            listartodo();
            
        } else {
            msg = "Fallo en registro";
            msgbox.vbError(msg);
        };
        
        this.depto = new TdeptoPais();
    }

    public void btneditarDepto() {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();
        
         int id =  Integer.valueOf (this.item_pais);
         this.paises.setId(id);
         this.depto.setTpais(this.paises);
        
        if (uDao.editarDepto(this.depto)) {
            msg = "Registro Modificado";
            msgbox.vbInfo(msg);
        } else {
            msg = "Fallo en registro";
           msgbox.vbError(msg);
        };
       
        this.depto = new TdeptoPais();
        
    }

    
   public void btnBorrarDepto() {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();
     
        int id_pais =  Integer.valueOf (this.item_pais);
        this.paises.setId(id_pais);
        this.depto.setTpais(this.paises);
             
        if (uDao.borrarDepto(id_pais, this.depto.getId())) {
            msg = "Registro Borrado";
            msgbox.vbInfo(msg);
            listartodo();
          
        } else {
            msg = "Fallo en registro";
            msgbox.vbFatal(msg);
  
        };
        this.depto = new TdeptoPais();
    
    }

}
