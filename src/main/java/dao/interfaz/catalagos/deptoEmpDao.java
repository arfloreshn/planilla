/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaz.catalagos;

import java.util.List;
import modelo.TdeptoEmp;

/**
 *
 * @author root
 */
public interface deptoEmpDao {
    
    public List<TdeptoEmp> lstdep();
    public boolean crearDepto(TdeptoEmp depto);
    public boolean editarDepto(TdeptoEmp depto);
    public boolean borrarDepto(Integer id);
    
    
}
