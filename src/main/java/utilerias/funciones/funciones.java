/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.funciones;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author root
 */
public class funciones {
    
    FacesContext fc = FacesContext.getCurrentInstance();
    ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
    
    
    private static final String app="/app/";
    
   // Path de los directorios de la vista
    private static final String catalagos="app/catalagos";
    private static final String procesos="app/procesos";
    private static final String pathReportes="app/reportes/";
    // Path de los directorios de los recursos de la app y jaspertReport
    private static final String rpts = "app/reportes/rpt/"; 
    private static final String images = "resources/images//";
   
    private static final String contexto="/planilla";
    private final String RealPath =  sc.getRealPath(contexto);

     private static final String rptsEmpleados =  rpts + "empleados/";
     private static final String rptsPlanillas =  rpts + "planillas/";
   
    
    public String getHome() {
        return RealPath;
    }
    
    public static String PathInicio()
    {
        return contexto;
    }

    public static String PathApp()
    {
        return app;
    }
    
  
    public static String PathFrmCatalagos()
    {
     return catalagos;
    }
    
     public static String PathFrmProcesos()
    {
     return  procesos;
    }
     
     public static String PathReporte()
    {
     return pathReportes;
    } 
   
     public static String PathRpt()
    {
     return rpts;
    } 
   
     public static String PathImg()
    {
     return images;
    } 
    
    public static String rptsEmpleados()
    {
      return rptsEmpleados;
    } 

     public static String rptsPlanillas()
    {
      return rptsPlanillas;
    } 

    
     public ServletContext fnContexto() {
         return this.sc;
     }
     
    public static String encriptar(String cadena)
    {
        StringBuilder sb = new StringBuilder();
       
         try {
            
             MessageDigest md = MessageDigest.getInstance("SHA-512");
             md.update(cadena.getBytes());
             byte[] mb = md.digest();
            
             for (int i = 0; i < mb.length; i++)
             {
                 sb.append(Integer.toString((mb[i] & 0xff) + 0x100,16).substring(1));
             } 
             
        } catch (NoSuchAlgorithmException ex) 
        {
        
        }
        
      return sb.toString();
    }   
    
   
}
