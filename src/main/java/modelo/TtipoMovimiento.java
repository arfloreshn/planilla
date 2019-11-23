package modelo;
// Generated 08-13-2019 10:15:53 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TtipoMovimiento generated by hbm2java
 */
public class TtipoMovimiento  implements java.io.Serializable {


     private Integer id;
     private String desc;
     private String tipo;
     private Set movimientos = new HashSet(0);

    public TtipoMovimiento() {
      this.id = 0;
      this.desc = "";
    }

	
    public TtipoMovimiento(String desc, String tipo) {
        this.desc = desc;
        this.tipo = tipo;
    }
    public TtipoMovimiento(String desc, String tipo, Set movimientos) {
       this.desc = desc;
       this.tipo = tipo;
       this.movimientos = movimientos;
    }
   
    public Integer getid() {
        return this.id;
    }
    
    public void setid(Integer id) {
        this.id = id;
    }
    public String getdesc() {
        return this.desc;
    }
    
    public void setdesc(String desc) {
        this.desc = desc;
    }
    public String gettipo() {
        return this.tipo;
    }
    
    public void settipo(String tipo) {
        this.tipo = tipo;
    }
    public Set getMovimientos() {
        return this.movimientos;
    }
    
    public void setMovimientos(Set movimientos) {
        this.movimientos = movimientos;
    }




}


