/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.catalagos;

import dao.implementar.catalagos.deptoPaisDaoImpl;
import dao.implementar.catalagos.municipioPaisDaoImpl;
import dao.implementar.catalagos.paisesDaoImpl;
import dao.interfaz.catalagos.deptoPaisDao;
import dao.interfaz.catalagos.municipioPaisDao;
import dao.interfaz.catalagos.paisesDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import modelo.TdeptoPais;
import modelo.Tmunicipios;
import modelo.Tpais;
import utilerias.funciones.msgbox;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "municipiosBean")
@ApplicationScoped
public class municipios_abm_controller implements java.io.Serializable {

    
    private String id_pais;
    private String id_depto;

    private TdeptoPais depto;
    private Tpais paises ;
    private Tmunicipios municipios;
  
    private List<SelectItem> seleccionarPais;
    private List<SelectItem> seleccionarDepto;
  
    private List<Tmunicipios> ListaMunicipios;

    // Inicializamos la clase
    @PostConstruct
    public void inicializar() {
      instanciar_variables();
    }
    
    /**
     * Creates a new instance of municipiosBean
     */
    public municipios_abm_controller() 
    {
        instanciar_variables();
    }
    

    private void instanciar_variables() 
    {
        this.id_pais = "";
        this.id_depto = "";

         this.paises = new Tpais() ;
         this.depto = new TdeptoPais(this.paises,"");
         this.municipios = new Tmunicipios();

        this.seleccionarPais = new ArrayList<>();
        this.seleccionarDepto = new ArrayList<>();
        this.ListaMunicipios = new ArrayList<Tmunicipios>();

        paisesDao pdao = new paisesDaoImpl();
          List<Tpais> r =  pdao.lstPaises();
          this.seleccionarPais.clear();

          for (Tpais obj : r) {
              SelectItem item = new SelectItem(obj.getId(), obj.getNomPais());
              this.seleccionarPais.add(item);
          }


    }
    
  
    public Tpais getPaises() {
        return paises;
    }

    public void setPaises(Tpais paises) {
        this.paises = paises;
    }

    public String getId_pais() {
        return id_pais;
    }

   // Instancia el id del pais 
    public void setId_pais(String id_pais) {
        this.id_pais = id_pais;
    }

   // Instancia el id del departamento 

    public String getId_depto() {
        return id_depto;
    }

    public void setId_depto(String id_depto) {
        this.id_depto = id_depto;
    }

    
    public TdeptoPais getDepto() {
        return depto;
    }

    public void setDepto(TdeptoPais depto) {
        this.depto = depto;
    }

    
    public Tmunicipios getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Tmunicipios municipios) {
        this.municipios = municipios;
    }

   
    
     /*
     DEVUELVE EL COMBO DE PAISES
     */
    public List<SelectItem> getSeleccionarPais() {
        return seleccionarPais;
    }

    
    /*
     DEVUELVE EL COMBO DE DEPARTAMENTOS
     */

    public List<SelectItem> getSeleccionarDepto() {
        return seleccionarDepto;
    }


    
   public void oncboPais(ValueChangeEvent e) {
      
         this.seleccionarDepto = new ArrayList<>();
   
         if (e.getNewValue() != this.id_pais) {

            this.id_pais = e.getNewValue().toString();
            deptoPaisDao ddao = new deptoPaisDaoImpl();
            List<TdeptoPais> r =  ddao.lstdepto(this.id_pais);
            this.seleccionarDepto.clear();

            for (TdeptoPais obj : r) {
                SelectItem item = new SelectItem(obj.getId(), obj.getNomDepto());
                this.seleccionarDepto.add(item);
            }

             getSeleccionarDepto();
            }
         
         
}

      
   public void oncboDepto(ValueChangeEvent e) {
      
         if (e.getNewValue() != "") {
             this.id_depto = e.getNewValue().toString();
             listarMunicipios();
          }
         
}


 
    private void listarMunicipios() 
    {
       this.ListaMunicipios = new ArrayList<Tmunicipios>();
       municipioPaisDao pdao = new  municipioPaisDaoImpl();
       this.ListaMunicipios = pdao.lstmunicipios(this.id_pais,this.id_depto);
       getListaMunicipios();
    }  
          
  
    
    public List<Tmunicipios> getListaMunicipios() {
        return ListaMunicipios;
    }
    
    public void nuevo_registro() {
        this.paises.setId(Integer.valueOf(this.id_pais));
        this.depto = new TdeptoPais(this.paises,this.paises.getNomPais());
        this.municipios = new Tmunicipios(this.depto, Integer.valueOf(this.id_pais),"", "");
    }
   
    
    public void btncrearMunicipio() {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();

         int id =  Integer.valueOf (this.id_pais);
         this.paises.setId(id);
         this.depto.setTpais(this.paises);
         
        if (uDao.crearDepto(this.depto)) {
            msg = "Registro creado";
            msgbox.vbInfo(msg);
            listarMunicipios();
            
        } else {
            msg = "Fallo en registro";
            msgbox.vbError(msg);
        };
        
        this.municipios = new Tmunicipios();

    }

    public void btneditarMunicipio() {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();
        
         int id =  Integer.valueOf (this.id_pais);
         this.paises.setId(id);
         this.depto.setTpais(this.paises);
        
        if (uDao.editarDepto(this.depto)) {
            msg = "Registro Modificado";
            msgbox.vbInfo(msg);
        } else {
            msg = "Fallo en registro";
           msgbox.vbError(msg);
        };
       
        this.municipios = new Tmunicipios();
        
    }

    
   public void btnBorrarMunicipio() {
        String msg = "";
        deptoPaisDao uDao = new deptoPaisDaoImpl();
        this.paises.setId(Integer.valueOf (this.id_pais));
        this.depto.setTpais(this.paises);
             
        if (uDao.borrarDepto(Integer.valueOf (this.id_pais), this.depto.getId())) {
            msg = "Registro Borrado";
            msgbox.vbInfo(msg);
            listarMunicipios();
          
        } else {
            msg = "Fallo en registro";
            msgbox.vbFatal(msg);
  
        };
        this.municipios = new Tmunicipios();
    
    }

}
