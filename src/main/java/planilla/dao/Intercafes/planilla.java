/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilla.dao.Intercafes;

import colecciones.planResTmpWkf;
import java.util.List;
import modelo.PlanDetWkf;
import modelo.PlanMaeWkf;
import modelo.PlanResWkf;

/**
 *
 * @author AllanRamiro
 */
public interface planilla {
    public List<planResTmpWkf> ListarEmpleados();
    public boolean PlanResumenPlanillaWkfcrear(PlanMaeWkf obj, List<PlanResWkf> ListObj); // Guardo el resumen maestro de la planilla
    public boolean PlanResumenEmpleadoWkf(PlanResWkf obj); // Guardo el resumen maestro por empleado
    public boolean PlanDetalleEmpleadoWkf(int var_nro_planilla); // Guardo el detalle por empleado
    
}
