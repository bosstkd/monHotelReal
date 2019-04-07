/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fct.uploadedFiles;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        
        String codeH = getCdHFromSuper();
        
        
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/uploadTest/"+codeH+"/photos/");
        System.out.println(relativeWebPath);
      
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
    
    public String getCdHFromSuper(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdH");
    }
}