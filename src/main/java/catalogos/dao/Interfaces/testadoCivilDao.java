/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.EmpMae;
import modelo.TestadoCivil;

/**
 *
 * @author AllanRamiro
 */
public interface testadoCivilDao {
    public List<TestadoCivil> lstEstadoCivil();
    public List<TestadoCivil> lstEstadoCivilXEmpleado(EmpMae obj);
}
