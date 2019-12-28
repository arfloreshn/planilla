/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementar.catalagos;

import dao.interfaz.catalagos.tctasBcoDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.TbancoCtas;
import modelo.TtipoMovimiento;
import utilerias.jodbc.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
public class tctasbcoDaoImpl implements tctasBcoDao {

    private ResultSet rs = null;
    private Statement st;
      private List<TbancoCtas> listar = null;
    @Override
    public List<TbancoCtas> lstCuentasBco() {
  
      try {
             this.listar  = new ArrayList<TbancoCtas>(); // Es obligatorio reinicializar las 
             Connection con = dbConexcion.getConnection();
            st = con.createStatement();
              String sql = "select a.id, a.nro_cta_bco from tbanco_ctas a where a.sn_activa='S' and a.sn_paga_planilla='S'"; 

            this.rs = st.executeQuery(sql);
            
            this.listar.clear();
            while (rs.next()) {
                TbancoCtas tb = new TbancoCtas();
                tb.setNroCtaBco(rs.getString("nro_cta_bco"));
                tb.setId(rs.getInt("id"));
                this.listar.add(tb);
            }
            con.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    
        return this.listar;
    }
    
}
