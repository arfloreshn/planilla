/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.cargosDao;
import java.util.List;
import modelo.Tcargo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;
import utilerias.hibernate.uh;

/**
 *
 * @author root
 */
public class cargosDaoImpl implements cargosDao {

    
     private Tcargo cargo;
     private List<Tcargo> listar;
     
     private Session session;
     private Transaction trans;

    @Override
    public List<Tcargo> lstcargos() {
  
        String sql = "from Tcargo";
        Transaction tx = null;

        session = null;
        try {

            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            listar= session.createQuery(sql).list();
            listar.size();
            tx.commit();
        
            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                listar= null;
            }
            e.printStackTrace();
        } 
        
        finally {
            session.close();
        }

          
            return listar;
    
    }


    @Override
    public List<Tcargo> listarCargoXId(int id) {
  
        String sql = "FROM Tcargo where id=" + id;
        Transaction tx = null;

        session = null;
        try {

            session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            listar= session.createQuery(sql).list();
            listar.size();
            tx.commit();
        
            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                listar= null;
            }
            e.printStackTrace();
        } 
        
        finally {
            session.close();
        }

          
            return listar;
    
    }


    @Override
    public boolean crearCargo(Tcargo cargo) {

      boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        session.save(cargo);
                        trans.commit();
                        flag = true;
		} catch (Exception ex) {
                        System.out.println(ex.getMessage());
			trans.rollback();
			throw ex;
		} finally {
                  // Si la session esta abierta la cierro
                    if (session!= null) 
                    {
                        session.close();
                    }

        }
        return flag;

    }

    @Override
    public boolean editarCargo(Tcargo cargo) {
    boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        session.update(cargo);
                        trans.commit();
                        flag = true;
		} catch (Exception ex) {
                        System.out.println(ex.getMessage());
			trans.rollback();
			throw ex;
		} finally {
                  // Si la session esta abierta la cierro
                    if (session!= null) 
                    {
                        session.close();
                    }

        }
        return flag;
   }

    @Override
    public boolean borrarCargo(Integer id) {
     boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        Tcargo cargo = (Tcargo) session.load(Tcargo.class, id);
                        session.delete(cargo);
			trans.commit();
                        flag=true;
		} catch (Exception ex) {
                        System.out.println(ex.getMessage());
			trans.rollback();
                        throw ex;
		} finally {
	
                  // Si la session esta abierta la cierro
                 if (session!= null) 
                    {
                        session.close();
                    }

        }
        return flag;
    
    
    }
    
}
