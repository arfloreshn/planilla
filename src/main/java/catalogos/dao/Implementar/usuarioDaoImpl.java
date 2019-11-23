/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Implementar;

import catalogos.dao.Interfaces.usuarioDao;
import modelo.Usuario;
import org.hibernate.HibernateException;
import utilerias.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import modelo.Rol;

/**
 *
 * @author root
 */
public class usuarioDaoImpl implements usuarioDao {

        private Session session;
	private Transaction trans;
	private List<Usuario> lstUsuarios;
        private List<Rol> lstRoles;

    
    @Override
    public Usuario validarlogin(Usuario usuario) {

        Usuario model = this.buscarUsuario(usuario);

        // Si no hay data en el resulset
        if (model != null) {
            if (!usuario.getClave().equals(model.getClave())) {
                model = null;
            }
        }
        return model;
    }

    /*
    Fecha: 20180825
    
    Este metodo recupera unicamente el valor que estamos buscando
    
    El metodo HibernateUtil.getSessionFactory().getCurrentSession(), se toma del paquete util de hibernate;
    La inicializacion de la variable Usuario rs = null, es igual a crear un resulset en vb60;

    El Usuario en mayuscula (Usuario) es la clase del modelo, que representa al tabla de la base de datos
     */
    @Override
    public Usuario buscarUsuario(Usuario usuario) {

        Usuario model = null;

        Session session = HibernateUtil.getSession();
      
        String sql = "FROM Usuario WHERE usuario='" + usuario.getUsuario() + "'";
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            model = (Usuario) session.createQuery(sql).uniqueResult();
            tx.commit();
        
            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                model = null;
            }
            e.printStackTrace();
        } 
        
        finally {
            session.close();
        }

        return model;
    }

    

      
        @Override
	public List<Usuario> Listar() {

        Session session = HibernateUtil.getSession();
      
        String sql = "FROM Usuario";
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            lstUsuarios= session.createQuery(sql).list();
            tx.commit();
        
            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                lstUsuarios= null;
            }
            e.printStackTrace();
        } 
        
        finally {
            session.close();
        }

          
            return lstUsuarios;
	}

    @Override
    public boolean crearUsuario(Usuario usuario) {
      boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        session.save(usuario);
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
    public boolean editarUsuario(Usuario usuario) 
    {
      boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        session.update(usuario);
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
    public boolean borrarUsuario(Integer id) {
         boolean flag = false;
        try {
			session = HibernateUtil.getSession();
			trans = session.beginTransaction();
                        Usuario usuario = (Usuario) session.load(Usuario.class, id);
                        session.delete(usuario);
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
	public List<Rol> ListarRoles() {

        Session session = HibernateUtil.getSession();
      
        String sql = "FROM Rol";
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            lstRoles= session.createQuery(sql).list();
            tx.commit();
        
            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                lstRoles= null;
            }
            e.printStackTrace();
        } 
        
        finally {
            session.close();
        }

          
            return lstRoles;
	}

}
