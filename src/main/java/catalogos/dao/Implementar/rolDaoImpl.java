/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Implementar;

import catalogos.dao.Interfaces.rolDao;
import java.util.List;
import modelo.Rol;
import modelo.TestadosApp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.hibernate.HibernateUtil;

/**
 *
 * @author root
 */
public class rolDaoImpl implements rolDao {

     private Rol rol;
     private List<Rol> listar;
     private List<TestadosApp> lstEstados;
     
     private Session session;
     private Transaction trans;

     
    @Override
    public List<Rol> lstrol() {
    Session session = HibernateUtil.getSession();
      
        String sql = "FROM Rol";
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            listar= session.createQuery(sql).list();
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
    public boolean crearRol(Rol rol) {
      boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        session.save(rol);
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
    public boolean editarRol(Rol rol) {
   boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        session.update(rol);
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
    public boolean borrarRol(Integer id) {
      boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        Rol rol = (Rol) session.load(Rol.class, id);
                        session.delete(rol);
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

    @Override
    public List<TestadosApp> lstEstado() {

        Session session = HibernateUtil.getSession();
      
        String sql = "FROM TestadosApp";
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            lstEstados= session.createQuery(sql).list();
            tx.commit();
        
            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                lstEstados= null;
            }
            e.printStackTrace();
        } 
        
        finally {
            session.close();
        }

          
            return lstEstados;
    }

   
    
}
