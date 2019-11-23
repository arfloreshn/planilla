/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes.contralador.planilla;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import reportes.iReporte.planilla.rptimprimirplanilla;
import static utilerias.funciones.funciones.PathRpt;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "rptImprimirPlanillaBeans")
@RequestScoped
public class rptImprimirPlanillaBeans implements Serializable {

    private int nroPlanilla;
    private boolean rsp;
     
    public rptImprimirPlanillaBeans() {

         // long d = System.currentTimeMillis();
        rsp = FacesContext.getCurrentInstance().isPostback();

        if (rsp == false) {
          this.nroPlanilla = 0;
        }
    }

    public int getNroPlanilla() {
        return nroPlanilla;
    }

    public void setNroPlanilla(int nroPlanilla) {
        this.nroPlanilla = nroPlanilla;
    }
    
    
  //Declaracion del Metodo para invocar el reporte desde el xhtml
    public void rptPlanilla() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

        //Instancia la clase o el metodo rptAltas, que es la encargada de generar el un archivo pdf        
        rptimprimirplanilla rpt = new rptimprimirplanilla();

        // Se captura y se instancia el Contexto de la Vista levantada por  ViewScope
        FacesContext facesContext = FacesContext.getCurrentInstance();

       
        // Se instancia servlet para determinar la ruta real de la Aplicacion
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String path =  servletContext.getRealPath(PathRpt()+ "/planilla/rptPlaP01EnAjuste.jasper") ; 

        rpt.getReporte(path, this.nroPlanilla);

        // Cuando finaliza de procesar el Contexto del Servlet dentro de la vista, lo muestra en pantalla        
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    
}
