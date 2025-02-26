/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import jakarta.faces.context.FacesContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.IOException;

/**
 *
 * @author JEAN-PAUL MUKAD
 */

@Named("NavigationBean")
@RequestScoped
public class NavigationBean { 
    
    public void VoirApropos(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("pages/a_propos.xhtml");
        
        }catch(IOException e){
            e.printStackTrace();
        
        }
    
    
    }
    public void VoirVisiter(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        
        }catch(IOException e){
            e.printStackTrace();
        
        }
    
    
    }
    public void VoirAjouter(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("pages/lieu.xhtml");
        
        }catch(IOException e){
            e.printStackTrace();
        
        }
    
    
    }
      
}
