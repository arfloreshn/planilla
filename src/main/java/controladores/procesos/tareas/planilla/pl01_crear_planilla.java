/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.procesos.tareas.planilla;

import dao.implementar.catalagos.periodosDaoImpl;
import dao.implementar.catalagos.tctasbcoDaoImpl;
import dao.implementar.catalagos.ttipoMonedaDaoImpl;
import dao.implementar.catalagos.ttipoPlanillaDaoImpl;
import dao.interfaz.catalagos.periodosDao;
import dao.interfaz.catalagos.tctasBcoDao;
import dao.interfaz.catalagos.ttipoMonedaDao;
import dao.interfaz.catalagos.ttipoPlanillaDao;
import utilerias.colecciones.planRedTmpWkfDataModel;
import utilerias.colecciones.planResTmpWkf;
import dao.implementar.empleados.empleadoDaoImpl;
import dao.interfaz.empleados.empleadoDao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import modelo.EmpMae;
import modelo.PlanDetWkf;
import modelo.PlanMaeWkf;
import modelo.PlanResWkf;
import modelo.TbancoCtas;
import modelo.Tmoneda;
import modelo.TtipoPlanilla;
import modelo.Usuario;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import dao.implementar.planilla.planillaImpl;
import dao.interfaz.planilla.planillaDao;
import javax.annotation.PostConstruct;
import utilerias.System.clsSistemaImpl;
import utilerias.System.clsSistema;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "planillacrearBean")
@ViewScoped
public class pl01_crear_planilla implements Serializable {

    /**
     * Creates a new instance of planillacrearBean
     */
    private int mPeriodo;
    private int manio;
    private int var_Tipoplanilla;
    private int var_cod_moneda;
    private int var_cod_cta_bco;
    private Usuario usuario;
    private clsSistema objSystem = new clsSistemaImpl();
    private Date var_fecha_sistema = objSystem.getFechaHoy();

    private EmpMae var_empleado = new EmpMae();
    private empleadoDao objEmpDao = new empleadoDaoImpl();
    private planillaDao objPlanilla = new planillaImpl();
    private periodosDao objPeriodo = new periodosDaoImpl();

    private PlanMaeWkf var_planMaeWkf = null;
    private PlanResWkf var_PlanResWkf = null;
    private PlanDetWkf var_PlanDetWkf = null;

    private List<SelectItem> ListarTipoPlanilla;
    private List<SelectItem> ListarTipoMoneda;
    private List<SelectItem> ListarBancos;
    private List<SelectItem> ListarCuentaBancaria;

    private BigDecimal var_factor_cambio;

    private int var_nro_pagos = 0;
    private int var_nro_planilla = 0;
    private double var_tot_bruto = 0.00;
    private double var_tot_debitos = 0.00;
    private double var_tot_credito = 0.00;
    private double var_tot_neto = 0.00;

    private List<planResTmpWkf> listadePagos;
    private planResTmpWkf[] selectedFilas;
    private planRedTmpWkfDataModel WkfDataModel;

    @PostConstruct
    public void init()
    {
       iniciar_variables();
    }
    
    
    public pl01_crear_planilla() {
        iniciar_variables();
    }

    private void iniciar_variables() 
    {
        this.ListarTipoPlanilla = new ArrayList<SelectItem>();
        this.listadePagos = new ArrayList<planResTmpWkf>();
        this.ListarCuentaBancaria = new ArrayList<SelectItem>();
        this.WkfDataModel = new planRedTmpWkfDataModel(listadePagos);
        this.mPeriodo = objPeriodo.mesFiscal();
        this.manio = objPeriodo.anioFiscal();
        this.var_fecha_sistema = objSystem.getFechaHoy();
        this.var_factor_cambio = BigDecimal.valueOf(1);
   
    }
    

    // Instanciandos nueva pagos de planillaCargando el listado de empleados 
    public void nuevoDetalledePago() 
    {
        this.listadePagos = new ArrayList<planResTmpWkf>();
        getLstEmpleados();
     }
    
    
    // Cargando el listado de empleados 
    public void cargarEmpleados() 
    {
        this.listadePagos = new ArrayList<planResTmpWkf>();
        this.listadePagos = objPlanilla.ListarEmpleados();
        getLstEmpleados();
    }
    
    // Devuelviendo el resultado a la vista o al html de los empleados a los que les voy a pagar
    public List<planResTmpWkf> getLstEmpleados() {
        return listadePagos;
    }

    public planResTmpWkf[] getSelectedFilas() {
        return selectedFilas;
    }

    public void setSelectedFilas(planResTmpWkf[] selectedFilas) {
        this.selectedFilas = selectedFilas;
        //totalizar();
    }

