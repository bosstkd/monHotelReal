/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.particulier;

import java.io.Serializable;
import java.util.Date;
 
public class Message implements Serializable {
    private Date dateSent;
    private String user;
    private String message;
 
    public Date getDateSent() {
        return dateSent;
    }
 
    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
}