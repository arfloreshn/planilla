/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.paisesDao;
import java.util.ArrayList;
import java.util.List;
import modelo.Tpais;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;

/**
 *
 * @author root
 */
public class paisesDaoImpl implements paisesDao {

    private Tpais pais;
    private List<Tpais> listar;

    private Session session;
    private Transaction trans;

    @Override
    public List<Tpais> lstPaises() {

        Session session = null;
        String sql = "FROM Tpais";
        Transaction tx = null;

        try {

            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            listar = session.createQuery(sql).list();
            listar.size();
            tx.commit();

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
    public boolean crearPais(Tpais pais) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.save(pais);
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
    public boolean editarPais(Tpais pais) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.update(pais);
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
    public boolean borrarPais(Integer id) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            Tpais pais = (Tpais) session.load(Tpais.class, id);
            session.delete(pais);
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
