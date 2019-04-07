package validation;

import bean.MhHotelBean;
import bean.agc.mhAgenceBean;
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
public class adresseMailValidatorAgc implements Validator {

    private static final String EMAIL_EXISTE_DEJA = "Inscription déja efectuée cette adresse email est déjà utilisée";

    @EJB
    private mhAgenceBean bean;

    @Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* Récupération de la valeur à traiter depuis le paramètre value */
        String email = (String) value;
        try {
            if ( bean.getByMail(email) != null ) {
               
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, EMAIL_EXISTE_DEJA, null ) );
            }
        } catch ( DAOException e ) {
            
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
}