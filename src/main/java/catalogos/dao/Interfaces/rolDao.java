/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.Rol;
import modelo.TestadosApp;

/**
 *
 * @author root
 */
public interface rolDao {
    
   public List<Rol> lstrol();
   public List<TestadosApp> lstEstado();
   public boolean crearRol(Rol rol);
   public boolean editarRol(Rol rol);
   public boolean borrarRol(Integer id);
}
