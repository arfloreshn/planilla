package modelo;
// Generated 05-13-2019 07:40:13 AM by Hibernate Tools 4.3.1



/**
 * Tcorrelativo generated by hbm2java
 */
public class Tcorrelativos  implements java.io.Serializable {


     private String tabla;
     private short ultNro;

    public Tcorrelativos() {
    }

    public Tcorrelativos(String tabla, short ultNro) {
       this.tabla = tabla;
       this.ultNro = ultNro;
    }
   
    public String getTabla() {
        return this.tabla;
    }
    
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    public short getUltNro() {
        return this.ultNro;
    }
    
    public void setUltNro(short ultNro) {
        this.ultNro = ultNro;
    }




}


