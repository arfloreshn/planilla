/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.menu;

import java.util.List;
import dao.interfaz.menu.menuDao;
import modelo.Menu;
import org.hibernate.query.Query;
import org.hibernate.Session;
import utilerias.hibernate.HibernateUtil;

/**
 *
 * @author root
 */
public class menuDaoImpl implements menuDao {

    private List<Menu> lstItemMenu;
    
    @Override
    public List<Menu> ListarMenu() {
  
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Menu m order by m.id");
        List<Menu> lstItemMenu = query.list();
        return lstItemMenu;    
    }

    //@Override
    //public List<Menu> nOpiontsMenu() {
    //return lstItemMenu;  
  //  }

    @Override
    public Integer iOptionsMenu() {
       Integer imenus; 
       Session session = HibernateUtil.getSession();
       Query query = (Query) session.createQuery("SELECT count(idNivel) FROM Menu where idNivel=0").uniqueResult();
       imenus = query.getFirstResult();
       return imenus;
    }

    
    
}
