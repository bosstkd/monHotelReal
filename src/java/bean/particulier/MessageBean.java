/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.particulier;

import bean.MhCltSChBean;
import controller.particulier.MessageManagerLocal;
import fct.dt;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.particulier.Message;
import org.primefaces.context.RequestContext;
 
/**
 *
 * @author Anton Danshin
 */
@ManagedBean
@ViewScoped
public class MessageBean implements Serializable {
 
    @EJB
    MessageManagerLocal mm;
 
    private final List messages;
    private Date lastUpdate;
    private Message message;
 
    /**
     * Creates a new instance of MessageBean
     */
    public MessageBean() {
        messages = Collections.synchronizedList(new LinkedList());
        lastUpdate = new Date(0);
        message = new Message();
    }
 
    public Date getLastUpdate() {
        return lastUpdate;
    }
 
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
 
    public Message getMessage() {
        return message;
    }
 
    public void setMessage(Message message) {
        this.message = message;
    }
 
    
    @EJB
    MhCltSChBean beanClt;
    public void sendMessage(ActionEvent evt) {
        mm.sendMessage(message);
    }
 

    
    public void firstUnreadMessage(ActionEvent evt) {
       RequestContext ctx = RequestContext.getCurrentInstance();
 
       Message m = mm.getFirstAfter(lastUpdate);
 
       ctx.addCallbackParam("ok", m!=null);
       if(m==null)
           return;
 
       lastUpdate = m.getDateSent();

       dt DTS = new dt();
       
       ctx.addCallbackParam("user", m.getUser());
       ctx.addCallbackParam("dateSent", DTS.form_Aff(m.getDateSent())+" "+DTS.getTime(m.getDateSent()));
       ctx.addCallbackParam("text", reglageMsg(m.getMessage()));
    }
    
    @PostConstruct
    public void init(){
        try {
            message.setUser(beanClt.clt().getNomPrenom());
        } catch (Exception e) {
        }
       
    }
 
    String reglageMsg(String msg){
        String ligne = "";
        int x = 0;
        for(int i = 0; i<msg.length(); i++){
            ligne = ligne+msg.charAt(i);
            if(x>=13){
                ligne = ligne+"\n";
                x=0;
            }else{
                x++;
            }
        }
        return "- "+ligne;
    }
    
    
}