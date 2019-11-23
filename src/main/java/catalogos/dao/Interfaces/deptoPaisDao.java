/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.TdeptoPais;

/**
 *
 * @author root
 */
public interface deptoPaisDao {
    
    public List<TdeptoPais> lstdepto(String id_pais);
    public boolean crearDepto(TdeptoPais obj);
    public boolean editarDepto(TdeptoPais obj);
    public boolean borrarDepto(String id);
    
    
}
