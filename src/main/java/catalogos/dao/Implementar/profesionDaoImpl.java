/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Implementar;

import catalogos.dao.Interfaces.profesionDao;
import java.util.List;
import modelo.Tprofesion;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;

/**
 *
 * @author root
 */
public class profesionDaoImpl implements profesionDao {

    private Tprofesion profesion;
    private List<Tprofesion> listar;

    private Session session;
    private Transaction trans;

    @Override
    public List<Tprofesion> lstprofesion() {
        Session session = null;
        String sql = "FROM Tprofesion";
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
    public boolean crearProfesion(Tprofesion profesion) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.save(profesion);
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
    public boolean editarProfesion(Tprofesion profesion) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.update(profesion);
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
    public boolean borrarProfesiono(Integer id) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            Tprofesion profesion = (Tprofesion) session.load(Tprofesion.class, id);
            session.delete(profesion);
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

}
