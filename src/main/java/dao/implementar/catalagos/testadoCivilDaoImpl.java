/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.testadoCivilDao;
import java.util.List;
import modelo.EmpMae;
import modelo.TestadoCivil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;

/**
 *
 * @author AllanRamiro
 */
public class testadoCivilDaoImpl implements testadoCivilDao {

    private List<TestadoCivil> listar;

    @Override
    public List<TestadoCivil> lstEstadoCivil() {

        String sql = "FROM TestadoCivil";
        Session session = null;
        Transaction tx = null;

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
    public List<TestadoCivil> lstEstadoCivilXEmpleado(EmpMae obj) {
        Session session = HibernateUtil.getSession();

        String sql = "FROM TestadoCivil where id=" + obj.getTestadoCivil().getId();
        Transaction tx = null;

        try {

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
