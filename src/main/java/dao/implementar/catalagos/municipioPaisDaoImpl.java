/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.municipioPaisDao;
import java.util.List;
import modelo.Tmunicipios;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;

/**
 *
 * @author AllanRamiro
 */
public class municipioPaisDaoImpl implements municipioPaisDao {

    private List<Tmunicipios> listar;

    private Session session;
    private Transaction trans;

    @Override
    public List<Tmunicipios> lstmunicipios(String id_pais, String id_depto) {
     
        session = uh.getSessionFactory().openSession();

        
        String sql = "FROM Tmunicipios where idPais=" + id_pais + " and id_depto_pais=" + id_depto.trim();
        
        Transaction tx = null;

        try {

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
    public boolean crearmunicipio(Tmunicipios obj) {

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
    public boolean editarmunicipio(Tmunicipios obj) {
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
    public boolean borrarmunicipio(String cod_municipio) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            Tmunicipios obj = (Tmunicipios) session.load(Tmunicipios.class, cod_municipio);
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

}
