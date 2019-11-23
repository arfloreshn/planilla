/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planilla.controladores;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "exportaPlanillaCVSBean")
@Dependent
public class planillaexportaCVSBean  implements Serializable{

    /**
     * Creates a new instance of exportaPlanillaCVSBean
     */
    public planillaexportaCVSBean() {
    }
    
}
