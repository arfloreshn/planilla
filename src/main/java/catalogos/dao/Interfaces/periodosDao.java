/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.Tmeses;

/**
 *
 * @author AllanRamiro
 */
public interface periodosDao {
     public List<Tmeses> lstmeses();
     public int mesFiscal();
     public int  anioFiscal();
}