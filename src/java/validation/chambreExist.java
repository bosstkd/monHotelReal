/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import bean.MhChambreBean;
import bean.MhReservationBean;
import controller.Util;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amine
 */
@ManagedBean
@RequestScoped
public class chambreExist implements Validator{
  private static final String CHAMBRE_NON_EXISTE = "Cette chambre n'existe pas !!";

    @EJB
    private MhChambreBean bean;
    
    @Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* Récupération de la valeur à traiter depuis le paramètre value */
        String num_ch = (String) value;
        HttpSession hs = Util.getSession();
        num_ch = (String) hs.getAttribute("code_h")+"_"+num_ch;
        
        try {
            
            String nmCh = "";
            try {
                nmCh = bean.singleSelect(num_ch).getNumCh();
            } catch (Exception e) {
                nmCh = "";
            }
            
            if (nmCh.equals("") ) {
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, CHAMBRE_NON_EXISTE, null ) );
            }
            
        } catch ( DAOException e ) {
           
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
}