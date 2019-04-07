package validation;


import bean.MhChambreBean;
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
public class chambreValidator implements Validator {

    private static final String CHAMBRE_EXISTE_DEJA = "Cette chambre existe déja !!";

    @EJB
    private MhChambreBean bean;

    @Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* Récupération de la valeur à traiter depuis le paramètre value */
        String num_ch = (String) value;
        try {
            String nmCh = "";
            try {
                nmCh = bean.singleSelect(num_ch).getNumCh();
            } catch (Exception e) {
                nmCh = "";
            }
            if ( !nmCh.equals("") ) {
              
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, CHAMBRE_EXISTE_DEJA, null ) );
            }
        } catch ( DAOException e ) {
           
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
}