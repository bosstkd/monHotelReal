/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;


import bean.MhHotelBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@ManagedBean
@RequestScoped
public class raisonValidation implements Validator {

    private static final String RAISON_EXISTE_DEJA = "Cette raison social existe déja !!";

    @EJB
    private MhHotelBean bean;

    @Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* Récupération de la valeur à traiter depuis le paramètre value */
        String raison = (String) value;
        try {
            if ( bean.selectRaison(raison ) != null ) {
              
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, RAISON_EXISTE_DEJA, null ) );
            }
        } catch ( DAOException e ) {
           
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
}