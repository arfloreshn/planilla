/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.ttipoDocEmpDao;
import java.util.List;
import modelo.EmpMae;
import modelo.TtipoDocumento;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;

/**
 *
 * @author AllanRamiro
 */
public class ttipoDocEmpDaoImpl implements ttipoDocEmpDao {

    private List<TtipoDocumento> listar;

    @Override
    public List<TtipoDocumento> lstTipoDoc() {
        Session session = null;
        String sql = "FROM TtipoDocumento";
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
    public List<TtipoDocumento> listTipoDocXEmpleado(EmpMae obj) {
        Session session = HibernateUtil.getSession();

        String sql = "FROM TtipoDocumento where id=" + obj.getTtipoDocumento().getId();
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
