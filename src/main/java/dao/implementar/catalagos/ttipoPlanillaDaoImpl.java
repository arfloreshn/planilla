/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.ttipoPlanillaDao;
import java.util.List;
import modelo.TtipoPlanilla;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.uh;

/**
 *
 * @author AllanRamiro
 */
public class ttipoPlanillaDaoImpl implements ttipoPlanillaDao{

    private List<TtipoPlanilla> listar;
   
    @Override
    public List<TtipoPlanilla> lstTipoPlanilla() {
        Session session = null;
        String sql = "FROM TtipoPlanilla";
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
