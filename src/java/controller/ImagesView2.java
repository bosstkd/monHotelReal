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
public class ImagesView2 {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 8; i++) {
            images.add(i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}