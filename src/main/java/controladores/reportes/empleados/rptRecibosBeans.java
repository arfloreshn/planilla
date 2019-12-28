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
import reportes.iReport.empleados.rptBajas;
import reportes.iReport.empleados.rptRecibos;
import static utilerias.funciones.funciones.PathRpt;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "rptRecibosBeans")
@RequestScoped
public class rptRecibosBeans implements Serializable{
    
    private boolean rsp;
    private int nro_recibo;
    
    
    public rptRecibosBeans()
    {
         rsp = FacesContext.getCurrentInstance().isPostback();

        if (rsp == false) 
        {
          this.nro_recibo = 0;
        }
    }

    public int getNro_recibo() {
        return nro_recibo;
    }

    public void setNro_recibo(int nro_recibo) {
        this.nro_recibo = nro_recibo;
    }
    
    
    
    
    
      //Declaracion del Metodo para invocar el reporte desde el xhtml
    public void rptRecibo() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

        //Instancia la clase o el metodo rptBajass, es la que se encargada de generar el un archivo pdf        
        rptRecibos rpt = new rptRecibos();

        // Se captura y se instancia el Contexto de la Vista levantada por  ViewScope
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Se instancia servlet para determinar la ruta real de la Aplicacion
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String path =  servletContext.getRealPath(PathRpt()+ "/empleados/rptrecibodepago.jasper") ; 

        rpt.getReporte(path, this.nro_recibo);

        // Cuando finaliza de procesar el Contexto del Servlet dentro de la vista, lo muestra en pantalla        
        FacesContext.getCurrentInstance().responseComplete();
    }

    
}
