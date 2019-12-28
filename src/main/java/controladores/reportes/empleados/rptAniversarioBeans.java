/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.reportes.empleados;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import reportes.iReport.empleados.rptAniversarios;
import static utilerias.funciones.funciones.rptsEmpleados;

@Named(value = "rptAniversarioBeans")
@RequestScoped
public class rptAniversarioBeans implements Serializable {

    private int periodo;

    /**
     * Creates a new instance of rptAltasBeans
     */
    private boolean rsp;

    public rptAniversarioBeans() {

        rsp = FacesContext.getCurrentInstance().isPostback();

        if (rsp == false) {
            this.periodo = 8;
        }
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    

    //Declaracion del Metodo para invocar el reporte desde el xhtml
    public void rptAniversarios() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

        //Instancia la clase o el metodo rptCumpleañeros, que es la encargada de generar el un archivo pdf        
        rptAniversarios rpt = new rptAniversarios();

        // Se captura y se instancia el Contexto de la Vista levantada por  ViewScope
        FacesContext fc = FacesContext.getCurrentInstance();
        // Se instancia servlet para determinar la ruta real de la Aplicacion
        ServletContext sc = (ServletContext) fc.getExternalContext().getContext();

        String path =  sc.getRealPath(rptsEmpleados()+ "rptaniversarios.jasper") ; 

        rpt.getReporte(path, periodo);

        // Cuando finaliza de procesar el Contexto del Servlet dentro de la vista, lo muestra en pantalla        
        FacesContext.getCurrentInstance().responseComplete();
    }

}
