/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.menu;

import java.util.List;
import modelo.Menu;

/**
 *
 * @author root
 */
public interface menuDao {
    
    public List<Menu> ListarMenu();
    public Integer iOptionsMenu();
    
}
