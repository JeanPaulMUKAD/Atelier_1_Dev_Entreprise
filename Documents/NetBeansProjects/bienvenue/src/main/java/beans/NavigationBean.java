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
    
    public String VoirApropos(){
        return "/pages/a_propos?faces-redirect=true";
    }
    
    public String VoirVisiterLieux(){
        return "/pages/guide?faces-redirect=true";
    }
    
    public String VoirAjouter(){
        return "/pages/lieu?faces-redirect=true";
    }
}