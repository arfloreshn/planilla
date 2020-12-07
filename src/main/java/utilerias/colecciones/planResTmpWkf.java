/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.colecciones;

import modelo.EmpMae;

/**
 *
 * @author AllanRamiro
 */
public class planResTmpWkf {
    
     private boolean sn_seleccionado;
     //private EmpMae empMae;
     private int periodo;
     private int anio;
     private int id_emp;
     private String codEmp;
     private String nombre;
     private double impSueldoBruto;
     private double impDebitos;
     private double impCreditos;
     private double impPagoNeto;

    public planResTmpWkf()
    {
       // this.empMae = new EmpMae();
    } 

    public boolean isSn_seleccionado() {
        return sn_seleccionado;
    }

    public void setSn_seleccionado(boolean sn_seleccionado) {
        this.sn_seleccionado = sn_seleccionado;
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

    public double getImpSueldoBruto() {
        return impSueldoBruto;
    }

    public void setImpSueldoBruto(double impSueldoBruto) {
        this.impSueldoBruto = impSueldoBruto;
    }

    public double getImpDebitos() {
        return impDebitos;
    }

    public void setImpDebitos(double impDebitos) {
        this.impDebitos = impDebitos;
    }

    public double getImpCreditos() {
        return impCreditos;
    }

    public void setImpCreditos(double impCreditos) {
        this.impCreditos = impCreditos;
    }

    public double getImpPagoNeto() {
        return impPagoNeto;
    }

    public void setImpPagoNeto(double impPagoNeto) {
        this.impPagoNeto = impPagoNeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public String  getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }

  
    
    
     
}
