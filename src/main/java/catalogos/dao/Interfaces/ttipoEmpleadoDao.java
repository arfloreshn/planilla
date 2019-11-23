/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.EmpMae;
import modelo.TtipoEmpleado;

/**
 *
 * @author AllanRamiro
 */
public interface ttipoEmpleadoDao {
    
    public List<TtipoEmpleado> lstTipoEmpleado();
    public List<TtipoEmpleado> lstTipoEmpleadoXCodigo(EmpMae obj);
    
}
