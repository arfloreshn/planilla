/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.EmpMae;
import modelo.TtipoDocumento;

/**
 *
 * @author AllanRamiro
 */
public interface ttipoDocEmpDao {
    
    public List<TtipoDocumento> lstTipoDoc();
    public List<TtipoDocumento> listTipoDocXEmpleado(EmpMae obj);
    
}
