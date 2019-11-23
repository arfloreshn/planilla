/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Implementar;

import catalogos.dao.Interfaces.periodosDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Tmeses;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.uh;
import utilerias.jodbc.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
public class periodosDaoImpl implements periodosDao{

    
     private Session ses;
     private Transaction tx;
     private List<Tmeses> listar;
     private String sql = "";
     private ResultSet rs = null;
     private Statement st;
     private Connection con = null;
    @Override
    public List<Tmeses> lstmeses() {
        this.sql = "from Tmeses";
        this.tx = null;
        ses = null;
        try {

            ses = uh.getSessionFactory().openSession();
            tx = ses.beginTransaction();
            listar= ses.createQuery(sql).list();
            listar.size();
            tx.commit();
        
            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                listar= null;
            }
            e.printStackTrace();
        } 
        
        finally {
            ses.close();
        }

            return listar;
    
    }

    @Override
    public int mesFiscal() 
    {
       int var_mes = 0;
       try {
            this.con = dbConexcion.getConnection();
             st = con.createStatement();
             sql = "select a.nro_mes, a.anio_fiscal from tperiodo_contable a where a.sn_cerrado='N'"; 
             this.rs = st.executeQuery(sql);
              while (rs.next()) {
               var_mes  =  rs.getInt(("nro_mes"));
             }
            con.close();
         } catch (SQLException ex) {
            ex.getMessage();
        } 
        
        return  var_mes;
    }

    @Override
    public int anioFiscal() {
       int var_anio = 0;
       try {
            this.con = dbConexcion.getConnection();
             st = con.createStatement();
             sql = "select a.nro_mes, a.anio_fiscal from tperiodo_contable a where a.sn_cerrado='N'"; 
             this.rs = st.executeQuery(sql);
              while (rs.next()) {
               var_anio  =  rs.getInt(("anio_fiscal"));
             }
            con.close();
         } catch (SQLException ex) {
            ex.getMessage();
        } 
        
        return  var_anio;
    }
    
}
