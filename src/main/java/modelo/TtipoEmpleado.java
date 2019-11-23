/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author AllanRamiro
 */
public class TtipoEmpleado implements java.io.Serializable {

    
    private int id;
    private String descripcion;
    private String abreviatura;
    
    public TtipoEmpleado() {
      
        this.id = 0;
        this.descripcion = "";
        this.abreviatura = "";
        //  AQUI NO DEBE DE INICIALIZARSE NADA
        /*
        this.id = 0;
        this.descripcion = "";
        this.abreviatura = "";
        */
    }

    
       public TtipoEmpleado(String descripcion, String abreviatura) {
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }
    
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    
    
}
