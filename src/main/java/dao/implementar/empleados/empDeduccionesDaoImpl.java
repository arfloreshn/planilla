/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.empleados;

import dao.interfaz.empleados.empDeduccionesDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import modelo.EmpMovimiento;
import modelo.TtipoMovimiento;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.jodbc.dbConexcion;
import utilerias.hibernate.uh;

/**
 *
 * @author AllanRamiro
 */
public class empDeduccionesDaoImpl implements empDeduccionesDao {

    private Session session;
    private Transaction trans;
    private List<EmpMovimiento> listar;
    private List<TtipoMovimiento> lstDeducciones;
    private ResultSet rs = null;
    private Statement st;

    @Override
    public List<EmpMovimiento> lstDeduciones(int id) {
        
        String sql = "";
        sql = "FROM EmpMovimiento a"
        + " inner join fetch a.ttipoMovimiento b"
        + " inner join fetch a.empMae c"
        + " where b.tipo='D' and c.id=" + id;
                
        Transaction tx = null;

        session = null;
        try {

            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            listar = session.createQuery(sql).list();
            listar.size();
            tx.commit();

            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                listar = null;
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return listar;

    }

    @Override
    public boolean crear(EmpMovimiento obj) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.save(obj);
            trans.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            trans.rollback();
            throw ex;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;

    }

    @Override
    public boolean edita(EmpMovimiento obj) {
        boolean flag = false;
        try {
            
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.update(obj);
            trans.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            trans.rollback();
            throw ex;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;

    }

    @Override
    public boolean borrar(int id) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            EmpMovimiento obj = (EmpMovimiento) session.load(EmpMovimiento.class, id);
            session.delete(obj);
            trans.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            trans.rollback();
            throw ex;
        } finally {

            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;

    }
 
    
    public boolean snExistDeduccion(int id_empleado, int id_deduccion) 
    {
        boolean flag = false;
        
        try {
             Connection con = dbConexcion.getConnection();
            st = con.createStatement();
            String sql = "select * from emp_movimiento where id_emp=" + id_empleado + " and id_mov=" + id_deduccion ; 
             this.rs = st.executeQuery(sql);

              while (rs.next())
              {
                flag = true;
              }
              
              con.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    
      return flag;
    }
       
   
    
}
