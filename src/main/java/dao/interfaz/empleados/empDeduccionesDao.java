/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.empleados;

import java.util.List;
import modelo.EmpMovimiento;
import modelo.TtipoMovimiento;

/**
 *
 * @author AllanRamiro
 */
public interface empDeduccionesDao {
  
    public List<EmpMovimiento> lstDeduciones(int id); 
    public boolean crear(EmpMovimiento obj);
    public boolean edita(EmpMovimiento obj);
    public boolean borrar(int id);
    public boolean snExistDeduccion(int id_empleado, int id_deduccion);
    
}
