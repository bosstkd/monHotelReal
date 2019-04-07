/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.agc;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amine
 */
public class UtilAgc {
    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
}
