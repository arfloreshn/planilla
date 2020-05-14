/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.System;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

 

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

import utilerias.jodbc.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
public class clsSistemaImpl implements clsSistema  {

    
     private String sql = "";
     private ResultSet rs = null;
     private Statement st;
     private Connection con = null;
     public Date fechaSistema = null;
     private int var_nro_correlativo = 0;
     
     @Override
    public Date getFechaHoy() {
    try {
            this.con = dbConexcion.getConnection();
             st = con.createStatement();
             sql = "SELECT CURDATE()"; 
             this.rs = st.executeQuery(sql);
              while (rs.next()) {
               fechaSistema  =  rs.getDate(1);
             }
            con.close();
         } catch (SQLException ex) {
            ex.getMessage();
        } 
      return fechaSistema;
	
    }

    @Override
    public Timestamp getFechaHoraHoy() {
        long ms =  getFechaHoy().getTime();
        java.sql.Timestamp ts = new java.sql.Timestamp(ms);
        return ts;
    }

    @Override
    public Timestamp getDateTime(Date var_fecha) {
        java.sql.Timestamp ts = new java.sql.Timestamp(var_fecha.getTime());
        return ts;
    }
    
    
    @Override
    public int NroCorrelativo(String Tabla) throws Exception {

        String sSql = "";

        sSql = "SELECT fn_correlativo('" + Tabla + "')";
        CallableStatement cs = dbConexcion.getConnection().prepareCall(sSql);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            this.var_nro_correlativo = rs.getInt(1); // return value
        }
        cs.close();

        return this.var_nro_correlativo;
    }

    
    @Override
     public void postProcessXLS(Object document) {  
	    HSSFWorkbook wb = (HSSFWorkbook) document;  
	    HSSFSheet sheet = wb.getSheetAt(0);  
	    HSSFRow header = sheet.getRow(0);  
	      
	    HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	    
            int columnas = header.getPhysicalNumberOfCells()-1;
            for(int i=0; i < columnas ;i++) {  
	        HSSFCell cell = header.getCell(i);  
	        cell.setCellStyle(cellStyle);  
	    }  
	}  

     
    @Override
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {  
	    Document pdf = (Document) document;  
	    pdf.open();  
	    pdf.setPageSize(PageSize.A4);  
	  
	//    InputStream stream = funciones.class.getResourceAsStream("planilla/resources/images/rrhh.png");
	//    byte[] logoBytes =  IOUtils.toByteArray(stream);	   
	//    pdf.add(Image.getInstance(logoBytes));  
	} 
    

}
