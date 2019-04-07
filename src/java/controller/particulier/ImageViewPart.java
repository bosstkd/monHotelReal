/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.particulier;

import fct.uploadedFiles;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ImageViewPart {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        String codeP = getCdPFromSuper();   
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/uploadTest/publications/"+codeP+"/");
        
        uploadedFiles UPF = new uploadedFiles();
        List<String> imgs = UPF.getFileName(relativeWebPath);
        images = new ArrayList<String>();
        
        for(String img:imgs){
             images.add(img);
        }
        
    }
 
    public List<String> getImages() {
        return images;
    }
    
    public String getCdPFromSuper(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdPubPart");
    }
}