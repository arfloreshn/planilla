/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.catalagos;

import java.util.List;
import modelo.Tcargo;

/**
 *
 * @author root
 */
public interface cargosDao {
    
   public List<Tcargo> lstcargos();
   public boolean crearCargo(Tcargo cargo);
   public boolean editarCargo(Tcargo cargo);
   public boolean borrarCargo(Integer id);
   public List<Tcargo> listarCargoXId(int id);
}
