/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.planilla;

import utilerias.colecciones.planResTmpWkf;
import java.util.List;
import modelo.PlanMaeWkf;
import modelo.PlanResWkf;
  
/**
 *
 * @author AllanRamiro
 */
public interface planillaDao {
  
    public List<planResTmpWkf> ListarEmpleados();
    public boolean PlanDetalleEmpleadoWkf(int var_nro_planilla);
    public boolean PlanResumenPlanillaWkfcrear(PlanMaeWkf obj, List<PlanResWkf> ListObj);
    public boolean PlanResumenEmpleadoWkf(PlanResWkf obj);
    
}
