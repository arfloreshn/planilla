/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes.iReporte.planilla;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.engine.util.JRLoader;
import static utilerias.jodbc.dbConexcion.getConnection;

/**
 *
 * @author AllanRamiro
 */
public class rptimprimirplanilla implements Serializable {
 
    
      public void rptimprimirplanilla()
      {
      }
    
      public void getReporte(String ruta, int var_nroPlanilla ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {

        Connection conexion = null;
      try {

       //Conexcion a JODBC Mysql    
        conexion = getConnection();

        //Se definen los parametros si es que el reporte necesita
        Map parameter = new HashMap();
        
        
        parameter.put("nro_Planilla", var_nroPlanilla); // El put de la variable debe ser identico al creado en el reporte
            
        File file = new File(ruta);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);

            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
     
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
            pdfExporter.exportReport();
           
            response.setContentType("application/pdf");
            response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
            response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
 
            
            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(pdfReportStream.toByteArray());
            responseOutputStream.close();
            pdfReportStream.close();
       
            
            if (pdfExporter != null) {
                try {
                  pdfExporter.exportReport();
                } catch (JRException e) {
                  //  logger.info(e.getMessage())
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
