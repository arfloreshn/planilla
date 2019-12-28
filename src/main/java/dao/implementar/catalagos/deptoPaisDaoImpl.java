/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.deptoPaisDao;
import java.util.List;
import modelo.TdeptoPais;
import modelo.Tpais;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilerias.funciones.msgbox;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;

/**
 *
 * @author root
 */
public class deptoPaisDaoImpl implements deptoPaisDao {

   private TdeptoPais depto;
   private Tpais pais;
   private List<TdeptoPais> listar;
    
    private Session session;
    private Transaction trans;

    @Override
    public List<TdeptoPais> lstdepto(String id_pais) {
        
        session = uh.getSessionFactory().openSession();

        String sql = "FROM TdeptoPais WHERE id_pais=" + id_pais.trim();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            listar = session.createQuery(sql).list();
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
    public boolean crearDepto(TdeptoPais obj) {

             
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
    public boolean editarDepto(TdeptoPais obj) {
 
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
    public boolean borrarDepto(int id_pais, int id_depto) {
  
      boolean flag = false;
        session = uh.getSessionFactory().openSession();

        Transaction tx = null;

        try {

            Tpais objPais = new Tpais();
            objPais.setId(id_pais);
            
            TdeptoPais objDepto = new TdeptoPais();
            objDepto.setTpais(objPais);
            objDepto.setId(id_depto);
            tx = session.beginTransaction();
            session.delete(objDepto);
            tx.commit();
            flag = true;
    
           
        } catch (HibernateException e) {
            if (tx != null) {
                flag = false;
                msgbox.vbError(e.getMessage());
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

      
      return flag;
    }
    
}
