/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.Tmunicipios;

/**
 *
 * @author AllanRamiro
 */
public interface municipioPaisDao {
    public List<Tmunicipios> lstmunicipios(String id_pais, String id_depto);
    public boolean crearmunicipio(Tmunicipios obj);
    public boolean editarmunicipio(Tmunicipios obj);
    public boolean borrarmunicipio(String id);
    
}
