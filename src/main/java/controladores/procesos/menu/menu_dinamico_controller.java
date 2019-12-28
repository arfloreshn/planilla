package controladores.procesos.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.interfaz.menu.menuDao;
import dao.implementar.menu.menuDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Menu;
import modelo.Usuario;

import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import static utilerias.funciones.funciones.PathApp;
import static utilerias.funciones.funciones.PathInicio;

/**
 *
 * @author root
 */
@Named(value = "menuBean")
@SessionScoped
public class menu_dinamico_controller implements Serializable {

    private Integer m_menu;
    private List<Menu> lstmenu;
    private MenuModel model;
    private String pathForms;
    private String Frm;

    private boolean rsp;

    private FacesContext contex = FacesContext.getCurrentInstance();
   
    public menu_dinamico_controller() {

        this.verificarSesion();
        model = new DefaultMenuModel();
    }

    @PostConstruct
    public void init() {
        this.verificarSesion();
        this.Iniciarmenu();
        this.crearMenu();
    }

    
    public void verificarSesion()
    {
      try
      {
       this.contex =  FacesContext.getCurrentInstance();
       Usuario us = (Usuario) this.contex.getExternalContext().getSessionMap().get("usuario");
      
      if(us==null)
      {
          String path = PathInicio()+"/page403.xhtml";
          this.contex.getExternalContext().redirect(path);
      }
      
      } 
      catch(Exception e)
      {
        e.getMessage();
      }
      
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

        pathForms = PathApp();
        //Haga el loop mientras las cuatros opciones de menu no estan contruidas
        while (n <= this.m_menu) {

            // Por cada loop construimos el menu y los submenus de nivel 1, 2 y 3
            for (int i = 0; i < 1; i++) {

                
                // Contrullendo el nivel 1 o Menus Contextuales
                for (Menu m1 : lstmenu) {

                    if (m1.getIdMenu().intValue() == n && m1.getTipoItem().toString().contains("M")) {

                        idMenu = m1.getIdItem().intValue();

                        if (m1.getNombre().isEmpty() || m1.getNombre().length() <= 0) {
                            separar = new DefaultSeparator();
                            mMnu.addElement(separar);
                        } else {

                            mMnu = new DefaultSubMenu(m1.getNombre().toString());
                        }

                        if (m1.getId().toString().length() > 0) {
                            mMnu.setId(m1.getId().toString());
                        }

                        // Agregamos el  nivel 2 o Submenu 
                        for (Menu s1 : lstmenu) {
                            
                            // Verifico si el submenu que voy a construir sea hijo del mismo papa
                            if (s1.getIdMenu().intValue() == n
                                    && s1.getTipoItem().toString().contains("S")
                                    && s1.getIdNivel().intValue() == idMenu) {
                                
                                    //optengo el id del submenu, ese sera ahora el papa de los items
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

                        
                       // Agregamos el  Submenu 

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
        return "/login?faces-redirect=true";
    }

}
