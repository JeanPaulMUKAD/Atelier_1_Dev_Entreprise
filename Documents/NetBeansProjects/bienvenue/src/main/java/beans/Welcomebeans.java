package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("Welcomebeans")
@RequestScoped
public class Welcomebeans {
    private String nom;
    private String message;
    private double montant;
    private String devise;
    private double montantConverti;

    // Getters et setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom(){
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

    public void afficherMessages() {
        if (nom != null && !nom.trim().isEmpty()) {
            message = "Selamat datang, " + nom + "!";
        } else {
            message = "";
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
