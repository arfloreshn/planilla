/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.ttipoMovimientoDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.TtipoMovimiento;
import utilerias.jodbc.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
public class ttipoMovimientoDaoImpl implements ttipoMovimientoDao {

    private List<TtipoMovimiento> listar = null;
    private List<TtipoMovimiento> lstdeducciones = null;
    private ResultSet rs = null;
    private Statement st;
    
    @Override
    public List<TtipoMovimiento> lstTipoMovimiento() {
      
        
         try {
             this.listar  = new ArrayList<TtipoMovimiento>(); // Es obligatorio reinicializar las 
             Connection con = dbConexcion.getConnection();
            st = con.createStatement();
              String sql = "select a.id, a.desc, a.tipo from ttipo_moviento a where a.tipo='D'"; 

            this.rs = st.executeQuery(sql);
            
            this.listar.clear();
            while (rs.next()) {
               TtipoMovimiento tb = new TtipoMovimiento();
                tb.setdesc(rs.getString("desc"));
                tb.setid(rs.getInt("id"));
                tb.settipo(rs.getString("tipo"));
                this.listar.add(tb);
            }
            con.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    
        return this.listar;

    
    }

    
    //Beneficios por empleado
    @Override
    public List<TtipoMovimiento> lstMovimientodeIngresos(int id) {
       String sql = "select a.id, a.desc, a.tipo from ttipo_movimiento a" 
              + " where a.tipo='C' and not exists(select 1 from emp_movimiento b" 
              + " where b.id_mov=a.id"
              + " and b.id_emp=" +  id + ")";
    
    try {
     
       Connection con = dbConexcion.getConnection();
      st = con.createStatement();
      rs = st.executeQuery(sql);
      this.listar.clear();
      while (rs.next()) {
        TtipoMovimiento tb = new TtipoMovimiento();
        tb.setdesc(rs.getString("desc"));
        tb.setid(rs.getInt("id"));
        tb.settipo(rs.getString("tipo"));
        this.listar.add(tb);
      }
      
      con.close();
    } catch (SQLException ex) {
       ex.getMessage();
    }
    return this.listar;
    
    }

   
    //Deducciones por empleado
    @Override
    public List<TtipoMovimiento> lstTipoMovimintoXCodigoEmpleado(int id) 
    {
    String sql = "select a.id, a.desc, a.tipo from ttipo_movimiento a" 
              + " where a.tipo='D' and not exists(select 1 from emp_movimiento b" 
              + " where b.id_mov=a.id"
              + " and b.id_emp=" +  id + ")";
    
    try {
     
       Connection con = dbConexcion.getConnection();
      st = con.createStatement();
      rs = st.executeQuery(sql);
      this.listar.clear();
      while (rs.next()) {
        TtipoMovimiento tb = new TtipoMovimiento();
        tb.setdesc(rs.getString("desc"));
        tb.setid(rs.getInt("id"));
        tb.settipo(rs.getString("tipo"));
        this.listar.add(tb);
      }
      
      con.close();
    } catch (SQLException ex) {
       ex.getMessage();
    }
    return this.listar;
    
    }
    
    
    //Deducciones por empleado
    @Override
    public List<TtipoMovimiento> lstDeducionesPorEmpleado(int id) {
        
        
        String sql = "select a.id, a.desc, a.tipo from ttipo_movimiento a"
                + " where a.tipo='D' and exists(select 1 from emp_movimiento b"
                + " where b.id_mov=a.id"
                + " and b.id_emp=" + id + ")";

         try {
            lstdeducciones= new ArrayList<TtipoMovimiento>(); // Es obligatorio reinicializar las 
             Connection con = dbConexcion.getConnection();
            st = con.createStatement();
            this.rs = st.executeQuery(sql);
            
            lstdeducciones.clear();
            while (rs.next()) {
               TtipoMovimiento tb = new TtipoMovimiento();
                tb.setdesc(rs.getString("desc"));
                tb.setid(rs.getInt("id"));
                tb.settipo(rs.getString("tipo"));
                lstdeducciones.add(tb);
            }
            con.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lstdeducciones;

    }

    @Override
    public List<TtipoMovimiento> lstIngresosPorEmpleado(int id) {
        
        
        String sql = "select a.id, a.desc, a.tipo from ttipo_movimiento a"
                + " where a.tipo='C' and exists(select 1 from emp_movimiento b"
                + " where b.id_mov=a.id"
                + " and b.id_emp=" + id + ")";

         try {
            lstdeducciones= new ArrayList<TtipoMovimiento>(); // Es obligatorio reinicializar las 
             Connection con = dbConexcion.getConnection();
            st = con.createStatement();
            this.rs = st.executeQuery(sql);
            
            lstdeducciones.clear();
            while (rs.next()) {
               TtipoMovimiento tb = new TtipoMovimiento();
                tb.setdesc(rs.getString("desc"));
                tb.setid(rs.getInt("id"));
                tb.settipo(rs.getString("tipo"));
                lstdeducciones.add(tb);
            }
            con.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lstdeducciones;

    }

    
}
