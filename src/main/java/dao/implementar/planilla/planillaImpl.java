/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.planilla;

import utilerias.colecciones.planResTmpWkf;
import dao.implementar.empleados.empleadoDaoImpl;
import dao.interfaz.empleados.empleadoDao;
import dao.interfaz.planilla.planillaDao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.EmpMae;
import modelo.PlanDetWkf;
import modelo.PlanMaeWkf;
import modelo.PlanResWkf;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;
import utilerias.jodbc.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
public class planillaImpl implements planillaDao {

    private List<planResTmpWkf> lista = null;
    private ResultSet rs = null;
    private Statement st;
    private String sql = "";
    private Connection con = null;
    private empleadoDao emp = null;
    private boolean flag = false;
    private Session session = null;
    private Transaction tx = null;

    @Override
    public List<planResTmpWkf> ListarEmpleados() {

        try {
            this.con = dbConexcion.getConnection();
            st = con.createStatement();
            this.sql = "select sn_seleccionado, id_emp,cod_empleado"
                    + ", nombre, sueldo_bruto, debitos,credito,pagoneto"
                    + " from pla_vw_planResTmpWkf";
            this.rs = st.executeQuery(this.sql);

            this.lista = new ArrayList<planResTmpWkf>();
            this.lista.clear();
            while (rs.next()) {
                planResTmpWkf mplanResTmpWkf = new planResTmpWkf();

                mplanResTmpWkf.setPeriodo(9);
                mplanResTmpWkf.setAnio(2018);
                mplanResTmpWkf.setId_emp(rs.getInt("id_emp"));
                mplanResTmpWkf.setCodEmp(rs.getString("cod_empleado"));
                mplanResTmpWkf.setNombre(rs.getString("nombre"));
                mplanResTmpWkf.setImpSueldoBruto(rs.getDouble("sueldo_bruto"));
                mplanResTmpWkf.setImpDebitos(rs.getDouble("debitos"));
                mplanResTmpWkf.setImpCreditos(rs.getDouble("credito"));
                mplanResTmpWkf.setImpPagoNeto(rs.getDouble("pagoneto"));
                mplanResTmpWkf.setSn_seleccionado(rs.getBoolean("sn_seleccionado"));

                lista.add(mplanResTmpWkf);
            }

        } catch (Exception ex) {

            ex.getMessage();
        }

        return lista;
    }

    @Override
    public boolean PlanResumenPlanillaWkfcrear(PlanMaeWkf obj, List<PlanResWkf> ListObj) {
        this.session = HibernateUtil.getSession();
        this.flag = false;
        try {

            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
         
            session.save(obj); // Salvo el resumen maestro de la planilla

            /*
            //Voy a recorrer a los empleado y los voy a guardar
            Iterator<PlanResWkf> it = ListObj.iterator();
            while (it.hasNext()) {
                PlanResWkf tb = new PlanResWkf();
                tb = it.next();
                //session.save(tb);
            }
            */

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
        return flag;//To change body of generated methods, choose Tools | Templates.//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean PlanResumenEmpleadoWkf(PlanResWkf obj) {
        this.session = HibernateUtil.getSession();
        this.flag = false;

        try {
            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(obj); // Salvo el resumen por empleado de la planilla
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
        return flag;//To change body of generated methods, choose Tools | Templates.//To change b
    }

    @Override
    public boolean PlanDetalleEmpleadoWkf(int var_nro_planilla) {
        this.session = HibernateUtil.getSession();
        this.flag = false;
        try {
              
            Connection con = dbConexcion.getConnection();
            CallableStatement sp = con.prepareCall("call plan_det_wkf_ins(?)");
            sp.setInt("var_nro_planilla",var_nro_planilla);
            sp.execute();
            con.close();
            
            flag = true;
        } catch (SQLException e) {
             e.getMessage();
        }
        return flag;//To change body of generated methods, choose Tools | Templates.//To change b
    }
}
