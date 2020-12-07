/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.procesos.tareas.empleados;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import modelo.Tcargo;
import modelo.TdeptoEmp;
import modelo.TdeptoPais;
import modelo.EmpMae;
import modelo.TestadoCivil;
import modelo.Tmunicipios;
import modelo.Tpais;
import modelo.Tprofesion;
import modelo.TtipoDocumento;
import modelo.TtipoEmpleado;
import utilerias.hibernate.uh;
import utilerias.funciones.msgbox;

import dao.interfaz.catalagos.cargosDao;
import dao.implementar.catalagos.cargosDaoImpl;
import dao.interfaz.catalagos.deptoEmpDao;
import dao.implementar.catalagos.deptoEmpDaoImpl;
import dao.interfaz.catalagos.testadoCivilDao;
import dao.implementar.catalagos.testadoCivilDaoImpl;
import dao.interfaz.catalagos.ttipoDocEmpDao;
import dao.implementar.catalagos.ttipoDocEmpDaoImpl;
import dao.interfaz.catalagos.ttipoEmpleadoDao;
import dao.implementar.catalagos.ttipoEmpleadoDaoImpl;
import dao.interfaz.empleados.empleadoDao;
import dao.implementar.empleados.empleadoDaoImpl;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import utilerias.System.clsSistemaImpl;
import static utilerias.funciones.funciones.PathImg;
import utilerias.System.clsSistema;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "empleadosBean")
@ViewScoped
public class empleados_controller implements Serializable {

    private static final long serialVersionUID = 1L;

    private EmpMae empleados;
    private List<EmpMae> lstEmpleados;
    private List<SelectItem> ListaCargos;
    private List<SelectItem> ListaUbicacion;
    private List<SelectItem> ListaProfesion;
    private List<SelectItem> ListarPais;
    private List<SelectItem> ListaDeptos;
    private List<SelectItem> ListaMunicipios;
    private List<SelectItem> ListaEstadocivil;
    private List<SelectItem> ListaTipoDocumento;
    private List<SelectItem> ListaTipoEmpleado;
    private clsSistema objFecha = new clsSistemaImpl();
    private int var_nroEmpleado;

    private String msg;
    private String codEmpleado;
    private String destination = "images/";
    private String foto;
    private String pathImagen = null;

    private String TipoAccion = null;
    private String idpais = null;
    private String iddepto = null;
    private String Sexo = "";

    private int id_deptopais;
    private int id_municipio;
    private byte[] bByte;
    private boolean sn_nuevo = false;

    /*
     VARIABLES PARA SUBIR ARCHIVO
     */
    private UploadedFile file;

    Session session;
    Transaction tx;

    ServletContext servletContext = null;

    empleadoDao emp = null;

    private boolean rsp;

    /**
     * Creates a new instance of empleadosBean
     */
    public empleados_controller() {

        rsp = FacesContext.getCurrentInstance().isPostback();
        if (!rsp) {

            inicializarBean();
        }

    }

    private void inicializarBean() {
        this.TipoAccion = "U";
        this.foto = "avatar.png";
        this.Sexo = "";
        servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        this.id_municipio = 0;
        this.id_deptopais = 0;
        this.emp = new empleadoDaoImpl();
        this.empleados = new EmpMae();
        this.idpais = "";
        this.iddepto = "";
        this.sn_nuevo = false;
   }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public int getVar_nroEmpleado() {
        return var_nroEmpleado;
    }

