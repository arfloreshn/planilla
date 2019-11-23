/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecciones;

/**
 *
 * @author AllanRamiro
 */
public class planResAutorizacion {
    
     private int periodo;
     private int anio;
     private int nro_planilla;
     private boolean sn_autorizado;
     
     public planResAutorizacion() 
     {
     }

      public planResAutorizacion(int periodo, int anio, int nro_planilla, boolean sn_autorizado) 
     {
         this.periodo=periodo;
         this.anio=anio;
         this.nro_planilla=nro_planilla;
         this.sn_autorizado=sn_autorizado;
     }

     
    public int getPeriodo() {
        return periodo;
    }

    
   
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getNro_planilla() {
        return nro_planilla;
    }

    public void setNro_planilla(int nro_planilla) {
        this.nro_planilla = nro_planilla;
    }

    public boolean isSn_autorizado() {
        return sn_autorizado;
    }

    public void setSn_autorizado(boolean sn_autorizado) {
        this.sn_autorizado = sn_autorizado;
    }
     
     
     
 
}
