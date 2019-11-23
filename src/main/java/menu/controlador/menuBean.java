/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.controlador;

import menu.dao.menuDao;
import menu.dao.menuDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import modelo.Menu;

import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import static utilerias.funciones.funciones.PathApp;

/**
 *
 * @author root
 */
@Named(value = "menuBean")
@SessionScoped
public class menuBean implements Serializable {

    private Integer m_menu;
    private List<Menu> lstmenu;
    private MenuModel model;
    private String pathForms;
    private String Frm;

    public menuBean() {

        model = new DefaultMenuModel();
    }

    @PostConstruct
    public void init() {

        this.Iniciarmenu();
        this.crearMenu();
    }

    public void Iniciarmenu() {
        // Aqui llenamos o cargamos nuestra tabla de menu 
        try {
            menuDao m_dao = new menuDaoImpl();
            this.lstmenu = m_dao.ListarMenu();
        } catch (Exception e) {
        }
    }

    //Listamos todos los item del menu
    public List<Menu> listarItemsMenu() {
        menuDao m_dao = new menuDaoImpl();
        this.lstmenu = m_dao.ListarMenu();

        return lstmenu;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void crearMenu() {

        this.m_menu = 3;

        DefaultSubMenu mMnu = null;
        DefaultSubMenu mSubMnu = null;
        DefaultMenuItem item = null;
        DefaultSeparator separar = null;

        Integer idMenu = null;
        Integer idSubMenu = null;
        Integer n = 1;

        pathForms = "/app/"; //PathApp();
        while (n <= this.m_menu) {

            for (int i = 0; i < 1; i++) {

                for (Menu m1 : lstmenu) {

                    if (m1.getIdMenu().intValue() == n && m1.getTipoItem().toString().contains("M")) {

                        idMenu = m1.getIdItem().intValue();

                        if (m1.getNombre().isEmpty() || m1.getNombre().length() <= 0) {
                            separar = new DefaultSeparator();
                            mMnu.addElement(separar);
                        } else {

                            mMnu = new DefaultSubMenu(m1.getNombre().toString());
                            //      mMnu.setStyle("width:  80px");
                        }

                        if (m1.getId().toString().length() > 0) {
                            mMnu.setId(m1.getId().toString());
                        }

                        // Agregar Submenu 
                        for (Menu s1 : lstmenu) {
                            if (s1.getIdMenu().intValue() == n
                                    && s1.getTipoItem().toString().contains("S")
                                    && s1.getIdNivel().intValue() == idMenu) {
                                idSubMenu = s1.getIdItem();
                                if (s1.getNombre().isEmpty() || s1.getNombre().length() <= 0) {
                                    separar = new DefaultSeparator();
                                    mMnu.addElement(separar);
                                } else {
                                    mSubMnu = new DefaultSubMenu(s1.getNombre().toString());
                                    //mSubMnu.setStyle("width:  240px");
                                    mMnu.addElement(mSubMnu);

                                }

                                for (Menu t1 : lstmenu) {
                                    if (t1.getIdMenu().intValue() == n
                                            && t1.getTipoItem().toString().contains("I")
                                            && t1.getIdNivel().intValue() == idSubMenu) {
                                        if (t1.getNombre().length() == 0) {
                                            separar = new DefaultSeparator();
                                            mSubMnu.addElement(separar);
                                        } else {
                                            Frm = t1.getUrl();
                                            item = new DefaultMenuItem(t1.getNombre().toString());
                                            //         item.setStyle("width:  240px");

                                            if (Frm.length() > 1) {
                                                item.setOutcome(pathForms + this.Frm);
                                            }
                                            mSubMnu.addElement(item);
                                        }
                                    }

                                }

                            }

                        } // Fin de Submenu s1

                        for (Menu s2 : lstmenu) {
                            if (s2.getIdMenu().intValue() == n
                                    && s2.getTipoItem().toString().contains("I")
                                    && s2.getIdNivel().intValue() == idMenu) {
                                
                                Frm = s2.getUrl();
                                item = new DefaultMenuItem(s2.getNombre().toString());
                                if (Frm.length() > 1) {
                                    item.setOutcome(pathForms + this.Frm);
                                }
                                mSubMnu.addElement(item);

// item.setStyle("width:  240px");
                                mMnu.addElement(item);

                            }
                        }

                    }

                }

            }

            mMnu.setStyleClass("estilomenu");

            model.addElement(mMnu);

            ++n;
        }

    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return "/faces/login.xhtml";
    }

}
