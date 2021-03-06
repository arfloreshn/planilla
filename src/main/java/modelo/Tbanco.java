package modelo;
// Generated 09-02-2019 10:41:42 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Tbanco generated by hbm2java
 */
public class Tbanco  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private Set tbancoCtases = new HashSet(0);
     private Set empCtaBcos = new HashSet(0);

    public Tbanco() {
        this.id = 0;
        this.descripcion = "";
    }

    public Tbanco(String descripcion, Set tbancoCtases, Set empCtaBcos) {
       this.descripcion = descripcion;
       this.tbancoCtases = tbancoCtases;
       this.empCtaBcos = empCtaBcos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getTbancoCtases() {
        return this.tbancoCtases;
    }
    
    public void setTbancoCtases(Set tbancoCtases) {
        this.tbancoCtases = tbancoCtases;
    }
    public Set getEmpCtaBcos() {
        return this.empCtaBcos;
    }
    
    public void setEmpCtaBcos(Set empCtaBcos) {
        this.empCtaBcos = empCtaBcos;
    }




}


