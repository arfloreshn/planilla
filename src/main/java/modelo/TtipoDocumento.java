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
public class TtipoDocumento implements java.io.Serializable {

    private int id;
    private String descripcion;
    private String abreviatura;
    
    public TtipoDocumento() {
       
        this.id = 0;
        this.abreviatura = "";
        this.descripcion = "";
    }

    public TtipoDocumento(String descripcion,String abreviatura) {
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
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
