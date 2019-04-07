/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.agc.mhAgenceBean;
import fct.uploadedFiles;
import java.io.FileNotFoundException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class photoUploadController {
    
   // String cheminLogo = "";
    UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
/*
    public String getCheminLogo() {
        return cheminLogo;
    }

    public void setCheminLogo(String cheminLogo) {
        this.cheminLogo = cheminLogo;
    }
*/        
 //------------------------ 
    public void MutlipleFileUpload(FileUploadEvent event) {
        file = event.getFile();
          HttpSession hs = Util.getSession();
          uploadedFiles upf = new uploadedFiles();
          FacesMessage message ;
          try {
               String str = upf.photoReceiver(file, hs.getAttribute("code_h").toString());
                message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        } catch (Exception e) {
                message = new FacesMessage("Erreur", event.getFile().getFileName() + " Taille trop importante.");
        }
              FacesContext.getCurrentInstance().addMessage(null, message);

         // uploadedFiles.fileName(str);
      
    }
    
     public void SingleFileUpload(FileUploadEvent event) throws FileNotFoundException {
          uploadedFiles upf = new uploadedFiles();
          file = event.getFile();
          HttpSession hs = Util.getSession();
          upf.logoReceiver(file, hs.getAttribute("code_h").toString());
        //  cheminLogo = "E:/uploadTest/"+hs.getAttribute("code_h").toString()+"/logo/logo_RS.jpg";
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size:"+event.getFile().getSize());
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
     

}
