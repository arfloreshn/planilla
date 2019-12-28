/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.catalagos;

import java.util.List;
import modelo.TtipoMovimiento;

/**
 *
 * @author AllanRamiro
 */
public interface ttipoMovimientoDao {
    public List<TtipoMovimiento> lstTipoMovimiento();
    public List<TtipoMovimiento> lstMovimientodeIngresos(int id);
    public List<TtipoMovimiento> lstTipoMovimintoXCodigoEmpleado(int id);
    public List<TtipoMovimiento> lstDeducionesPorEmpleado(int id);
    public List<TtipoMovimiento> lstIngresosPorEmpleado(int id);
}
