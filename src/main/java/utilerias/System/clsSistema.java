/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.System;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author AllanRamiro
 */
public interface clsSistema {
 
  public Date getFechaHoy(); 
  public java.sql.Timestamp getFechaHoraHoy();
  public java.sql.Timestamp getDateTime(java.util.Date  var_fecha);
  public int NroCorrelativo(String Tabla) throws Exception;
  public void postProcessXLS(Object document);
  public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException;  
}
