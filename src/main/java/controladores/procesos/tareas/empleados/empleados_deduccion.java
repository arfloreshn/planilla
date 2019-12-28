/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.procesos.tareas.empleados;

import dao.implementar.empleados.empDeduccionesDaoImpl;
import dao.interfaz.empleados.empleadoDao;
import dao.implementar.empleados.empleadoDaoImpl;
import dao.interfaz.catalagos.ttipoMovimientoDao;
import dao.implementar.catalagos.ttipoMovimientoDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.EmpMovimiento;
import modelo.EmpMae;
import modelo.TtipoMovimiento;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import utilerias.funciones.msgbox;
import dao.interfaz.empleados.empDeduccionesDao;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "empleadosDeduccion")
@ViewScoped
public class empleados_deduccion implements Serializable {

    /*    
      Objetos de Tipo Tabla,  (En lenguaje de VB60, sensillamente se les llama variables).
      La diferencia entre un Objeto y una Variable.
      Es que el Objeto posee propiedades y atributos, las variables no las poseen
      El unico Objeto que podemos comparar en VB6.0 a un objeto Java son las Clases o DLL son lo mas cercano
     a la definición de un Objeto en Java
    */
    
    private EmpMae empleados;   // Entidad de EmpMae
    private EmpMovimiento deducciones;  // Entidad de Deducciones por Empleado
    
    // variables enteras
    private int id_empleado;        // Id interno de Empleado (No se muestra al usuario)
    private int id_deduccion;       // Codigo o Id de Deduccion 
  
    // Variables de doubles, para sumatorias
  
    private double importe;
    private double mTotal;          // Acumulador de Totales

    // Variables de Tipo Boleanas
    private boolean sn_Existe;      // Validador de la Existencia o no de las deducciones en la tabla de deducciones
    
    // Variables de Tipo String
    private String codEmpleado;     // Codigo de Trabajo para el usuario, este si se muestra al usuario 
    
    private List<EmpMae> lstEmpleados;  // Objeto de tipo lista, para desplegar la información del Empleado
    private List<TtipoMovimiento> Lstprodecencia; //Objeto de tipo lista, de las deducciones <NO> Aplicadas.
    private List<TtipoMovimiento> Lstdestino;    // Objeto de tipo listo, de las deducciones Aplicadas.
    private List<EmpMovimiento> LstDeduccionesPorEmpleado;  //Objeto de tipo Lista, con el detalle de las deducciones aplicadas

    private DualListModel<TtipoMovimiento> TipoDeducciones; // Objeto DualModel de PrimeFaces, para ser instanciado en la pagina HTML
    

    empleadoDao emp = null;
    empDeduccionesDao empdeduccionesDao = null;
    ttipoMovimientoDao tipo = null;

    @PostConstruct
    public void init() {
        //No realizar PostBak, en caso de usar Ajax o una ejecucion Ajax
        if (!FacesContext.getCurrentInstance().isPostback()) {
              inicializarObjetos();   //Inicializar todos los objetos
        }
    }

    /*
       El constructor maestro o padre de los Bean, es que inicializa los objetos en el HTML
     */
    public empleados_deduccion() {
          // Variables de Tipo Boleanas
        if (!FacesContext.getCurrentInstance().isPostback()) {
        
             inicializarObjetos();
        }

    }

