package beans;

import business.SessionManager;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import business.UtilisateurEntrepriseBean;
import entities.Utilisateur;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named("Welcomebeans")
@RequestScoped
public class Welcomebeans {
    
    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;
    @Inject
    private SessionManager sessionManager;

    private String nom;
    private String message;
    private double montant;
    private String devise;
    private double montantConverti;
    private String email;
    private String password;

    // Getters et setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getNom() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public double getMontantConverti() {
        return montantConverti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void afficherMessages() {
        if (nom != null && !nom.trim().isEmpty()) {
            message = "Selamat datang, " + nom + "!";
        } else {
            message = "";
        }
    }

    public void seConnecter() {
        FacesContext context = FacesContext.getCurrentInstance();
        Utilisateur utilisateur = utilisateurEntrepriseBean.authentifier(email, password);

        if (utilisateur != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion réussie. Bienvenue, " + utilisateur.getUsername() + "!", null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Échec de l'authentification. Vérifiez votre email et votre mot de passe.", null));
        }
    }

    public void convertir() {
        if (devise != null) {
            switch (devise) {
                case "Dollar":
                    montantConverti = montant * 0.012; 
                    break;
                case "Francs":
                    montantConverti = montant * 0.011; 
                    break;
                default:
                    montantConverti = 0;
                    break;
            }
        }
    }
}
