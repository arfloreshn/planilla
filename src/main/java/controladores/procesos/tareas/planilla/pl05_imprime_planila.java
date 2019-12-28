/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.procesos.tareas.planilla;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "imprimeplanillabean")
@Dependent
public class pl05_imprime_planila implements Serializable{

    /**
     * Creates a new instance of imprimeplanillabean
     */
    public pl05_imprime_planila() {
    }
    
}