    private void inicializarObjetos() 
    {
            this.codEmpleado = "";
            this.importe = 0;                    
            this.deducciones = new EmpMovimiento(); // Inicializar un objetivo de Tipo Tabla o Class Entity
            this.emp = new empleadoDaoImpl();  // Establecer las implementaciones para los empleados
            this.empdeduccionesDao = new empDeduccionesDaoImpl();  // Establecer las implementaciones para las deducciones
            this.Lstprodecencia = Origen();                   //Lista de posibles deducciones sin aplicar
            this.Lstdestino = new ArrayList<TtipoMovimiento>(); //Inicializacion de Lista de Destino
            this.LstDeduccionesPorEmpleado = new ArrayList<EmpMovimiento>(); // Inicializacion en limpio de las deduccione por empleado
            TipoDeducciones = new DualListModel<TtipoMovimiento>(this.Lstprodecencia, this.Lstdestino); // Instanciación de la variable dual model
            this.mTotal = 0.00;
   }

    
    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public EmpMovimiento getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(EmpMovimiento deducciones) {
        this.deducciones = deducciones;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getmTotal() {
        return mTotal;
    }

    public void setmTotal(double mTotal) {
        this.mTotal = mTotal;
    }

    
    public DualListModel<TtipoMovimiento> getTipoDeducciones() {
        return TipoDeducciones;
    }

    public void setTipoDeducciones(DualListModel<TtipoMovimiento> TipoDeducciones) {
        this.TipoDeducciones = TipoDeducciones;
    }

    public List<EmpMae> getLstEmpleados() {
        this.lstEmpleados = this.emp.lstEmpleado();
        return lstEmpleados;
    }

    public List<TtipoMovimiento> getLstDestino() {
        return Lstdestino;
    }

    public EmpMae getEmpleados() {
        return empleados;
    }

    public void setEmpleados(EmpMae empleados) {
        this.empleados = empleados;
    }

    public List<EmpMovimiento> getLstDeduccionesPorEmpleado() {
        return LstDeduccionesPorEmpleado;
    }

    public void setLstDeduccionesPorEmpleado(List<EmpMovimiento> LstDeduccionesPorEmpleado) {
        this.LstDeduccionesPorEmpleado = LstDeduccionesPorEmpleado;
    }

    
    /*
    BUSCO EL CODIGO DEL EMPLEADO Y LOS DATOS GENERALES DEL EMPLEADO
    */
    
    public void buscarEmpleado() {

        if (this.codEmpleado == null && this.codEmpleado.length() <= 0) {
            return;
        }

        this.empleados = this.emp.buscarEmpleadoXid(this.codEmpleado);
        this.deducciones.setEmpMae(empleados);
        this.id_empleado = this.empleados.getId();
        this.codEmpleado = this.empleados.getCodEmpleado();

        if (this.empleados == null) {
            msgbox.vbPrecaucion("El codigo de empleado, no existe");
        } else {

            this.Lstprodecencia = new ArrayList<TtipoMovimiento>();
            this.Lstprodecencia = Origen();

            this.Lstdestino = new ArrayList<TtipoMovimiento>();
            this.Lstdestino = Destino();
            this.TipoDeducciones = new DualListModel<TtipoMovimiento>(this.Lstprodecencia, this.Lstdestino);
            lstDeduccionXempleado();
            totalizar();
            msgbox.vbInfo("Codigo correcto");

        }

    }

    public List<TtipoMovimiento> Origen() {

        List<TtipoMovimiento> mListaOrigen = new ArrayList<TtipoMovimiento>();

        this.tipo = new ttipoMovimientoDaoImpl();
        mListaOrigen = this.tipo.lstTipoMovimiento();
        if (this.codEmpleado != null && this.codEmpleado.length() > 0) {
            mListaOrigen = this.tipo.lstTipoMovimintoXCodigoEmpleado(this.id_empleado);
        }

        return mListaOrigen;

    }

    public List<TtipoMovimiento> Destino() {

        this.tipo = new ttipoMovimientoDaoImpl();
        List<TtipoMovimiento> mListaDestino = new ArrayList<TtipoMovimiento>();
        mListaDestino = this.tipo.lstDeducionesPorEmpleado(this.id_empleado);
        return mListaDestino;

    }

    public List<EmpMovimiento> lstDeduccionXempleado() {
        
        this.LstDeduccionesPorEmpleado = new ArrayList<EmpMovimiento>();
        this.LstDeduccionesPorEmpleado.clear();
        this.LstDeduccionesPorEmpleado = empdeduccionesDao.lstDeduciones(this.id_empleado);
        return this.LstDeduccionesPorEmpleado;
    }

 
    
    public void onRowEdit(RowEditEvent eRow) 
    {
            Object obTb = eRow.getObject();
            this.deducciones  =  (EmpMovimiento) obTb;
            empdeduccionesDao.edita(this.deducciones);
            lstDeduccionXempleado();
            totalizar();
            
        
    }

    
    /*
    public void onCellEdit(CellEditEvent eCol) {
        String col = eCol.getColumn().getHeaderText();
        Object NewValue = eCol.getNewValue();
        Object OldValue = eCol.getOldValue();
    }
     */

    public void grabarDeducciones() {

        //Favor no tocar el codigo, de esta manera funciona, AHH importantisimo no quitar el convertidor de la pagina web
        //El convertidor es indispensable para este tipo el pickList de PrimeFaces 
        ListIterator<TtipoMovimiento> it = TipoDeducciones.getTarget().listIterator();

        try {
            while (it.hasNext()) {

                TtipoMovimiento tb = it.next();
                this.id_deduccion = tb.getid();

                if (!empdeduccionesDao.snExistDeduccion(this.id_empleado, this.id_deduccion)) {

                    // Le pasamos los parametros o los valores si prefiere llamarse asi
                    deducciones.setEmpMae(this.empleados);
                    deducciones.setTtipoMovimiento(tb);
                    deducciones.setSnActivo(true);
                    deducciones.setSnAcumula(true);

                    // usamos Hibernate para grabar los valores de la nueva deduccion del empleado
                    empdeduccionesDao.crear(deducciones);
                    lstDeduccionXempleado();

                }

            }

        } catch (Exception e) {
            e.getMessage();
        }

    }
    
   
    
    private double totalizar() 
    {
    
         EmpMovimiento data = new EmpMovimiento();

        
           
           ListIterator<EmpMovimiento> it = this.LstDeduccionesPorEmpleado.listIterator();

            this.mTotal = 0.00;
            while (it.hasNext()) {
                data = it.next();
                mTotal += data.getImporte();
            }
        
        return mTotal;
    }

}
