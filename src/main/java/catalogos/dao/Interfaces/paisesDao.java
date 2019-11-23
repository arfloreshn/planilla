/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.ArrayList;
import java.util.List;
import modelo.Tpais;

/**
 *
 * @author root
 */
public interface paisesDao {
    
    public List<Tpais> lstPaises();
    public boolean crearPais(Tpais pais);
    public boolean editarPais(Tpais pais);
    public boolean borrarPais(Integer id);
    
}
