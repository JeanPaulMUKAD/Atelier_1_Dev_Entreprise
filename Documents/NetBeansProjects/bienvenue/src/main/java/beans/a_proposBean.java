package beans;

import business.SessionManager;
import business.UtilisateurEntrepriseBean;
import entities.Utilisateur;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author JEAN-PAUL MUKAD
 */
@Named("a_proposBean")
@SessionScoped
public class a_proposBean implements Serializable {

    private String email;
    private String username;
    private String description;
    private String newPassword;

    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;

    @Inject
    private SessionManager sessionManager;

  
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    @PostConstruct
    public void init() {
        
         chargerProfil();
    }

   
    public void chargerProfil() {
        String userEmail = sessionManager.getValueFromSession("user");
        if (userEmail != null) {
            Utilisateur utilisateur = utilisateurEntrepriseBean.trouverUtilisateurParEmail(userEmail);
            if (utilisateur != null) {
                this.email = utilisateur.getEmail();
                this.username = utilisateur.getUsername();
                //this.description = utilisateur.getDescription();
            }
        }
    }

        public String mettreAJourProfil() {
            String userEmail = sessionManager.getValueFromSession("user");
            FacesContext context = FacesContext.getCurrentInstance();

            if (userEmail != null) {
                Utilisateur utilisateur = utilisateurEntrepriseBean.trouverUtilisateurParEmail(userEmail);
                if (utilisateur != null) {
                 
                    if (newPassword == null || newPassword.trim().isEmpty()) {
                        context.addMessage("newPassword", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le mot de passe est obligatoire.", null));
                        return null; 
                    }

                    
                    String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    utilisateur.setPassword(hashedPassword);

              
                    utilisateurEntrepriseBean.mettreAJourUtilisateur(utilisateur);

                    context.addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre profil a été mis à  jour avec succès.", null));

                    return "/index?faces-redirect=true"; 
                }
            }

            context.addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la mise de votre profil.", null));

            return null; 
        }


}