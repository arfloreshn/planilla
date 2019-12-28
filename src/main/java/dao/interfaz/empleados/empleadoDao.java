/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.empleados;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import modelo.TdeptoPais;
import modelo.EmpMae;
import modelo.Tmunicipios;
import modelo.Tpais;
import modelo.Tprofesion;
import org.hibernate.Session;

/**
 *
 * @author AllanRamiro
 */
public interface empleadoDao {
   public List<EmpMae> lstEmpleado();
   public boolean crearEmpleado(EmpMae obj);
   public boolean editarEmpleado(EmpMae obj);
   public boolean bajaEmpleado(EmpMae obj);
   public List<Tpais> lstPais();
   public List<TdeptoPais> listarDeptos(EmpMae obj);
   public List<Tmunicipios> listarMunicipios(EmpMae obj);
   public List<Tprofesion> lstProfesion();
   public EmpMae buscarEmpleadoXid(String codEmpleado);
   public int getNroEmpleado() throws Exception;
   public EmpMae getFoto(String codEmpleado);
   public List<String> getAniversariosDelMes(int mes); 
   public List<EmpMae> getAniversariosXNombres(String nombre);
   public List<EmpMae> getEmpleadoIngresados(int anio, int mes);
}
