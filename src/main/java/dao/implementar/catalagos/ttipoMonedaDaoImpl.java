/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.ttipoMonedaDao;
import java.util.List;
import modelo.Tmoneda;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.uh;

/**
 *
 * @author AllanRamiro
 */
public class ttipoMonedaDaoImpl implements ttipoMonedaDao{

    private List<Tmoneda> listar;
   
    @Override
    public List<Tmoneda> lstMonedas() {
         Session session = null;
        String sql = "FROM Tmoneda";
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
    
}
