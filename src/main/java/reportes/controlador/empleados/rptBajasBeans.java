/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes.controlador.empleados;

import java.io.Serializable;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import reportes.iReport.empleados.rptBajas;
import static utilerias.funciones.funciones.PathRpt;

@Named(value = "rptBajasBeans")
@RequestScoped
public class rptBajasBeans implements Serializable {

    private Date fdesde;
    private Date fhasta;
 
    /**
     * Creates a new instance of rptAltasBeans
     */
    private boolean rsp;

    public rptBajasBeans() {

        // long d = System.currentTimeMillis();
        rsp = FacesContext.getCurrentInstance().isPostback();

        if (rsp == false) {
            this.fdesde = new Date();
            this.fhasta = new Date();
        }
    }

    public Date getFdesde() {
        return fdesde;
    }

    public void setFdesde(Date fdesde) {
        this.fdesde = fdesde;
    }

    public Date getFhasta() {
        return fhasta;
    }

    public void setFhasta(Date fhasta) {
        this.fhasta = fhasta;
    }

 
    //Declaracion del Metodo para invocar el reporte desde el xhtml
    public void rptBajas() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

        //Instancia la clase o el metodo rptBajass, es la que se encargada de generar el un archivo pdf        
        rptBajas rpt = new rptBajas();

        // Se captura y se instancia el Contexto de la Vista levantada por  ViewScope
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Se instancia servlet para determinar la ruta real de la Aplicacion
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String path =  servletContext.getRealPath(PathRpt()+ "/empleados/rptbajas.jasper") ; 

        rpt.getReporte(path, this.fdesde, this.fhasta);

        // Cuando finaliza de procesar el Contexto del Servlet dentro de la vista, lo muestra en pantalla        
        FacesContext.getCurrentInstance().responseComplete();
    }

}