    public planRedTmpWkfDataModel getWkfDataModel() {
        return WkfDataModel;
    }

    public void setWkfDataModel(planRedTmpWkfDataModel WkfDataModel) {
        this.WkfDataModel = WkfDataModel;
    }

    public Date getVar_fecha_sistema() {
        return var_fecha_sistema;
    }

    public void setVar_fecha_sistema(Date var_fecha_sistema) {
        this.var_fecha_sistema = var_fecha_sistema;
    }

    public int getVar_Tipoplanilla() {
        return var_Tipoplanilla;
    }

    public void setVar_Tipoplanilla(int var_Tipoplanilla) {
        this.var_Tipoplanilla = var_Tipoplanilla;
    }

    public int getmPeriodo() {
        return mPeriodo;
    }

    public void setmPeriodo(int mPeriodo) {
        this.mPeriodo = mPeriodo;
    }

    public int getManio() {
        return manio;
    }

    public void setManio(int manio) {
        this.manio = manio;
    }

    public int getVar_nro_pagos() {
        return var_nro_pagos;
    }

    public double getVar_tot_bruto() {
        return var_tot_bruto;
    }

    public double getVar_tot_debitos() {
        return var_tot_debitos;
    }

    public double getVar_tot_credito() {
        return var_tot_credito;
    }

    public double getVar_tot_neto() {
        return var_tot_neto;
    }

    public int getVar_cod_moneda() {
        return var_cod_moneda;
    }

    public void setVar_cod_moneda(int var_cod_moneda) {
        this.var_cod_moneda = var_cod_moneda;
    }

    public BigDecimal getVar_factor_cambio() {
        return var_factor_cambio;
    }

    public void setVar_factor_cambio(BigDecimal var_factor_cambio) {
        this.var_factor_cambio = var_factor_cambio;
    }

    public int getVar_cod_cta_bco() {
        return var_cod_cta_bco;
    }

