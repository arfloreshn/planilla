/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.convertidores;

/**
 *
 * @author AllanRamiro
 */

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.TtipoMovimiento;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "TtipoMovientoConvierte")
public class TtipoMovientoConvierte implements Converter {

 private DualListModel<TtipoMovimiento> data; 
    

    private List<TtipoMovimiento> lst = null;
      
     
    @Override
    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
    
       // Se valida el obj que estamos conviertiendo de la pagina web, en este caso un PICKLIST
        return getObtenerComponente(component,value);
    }


    @Override
    public String getAsString(final FacesContext fc, final UIComponent component, final Object value) {
        
        String mId = String.valueOf(((TtipoMovimiento) value).getid());
        return mId;
    }
    
    
    private TtipoMovimiento getObtenerComponente(UIComponent component, String value) 
    {
       final DualListModel<TtipoMovimiento> data;
       data = ( DualListModel<TtipoMovimiento>) ((PickList) component).getValue(); 
       TtipoMovimiento tbTabla = getObtenerLista(data.getSource(), Integer.valueOf(value));
       if(tbTabla == null) 
       {
          tbTabla = getObtenerLista(data.getTarget(), Integer.valueOf(value));
       }
       return tbTabla;
    }
    
    
    private TtipoMovimiento getObtenerLista(final List<?> list, final Integer id) 
    {
    
        for (final  Object object:list)
        {
           final TtipoMovimiento tbTabla = (TtipoMovimiento) object;
           if (tbTabla.getid().equals(id)) 
           {
              return tbTabla;
           }
        }
        return null;
    }
    
 }
