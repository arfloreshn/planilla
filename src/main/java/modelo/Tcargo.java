package modelo;
// Generated 05-13-2019 07:40:13 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Tcargo generated by hbm2java
 */
public class Tcargo  implements java.io.Serializable {


     private Integer id;
     private String nomCargo;
 //    private Set empleados = new HashSet(0);
 
    public Tcargo() {

        this.id = 0;
        this.nomCargo = "";
        //AQUI NO DEBE DE INICIALIZARSE NADA
     /*
        this.id = 0;
        this.nomCargo = "";
     */
    }

     public Tcargo(String nomCargo) {
        this.nomCargo = nomCargo;
    }

   
    
      /*
    public Tcargo(String nomCargo, Set empleados) {
        this.nomCargo = nomCargo;
       // this.empleados = empleados;
    }*/
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNomCargo() {
        return this.nomCargo;
    }
    
    public void setNomCargo(String nomCargo) {
        this.nomCargo = nomCargo;
    }

    /*
    
    public Set getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }
 */
    

}


