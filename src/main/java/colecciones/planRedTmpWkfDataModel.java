/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecciones;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author AllanRamiro
 */
public class planRedTmpWkfDataModel extends ListDataModel<planResTmpWkf> implements SelectableDataModel<planResTmpWkf>
{
    public planRedTmpWkfDataModel(List<planResTmpWkf> data) {
        super(data);
    }

    @Override
    public planResTmpWkf getRowData(String rowKey) {
        List<planResTmpWkf> obj = (List<planResTmpWkf>) getWrappedData();

        for(planResTmpWkf pla : obj) {
            if(pla.getCodEmp().equals(rowKey))
                return pla;
        }
        return null;
    }

    @Override
    public Object getRowKey(planResTmpWkf pla) {
        return pla.getCodEmp();
    }
    
}
