/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImageSwitchView {
     
    private List<String> images;
 
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        images.add("1.jpg");
        images.add("2.jpg");
        images.add("3.jpg");
        images.add("4.jpg");
    }
 
    public List<String> getImages() {
        return images;
    }
}
