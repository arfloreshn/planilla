/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.funciones;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AllanRamiro
 */
public class msgbox {

    public static HttpSession getSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return (HttpSession) fc.getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        return request;
    }

    public static FacesContext getFC() {
        return FacesContext.getCurrentInstance();
    }

    public static void vbInfo(String msg) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        fc.getCurrentInstance().addMessage(msg, message);

    }

    public static void vbPrecaucion(String msg) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
        fc.getCurrentInstance().addMessage(msg, message);
    }

    public static void vbError(String msg) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        fc.getCurrentInstance().addMessage(msg, message);
    }

    public static void vbFatal(String msg) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        fc.getCurrentInstance().addMessage(msg, message);
    }

}
