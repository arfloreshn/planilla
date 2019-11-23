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
    

    private int nro_correlativo = 0;
        
     
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
    String path =  servletContext.getRealPath(PathInicio()); 
    
    public static String PathInicio()
    {
        return "http://localhost:8080/planilla";
    }

    public static String PathApp()
    {
        return "/app/";
    }
    
  
    public static String PathFrmCatalagos()
    {
     return "app/catalagos";
    }
    
    
     public static String PathFrmProcesos()
    {
     return  "app/procesos";
    }
     
     public static String PathReporte()
    {
     return "app/reportes/";
    } 
   
     public static String PathRpt()
    {
     return "app/reportes/rpt";
    } 
   
     public static String PathImg()
    {
     return "resources/images//";
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
