/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.particulier;

import bean.agc.mhAgenceBean;
import fct.uploadedFiles;
import java.io.FileNotFoundException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.particulier.MhPubParticulier;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class photoUploadControllerPart {

    
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
    UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
       
        
        private String codeP;

    public String getCodeP() {
        return codeP;
    }

    public void setCodeP(String codeP) {
        this.codeP = codeP;
    }
        
        
 //------------------------ 
    public void MutlipleFileUpload(FileUploadEvent event) {
        file = event.getFile();
         String cdPub = codeP;
          uploadedFiles upf = new uploadedFiles();
          FacesMessage message ;
          try {
               String str = upf.photoPubReceiver(file, cdPub);
                message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        } catch (Exception e) {
                message = new FacesMessage("Erreur", event.getFile().getFileName() + " Taille trop importante.");
        }
              FacesContext.getCurrentInstance().addMessage(null, message);

    }
    

  
     
     @EJB
     mhAgenceBean beanAgc;
      public void SingleFileUploadAgc(FileUploadEvent event) throws FileNotFoundException {
         
          file = event.getFile();
          uploadedFiles upf = new uploadedFiles();
            upf.logoReceiver(file, beanAgc.agc().getCodeA());
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size:"+event.getFile().getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
       
    }
     
      
      public void onRowSelect(SelectEvent event) {
          String n_p = ((MhPubParticulier) event.getObject()).getCodePubParticulier();
          codeP = n_p;
          active = true;
    }

      
      public void init(){
          active = false;
          codeP = "";
      }
      
}