    public void setVar_nroEmpleado(int var_nroEmpleado) {
        this.var_nroEmpleado = var_nroEmpleado;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipoAccion() {
        return TipoAccion;
    }

    public void setTipoAccion(String TipoAccion) {
        this.TipoAccion = TipoAccion;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public EmpMae getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(EmpMae templeados) {
        this.empleados = templeados;

    }

    public List<EmpMae> getLstEmpleados() {

        this.lstEmpleados = this.emp.lstEmpleado();
        return lstEmpleados;
    }

    public void nuevoregistro() {
        this.sn_nuevo = true;
        btnHabilitar();
        this.TipoAccion = "N";
        this.foto = "avatar.png";
        this.bByte = null;
        this.empleados = new EmpMae();
        this.pathImagen = (String) this.servletContext.getRealPath("/resources/images/");
        this.destination = (String) this.servletContext.getRealPath("/resources/images/" + this.foto);
        this.empleados = new EmpMae();
        this.id_deptopais = 0;
        this.id_municipio = 0;

    }

    public void limpiarforma() {
        btnInhabilitar();
        this.TipoAccion = "U";
        this.foto = "avatar.png";
        this.bByte = null;
        this.empleados = new EmpMae();
        this.pathImagen = (String) this.servletContext.getRealPath("/resources/images/");
        this.destination = (String) this.servletContext.getRealPath("/resources/images/" + this.foto);
        this.empleados = new EmpMae();
        this.id_deptopais = 0;
        this.id_municipio = 0;

    }

    public void btnCrearEmpleado(ActionEvent actionevent) {
        this.msg = "";
        limpiarforma();
    }

    public void btnAccion() {
        this.msg = "";

        switch (this.TipoAccion) {
            // declaración case
            // los valores deben ser del mismo tipo de la expresión
            case "N":
                GuardarRegistro();
                break;
            case "U":

                ModificarRegistro();
                break;
            default:
            // Declaraciones
        }

        limpiarforma();

    }

    private void GuardarRegistro() {

        try {
            obtenerNroEmpleado();
            if (this.Sexo.equals("M")) {
                this.empleados.setSexo('M');
            } else {
                this.empleados.setSexo('F');
            }

            this.empleados.setCreadoPor("arflores");
            this.empleados.setCodEmpleado(String.valueOf(this.var_nroEmpleado).trim());
            this.empleados.setFoto(this.bByte);
            if (this.emp.crearEmpleado(this.empleados) == true) {
                msg = "Información registrada";
                msgbox.vbInfo(msg);
                limpiarforma();
            }
        } catch (Exception e) {
            msg = "Fallo en transacción" + e.getCause().getMessage();
            msgbox.vbError(msg);

        }

    }

    private void ModificarRegistro() {

        try {
            this.empleados.setModificadoPor("arflores");

            if (this.Sexo.equals("F")) {
                this.empleados.setSexo('F');
            } else {
                this.empleados.setSexo('M');
            }

            if (this.bByte != null) {
                this.empleados.setFoto(this.bByte);
            }

            if (this.emp.editarEmpleado(this.empleados)) {
                msg = "Información actualizada";
                msgbox.vbInfo(msg);
                limpiarforma();
            }

        } catch (Exception e) {
            msg = "Fallo en transacción";
            msgbox.vbError(msg);
        }

    }

    public void btnbajaEmpleado(ActionEvent actionevent) {
        this.msg = "";
        this.empleados.setFecBaja(objFecha.getFechaHoy());

        if (this.emp.bajaEmpleado(this.empleados)) {
            msg = "Empleado dado de baja";
            msgbox.vbInfo(msg);

        } else {
            msg = "Fallo en transacción";
            msgbox.vbError(msg);
        }
        limpiarforma();

    }

    public void buscarEmpleado() {

        if (this.empleados.getCodEmpleado() == null && this.empleados.getCodEmpleado().length() <= 0) {
            this.empleados = new EmpMae();
            return;
        } else {

            btnHabilitar();
            this.TipoAccion = "U";

            this.codEmpleado = this.empleados.getCodEmpleado();
            this.empleados = this.emp.buscarEmpleadoXid(this.codEmpleado);

            if (this.empleados == null) {
                msgbox.vbPrecaucion("El codigo de empleado, no existe");
                 this.empleados = new EmpMae();
                 nuevoregistro();
                 return;
            } else {

                if (String.valueOf(this.empleados.getSexo()).equals('F')) {
                    this.Sexo = "F";
                } else {
                    this.Sexo = "M";
                }

                this.idpais = this.empleados.getTpais().getId().toString();
                this.id_municipio = this.empleados.getTmunicipios().getId();
                this.id_deptopais = this.empleados.getTdeptoPais().getId();

                ObtenerImagen();
            }

        }

    }

    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void btnHabilitar() {
        enable = true;
        if (this.sn_nuevo != true) {
            this.TipoAccion = "U";
        }
    }

    public void btnInhabilitar() {
        enable = false;
    }

    public int getId_deptopais() {
        return id_deptopais;
    }

    public void setId_deptopais(int id_deptopais) {
        this.id_deptopais = id_deptopais;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    /*
     DEVUELVE EL CBO DEL TIPO DE DOCUMENTO EMPLEADO
     */
    public List<SelectItem> getListaTipoDocumento() {

        this.ListaTipoDocumento = new ArrayList<SelectItem>();
        ttipoDocEmpDao objDao = new ttipoDocEmpDaoImpl();
        List<TtipoDocumento> doc = objDao.lstTipoDoc();

        ListaTipoDocumento.clear();

        for (TtipoDocumento obj : doc) {
            SelectItem item = new SelectItem(obj.getId(), obj.getDescripcion());
            this.ListaTipoDocumento.add(item);
        }
        return ListaTipoDocumento;
    }

    /*
     DEVUELVE EL CBO DE CARGO DEL EMPLEADO
     */
    public List<SelectItem> getListaCargos() {

        this.ListaCargos = new ArrayList<SelectItem>();
        cargosDao objDao = new cargosDaoImpl();
        List<Tcargo> r = objDao.lstcargos();

        ListaCargos.clear();

        for (Tcargo obj : r) {
            SelectItem item = new SelectItem(obj.getId(), obj.getNomCargo());
            this.ListaCargos.add(item);
        }
        return ListaCargos;
    }

    /*
     DEVUELVE EL CBO DE UBICACION DEL DEPARTAMENTOE DEL EMPLEADO
     */
    public List<SelectItem> getListaUbicacion() {

        this.ListaUbicacion = new ArrayList<SelectItem>();
        deptoEmpDao objDao = new deptoEmpDaoImpl();
        List<TdeptoEmp> r = objDao.lstdep();
        this.ListaUbicacion.clear();
        for (TdeptoEmp obj : r) {
            SelectItem item = new SelectItem(obj.getId(), obj.getNomDepto());
            this.ListaUbicacion.add(item);
        }

        return ListaUbicacion;
    }

    /*
     DEVUELVE EL CBO DE PROFESION DEL EMPLEADO
     */
    public List<SelectItem> getListaProfesion() {

        this.ListaProfesion = new ArrayList<SelectItem>();
        List<Tprofesion> r = this.emp.lstProfesion();
        ListaProfesion.clear();
        for (Tprofesion obj : r) {
            SelectItem item = new SelectItem(obj.getId(), obj.getNomProfesion());
            this.ListaProfesion.add(item);
        }
        return ListaProfesion;
    }

    /*
     DEVUELVE EL CBO DEL PAIS DEL EMPLEADO
     */
    public List<SelectItem> getListarPais() {

        this.ListarPais = new ArrayList<SelectItem>();
        List<Tpais> r = this.emp.lstPais();
        this.ListarPais.clear();
        for (Tpais obj : r) {
            SelectItem item = new SelectItem(obj.getId(), obj.getNomPais());
            this.ListarPais.add(item);
        }

        return ListarPais;
    }

    /*
     DEVUELVE EL CBO EL DEPARTAMENTE DEL EMPLEADO
     */
    public List<SelectItem> getListaDeptos() {

        if (this.empleados.getTpais().getId() != null) {
            idpais = String.valueOf(this.empleados.getTpais().getId());
        }
        List<TdeptoPais> d = this.emp.listarDeptos(this.empleados);
        this.ListaDeptos = new ArrayList<SelectItem>();
        this.ListaDeptos.clear();
        for (TdeptoPais obj : d) {
            SelectItem item = new SelectItem(obj.getId(), obj.getNomDepto());
            this.ListaDeptos.add(item);
        }

        return ListaDeptos;
    }

    /*
     DEVUELVE EL CBO EL DEPARTAMENTE DEL EMPLEADO
     */
    public List<SelectItem> getListaMunicipios() {

        iddepto = String.valueOf(this.empleados.getTdeptoPais().getId());

        if (this.empleados.getCodEmpleado() != null) {
            idpais = String.valueOf(this.empleados.getTpais().getId());
            iddepto = String.valueOf(this.id_deptopais);
            this.empleados.getTdeptoPais().setId(this.id_deptopais);

        }

        this.ListaMunicipios = new ArrayList<SelectItem>();
        List<Tmunicipios> r = this.emp.listarMunicipios(this.empleados);
        for (Tmunicipios obj : r) {
            SelectItem item = new SelectItem(obj.getId(), obj.getNomMuni(), obj.getCodMunicipio());
            this.ListaMunicipios.add(item);
        }

        return ListaMunicipios;
    }

    /*
     DEVUELVE EL CBO DEL ESTADO CIVIL DEL EMPLEADO
     */
    public List<SelectItem> getListaEstadocivil() {

        this.ListaEstadocivil = new ArrayList<SelectItem>();
        testadoCivilDao objDao = new testadoCivilDaoImpl();
        List<TestadoCivil> r = objDao.lstEstadoCivil();

        ListaEstadocivil.clear();

        for (TestadoCivil obj : r) {
            SelectItem item = new SelectItem(obj.getId(), obj.getDescripcion());
            this.ListaEstadocivil.add(item);
        }
        return ListaEstadocivil;
    }

    /*
     DEVUELVE EL CBO DEL TIPO DE EMPLEADO
     */
    public List<SelectItem> getListaTipoEmpleado() {

        this.ListaTipoEmpleado = new ArrayList<SelectItem>();
        ttipoEmpleadoDao objDao = new ttipoEmpleadoDaoImpl();
        List<TtipoEmpleado> doc = objDao.lstTipoEmpleado();

        ListaTipoEmpleado.clear();

        for (TtipoEmpleado obj : doc) {
            SelectItem item = new SelectItem(obj.getId(), obj.getDescripcion());
            this.ListaTipoEmpleado.add(item);
        }
        return ListaTipoEmpleado;
    }

    private void obtenerNroEmpleado() {

        try {
            // Convertir a long el resultado de la consulta

            this.session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();

            this.var_nroEmpleado = this.emp.getNroEmpleado();
            this.empleados.setCodEmpleado(Integer.toString(this.var_nroEmpleado).trim());
            tx.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            //Cancelo los cambios en caso de error
            this.tx.rollback();
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
    }


    /*
    
    ESTE METODO SE DISPARA CUANDO SE SUBE LA FOTO DESDE LA PAGINA WEB
    EN LA PAGINA WEB SE INVOCA DESDE EL ACTIONLISTENER
    OJO, EL PARAMETRO (EVENT) NO SE USA EN LA PAGINA WEB 
     */
    public void subirfoto(FileUploadEvent event) {
        if (event.getFile().getFileName() != null) {

            try {
                this.foto = event.getFile().getFileName();
                copyFile(this.foto, event.getFile().getInputstream());
                this.foto = event.getFile().getFileName();

                //ObtenerImagen();
            } catch (IOException ex) {
                Logger.getLogger(empleados_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            msgbox.vbError("Foto Fallida ->" + event.getFile().getFileName() + " Fallo");
        }
    }

    /*
      COPIO EL ARCHIVO AL DIRECTORIO DE LA APLICACION
      (String) this.servletContext.getRealPath("/resources/images/" + fileName);
     */
    private void copyFile(String fileName, InputStream in) {

        try {

            // AQUI ES DONDE, VOY A GUARDAR LA FOTO
            this.destination = (String) this.servletContext.getRealPath("/resources/images/" + fileName);
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(this.destination));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                buffer.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            buffer.flush();
            this.bByte = buffer.toByteArray();
            out.close();

            buffer.close();
            msgbox.vbInfo("Fotografía almacenada correctamente");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ObtenerImagen() {

        try {

            this.codEmpleado = this.empleados.getCodEmpleado();
            // OBTENGO LA FOTO DESDE LA TABLA DE LA BASE DE DATOS, E INICIALIZO UN NUEVO OBJETO
            // CON LOS DATOS DEL EMPLEADO
            //obj = this.emp.getFoto(this.codEmpleado);

            // AQUI ES DONDE, VOY A GUARDAR LA 
            FacesContext facesContext = FacesContext.getCurrentInstance();

            // Se instancia servlet para determinar la ruta real de la Aplicacion
            ServletContext PathApp = (ServletContext) facesContext.getExternalContext().getContext();

            String NameFile = this.codEmpleado + ".jpg";
            this.pathImagen = (String) PathApp.getRealPath(PathImg());
            this.destination = this.pathImagen + "\\" + NameFile;

            File fichero = new File(this.destination);
            if (fichero.exists()) {
                fichero.delete();
            }

            byte[] bytes = this.empleados.getFoto();
            this.bByte = bytes;

            // CREO EL ARCHIVO CON CODIGO DEL EMPLEADO  
            FileOutputStream outputStream = new FileOutputStream(this.destination);
            outputStream.write(this.bByte);

            // ESCRIBO EL ARCHIVO CREADO CON EL CAMPO DE IMAGEN DE LA BASE DE DATOS.
            outputStream.close();

            // LE MANDO EL NOMBRE DEL ARCHIVO CREADO A LA FOTO
            this.foto = this.codEmpleado.trim() + ".jpg";
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void oncboEstadoCivil(ValueChangeEvent e) {

        this.empleados.getTestadoCivil().setId(Integer.valueOf(e.getNewValue().toString().trim()));
        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTestadoCivil().setId(itemSelect);

        }

    }

    public void oncboProfesion(ValueChangeEvent e) {

        this.empleados.getTprofesion().setId(Integer.valueOf(e.getNewValue().toString().trim()));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTprofesion().setId(itemSelect);

        }

    }

    public void oncboCargo(ValueChangeEvent e) {

        this.empleados.getTcargo().setId(Integer.valueOf(e.getNewValue().toString().trim()));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTcargo().setId(itemSelect);
        }

    }

    public void oncboPais(ValueChangeEvent e) {
        this.idpais = e.getNewValue().toString().trim();
        this.empleados.getTpais().setId(Integer.valueOf(e.getNewValue().toString().trim()));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.idpais = itemSelect.toString().trim();
        }

    }

    public void oncboDeptoPais(ValueChangeEvent e) {
        this.iddepto = e.getNewValue().toString().trim();
        this.empleados.getTdeptoPais().setId(Integer.valueOf(this.iddepto));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTdeptoPais().setId(itemSelect);
            this.iddepto = itemSelect.toString().trim();
            getListaMunicipios();

        }
    }

    public void oncboMunicipio(ValueChangeEvent e) {
        this.id_municipio = Integer.valueOf(e.getNewValue().toString());
        this.empleados.getTmunicipios().setId(Integer.valueOf(e.getNewValue().toString().trim()));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTmunicipios().setId(itemSelect);
        }

    }

    public void oncboDeptoEmp(ValueChangeEvent e) {
        this.empleados.getTdeptoEmp().setId(Integer.valueOf(e.getNewValue().toString().trim()));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTdeptoEmp().setId(itemSelect);

        }
    }

    public void oncboTipoEmpleado(ValueChangeEvent e) {
        this.empleados.getTtipoEmpleado().setId(Integer.valueOf(e.getNewValue().toString().trim()));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTtipoEmpleado().setId(itemSelect);
        }

    }

    public void oncboTipoDocumentoEmpleado(ValueChangeEvent e) {
        this.empleados.getTtipoDocumento().setId(Integer.valueOf(e.getNewValue().toString().trim()));

        if (e.getNewValue() != e.getOldValue()) {
            Integer itemSelect = Integer.valueOf(e.getNewValue().toString());
            this.empleados.getTtipoDocumento().setId(itemSelect);

        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        //FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        msgbox.vbInfo("Fotografía almacenada correctamente");
    }
}
