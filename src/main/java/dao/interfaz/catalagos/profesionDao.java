/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.catalagos;

import java.util.List;
import modelo.Tprofesion;

/**
 *
 * @author root
 */
public interface profesionDao {
   public List<Tprofesion> lstprofesion();
   public boolean crearProfesion(Tprofesion profesion);
   public boolean editarProfesion(Tprofesion profesion);
   public boolean borrarProfesiono(Integer id);
    
}
