/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.empleados;

import dao.interfaz.empleados.empleadoDao;
import dao.implementar.catalagos.paisesDaoImpl;
import dao.interfaz.catalagos.paisesDao;
import dao.implementar.catalagos.profesionDaoImpl;
import dao.implementar.catalagos.municipioPaisDaoImpl;
import dao.interfaz.catalagos.municipioPaisDao;
import dao.interfaz.catalagos.profesionDao;
import dao.interfaz.catalagos.deptoPaisDao;
import dao.implementar.catalagos.deptoPaisDaoImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import modelo.TdeptoPais;
import modelo.EmpMae;
import modelo.Tmunicipios;
import modelo.Tpais;
import modelo.Tprofesion;
import modelo.TtipoMovimiento;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;
import utilerias.jodbc.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
public class empleadoDaoImpl implements empleadoDao {

    private List<EmpMae> listar;
    private List<Tpais> lstpais;
    private List<TdeptoPais> lstdeptos;
    private List<Tmunicipios> lstmunicipios;
    private List<Tprofesion> lstprofesion;
    
    private String id_pais;
    private String id_depto;
    
    private ResultSet rs = null;
    private Statement st;

    Connection conexion;

    @Override
    public List<EmpMae> lstEmpleado() {

        Session session = null;
        Transaction tx = null;

        String sql = "from EmpMae emp "
                + "left join fetch emp.tpais pais "
                + "left join fetch emp.tdeptoPais deppais"
                + "left join fetch emp.tmunicipios mun"
                + "left join fetch emp.tcargo cargo"
                + "left join fetch emp.tdeptoEmp depemp"
                + "left join fetch emp.tprofesion prof"
                + "left join fetch emp.testadoCivil testadociv"
                + "left join fetch emp.ttipoDocumento tipodoc";

        try {
            session = uh.getSessionFactory().openSession();

            tx = session.beginTransaction();
            listar = session.createQuery(sql).list();
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
    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public boolean crearEmpleado(EmpMae obj) {
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editarEmpleado(EmpMae obj) {
        boolean flag = false;
        Session session = null;
        Transaction tx = null;

        try {
            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;
    }

    @Override
    public boolean bajaEmpleado(EmpMae obj) {

        boolean flag = false;
        Session session = uh.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public EmpMae buscarEmpleadoXid(String codEmpleado) {
        Session session = null;
        Transaction tx = null;
        EmpMae obj = new EmpMae();
        try {
            session = uh.getSessionFactory().openSession();
            String sql = "FROM EmpMae WHERE codEmpleado='" + codEmpleado.trim()+ "'";
            tx = session.beginTransaction();
            obj = (EmpMae) session.createQuery(sql).uniqueResult();
            tx.commit();
        } 
        catch (Exception ex) {
            tx.rollback();
            throw ex;
        } 
        finally {

            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
    return obj;
    }

    @Override
    public List<Tpais> lstPais() {
        paisesDao pdao = new paisesDaoImpl();
        this.lstpais = pdao.lstPaises();
        return lstpais;
    }

    @Override
    public List<TdeptoPais> listarDeptos(EmpMae obj) {

        deptoPaisDao var = new deptoPaisDaoImpl();
        this.lstdeptos = var.lstdepto(String.valueOf(obj.getTpais().getId()).trim());
        return lstdeptos;
    }

    @Override
    public List<Tmunicipios> listarMunicipios(EmpMae obj) {
        this.id_pais = String.valueOf(obj.getTpais().getId()).trim();
        this.id_depto = String.valueOf(obj.getTdeptoPais().getId()).trim();
        municipioPaisDao var = new municipioPaisDaoImpl();
        this.lstmunicipios = var.lstmunicipios(this.id_pais, this.id_depto);
        return lstmunicipios;

    }

    @Override
    public int getNroEmpleado() throws Exception {

        int nro_empleado = 0;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://192.168.0.19:3306/planilla", "root", "Denver$8");
        String sSql = "";

        sSql = "SELECT fn_correlativo('empmae')";
        CallableStatement cs = conexion.prepareCall(sSql);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            nro_empleado = rs.getInt(1); // return value
        }
        cs.close();

        return nro_empleado;
    }

    public EmpMae getFoto(String codEmpleado) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        EmpMae obj = new EmpMae();
        try {
            String sql = "FROM EmpMae WHERE codEmpleado='" + codEmpleado.trim()+ "'";
            tx = session.beginTransaction();
            obj = (EmpMae) session.createQuery(sql).uniqueResult();
            tx.commit();
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
            throw ex;
        } 
        finally {

            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
    return obj;

    }

    @Override
    public List<Tprofesion> lstProfesion() {
        profesionDao pdao = new profesionDaoImpl();
        this.lstprofesion = pdao.lstprofesion();
        return lstprofesion;
    
    }
    
    
    @Override
    public List<String> getAniversariosDelMes(int mes) 
    {
       
        Session session = null;
        Transaction tx = null;

        String sql = "select emp.nombres, emp.primerApellido, emp.segundoApellido"
                  + " from EmpMae emp "
                  + " where month(emp.fechaNac)=" + mes;

        List<String> ls = new ArrayList<String>();
        try {
            session = uh.getSessionFactory().openSession();

            tx = session.beginTransaction();
            ls = session.createQuery(sql).list();
            tx.commit();

            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                ls = null;
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return ls;
    
    }

    
    @Override
    public List<EmpMae> getEmpleadoIngresados(int anio, int mes) 
    {
        
        this.listar = new ArrayList<EmpMae>();
        for(EmpMae obj:listar)
        {
          String nombres = obj.getNombres() + " " + obj.getPrimerApellido() + " " + obj.getSegundoApellido();
          Calendar fecha = new GregorianCalendar();
          fecha.setTime(obj.getFechaNac());
          if (fecha.get(Calendar.YEAR) == anio && fecha.get(Calendar.MONTH) == mes) 
          {
             listar.add(obj);
          }
        }
        return listar;
    }
    
    
    @Override
    public List<EmpMae> getAniversariosXNombres(String nombre) 
    {
  
     Session session = null;
        Transaction tx = null;
                String sql = "select emp.nombres, emp.primerApellido, emp.segundoApellido "
                + "from EmpMae emp "
                + " where emp.nombres like '%" + nombre + "%'";


        try {
            session = uh.getSessionFactory().openSession();

            tx = session.beginTransaction();
            listar = session.createQuery(sql).list();
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
    
    
}
