package beans;

import entities.Utilisateur;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import java.io.Serializable;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    private Utilisateur utilisateur; // Utilisateur connecté
    private String newPassword;
    private String confirmPassword;

    // Constructor
    public UserBean() {
        // Initialisation de l'utilisateur (ex: récupération de l'utilisateur connecté depuis la session)
        utilisateur = new Utilisateur(); // Cela devrait venir de la session ou du service d'authentification
    }

    // Getters et Setters
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Méthode pour mettre à jour le profil (par exemple, changer le mot de passe)
    public String updateProfile() {
        if (newPassword != null && !newPassword.isEmpty() && newPassword.equals(confirmPassword)) {
            // Logique de mise à jour du mot de passe
            utilisateur.setPassword(newPassword);
            // Sauvegarder dans la base de données (par exemple, via un service ou un EJB)
            // Utilise un service ou une couche d'accès aux données pour mettre à jour l'utilisateur dans la base
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profil mis à jour avec succès"));
            return "profileUpdated"; // redirection vers une page de confirmation ou d'accueil
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Les mots de passe ne correspondent pas", ""));
            return null;
        }
    }

    // Méthode pour déconnecter l'utilisateur
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // Invalider la session
        return "index.xhtml?faces-redirect=true"; // Rediriger vers la page d'accueil
    }
}
