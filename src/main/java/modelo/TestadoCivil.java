/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author AllanRamiro
 */

public class TestadoCivil implements java.io.Serializable {

    private Integer id;
    private String descripcion;
    private String abreviatura;
 
    public TestadoCivil() {
     
        this.id = 0;
        // AQUI NO DEBE INICIALIZARSE NADA
        /*
        this.id = 0;
        this.abreviatura = "S";
        */
    }


    public TestadoCivil(String descripcion,String abreviatura) {
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