    public void setVar_cod_cta_bco(int var_cod_cta_bco) {
        this.var_cod_cta_bco = var_cod_cta_bco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getVar_nro_planilla() {
        return var_nro_planilla;
    }

    public void setVar_nro_planilla(int var_nro_planilla) {
        this.var_nro_planilla = var_nro_planilla;
    }

    
    //Obtener el Tipo de planilla que voy pagar (13avo), (14avo) etc
    public List<SelectItem> getListarTipoPlanilla() {

        this.ListarTipoPlanilla = new ArrayList<SelectItem>();
        ttipoPlanillaDao objDao = new ttipoPlanillaDaoImpl();
        List<TtipoPlanilla> doc = objDao.lstTipoPlanilla();

        this.ListarTipoPlanilla.clear();

        for (TtipoPlanilla obj : doc) {
            SelectItem item = new SelectItem(obj.getId(), obj.getDesc());
            this.ListarTipoPlanilla.add(item);
        }

        return ListarTipoPlanilla;
    }

    
    //Listar el tipo de moneda en la cual voy a pagar la planilla
    public List<SelectItem> getListarTipoMoneda() {

        this.ListarTipoMoneda = new ArrayList<SelectItem>();
        ttipoMonedaDao objDao = new ttipoMonedaDaoImpl();
        List<Tmoneda> objmoneda = objDao.lstMonedas();

        this.ListarTipoMoneda.clear();

        for (Tmoneda obj : objmoneda) {
            SelectItem item = new SelectItem(obj.getId(), obj.getDescripcion());
            this.ListarTipoMoneda.add(item);
        }

        return ListarTipoMoneda;
    }

    // Obtener la cuenta bancaria de la cual voy a pagar la planilla
    public List<SelectItem> getListarCuentaBancaria() {
        this.ListarCuentaBancaria = new ArrayList<SelectItem>();
        tctasBcoDao objDao = new tctasbcoDaoImpl();
        List<TbancoCtas> objCuentas = objDao.lstCuentasBco();
        this.ListarCuentaBancaria.clear();
        for (TbancoCtas obj : objCuentas) {
            SelectItem item = new SelectItem(obj.getId(), obj.getNroCtaBco());
            this.ListarCuentaBancaria.add(item);
        }

        return ListarCuentaBancaria;
    }

    public void toggleSelected(ToggleSelectEvent tse) {
        totalizar();
    }

    public void onRowSelect(SelectEvent event) {
        //this.var_tot_bruto = this.var_tot_bruto+((planResTmpWkf) event.getObject()).getImpSueldoBruto();
        totalizar();
    }

    public void onRowUnselect(UnselectEvent event) {
        //this.var_tot_bruto = this.var_tot_bruto-((planResTmpWkf) event.getObject()).getImpSueldoBruto();
        totalizar();
    }

    public void totalizar() {

        this.var_nro_pagos = 0;
        this.var_tot_bruto = 0.00;
        this.var_tot_debitos = 0.00;
        this.var_tot_credito = 0.00;
        this.var_tot_neto = 0.00;

        if (selectedFilas.length > 0) {

            //Se leen las filas seleccionadas y se procesaran en los archivos correspondientes
            for (int i = 0; i < selectedFilas.length; i++) {
                this.var_nro_pagos = this.var_nro_pagos + 1;
                this.var_tot_bruto = this.var_tot_bruto + selectedFilas[i].getImpSueldoBruto();
                this.var_tot_debitos = this.var_tot_debitos + selectedFilas[i].getImpDebitos();
                this.var_tot_credito = this.var_tot_credito + selectedFilas[i].getImpCreditos();
                this.var_tot_neto = this.var_tot_neto + selectedFilas[i].getImpPagoNeto();
            }
        }

    }

    public void cmdguardar() throws Exception {
        totalizar();

        List<PlanResWkf> lstWkfResumenEmpleado = new ArrayList<PlanResWkf>();

        boolean snRegistrado = false;

        Tmoneda objMoneda = new Tmoneda();
        objMoneda.setId(this.var_cod_moneda);

        TbancoCtas objCuenta = new TbancoCtas();
        objCuenta.setId(this.var_cod_cta_bco);

        this.usuario = new Usuario();
        this.usuario.setUsuario("arflores");

        this.var_nro_planilla = objSystem.NroCorrelativo("plan_mae_wkf"); //Obtengo el numero de planilla

        this.var_planMaeWkf = new PlanMaeWkf();
        var_planMaeWkf.setNroPlanilla(this.var_nro_planilla);
        var_planMaeWkf.setNroPeriodo(this.mPeriodo);
        var_planMaeWkf.setAnioPeriodo(this.manio);
        var_planMaeWkf.setCreadaPor(usuario.getUsuario());
        var_planMaeWkf.setFecCreacion(this.var_fecha_sistema);
        var_planMaeWkf.setSnRevisada("N");
        var_planMaeWkf.setSnAutorizada("N");
        var_planMaeWkf.setTbancoCtas(objCuenta);
        var_planMaeWkf.setTmoneda(objMoneda);
        var_planMaeWkf.setFactorCambio(this.var_factor_cambio);
        var_planMaeWkf.setNroLineas(this.var_nro_pagos);
        var_planMaeWkf.setImpCheque(BigDecimal.valueOf(this.var_tot_neto));
        var_planMaeWkf.setImpChequeEq(BigDecimal.valueOf(this.var_tot_neto));
        var_planMaeWkf.setSnEnviada("N");
        var_planMaeWkf.setSnContabilizada("N");


        snRegistrado = objPlanilla.PlanResumenPlanillaWkfcrear(var_planMaeWkf, lstWkfResumenEmpleado); //Guardo el maestro de la planilla

        
          if (selectedFilas.length > 0) {

            //Se leen las filas seleccionadas y se procesaran en los archivos correspondientes
            for (int i = 0; i < selectedFilas.length; i++) {

                var_empleado = new EmpMae();
                var_empleado = this.objEmpDao.buscarEmpleadoXid(selectedFilas[i].getCodEmp());

                //Inicializo la tabla de resumen por cada empleado
                //tengo que recorrer a todos los empleados
                this.var_PlanResWkf = new PlanResWkf();
                var_PlanResWkf.setPeriodo(mPeriodo);
                var_PlanResWkf.setAnio(manio);
                var_PlanResWkf.setNroPlanilla(var_nro_planilla);
                var_PlanResWkf.setPlanMaeWkf(var_planMaeWkf);
                var_PlanResWkf.setEmpMae(var_empleado);
                var_PlanResWkf.setNroCtaBco("12345678901");
                var_PlanResWkf.setImpCreditos(BigDecimal.valueOf(selectedFilas[i].getImpCreditos()));
                var_PlanResWkf.setImpDebitos(BigDecimal.valueOf(selectedFilas[i].getImpDebitos()));
                var_PlanResWkf.setImpPagoNeto(BigDecimal.valueOf(selectedFilas[i].getImpPagoNeto()));
                var_PlanResWkf.setImpSueldoBruto(BigDecimal.valueOf(selectedFilas[i].getImpSueldoBruto()));
                var_PlanResWkf.setSnAutorizado("N");

                 snRegistrado = objPlanilla.PlanResumenEmpleadoWkf(var_PlanResWkf); //Guardar el Resumen por Empleado

            }
        }

                
         snRegistrado = objPlanilla.PlanDetalleEmpleadoWkf(var_nro_planilla); // Guardar el detalle de deduccion por empleado;
    }

}
