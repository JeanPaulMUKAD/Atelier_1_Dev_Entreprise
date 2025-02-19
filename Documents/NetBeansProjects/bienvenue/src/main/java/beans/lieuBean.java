package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("lieuBean")
@SessionScoped

public class lieuBean implements Serializable {
    private String nom;
    private String description;
    private String latitude;
    private String longitude;
    private List<Lieu> lieux = new ArrayList<>();

    // Getters et Setters
    public String getNom() { 
        return nom; 
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description; 
    }

    public String getLatitude() {
        return latitude; 
    }
    public void setLatitude(String latitude) { 
        this.latitude = latitude; 
    }

    public String getLongitude() { 
        return longitude; 
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude; 
    }

    public List<Lieu> getLieux() { return lieux; }

    // Classe interne pour stocker les lieux
    public static class Lieu {
        private String nom;
        private String description;
        private String latitude;
        private String longitude;

        public Lieu(String nom, String description, String latitude, String longitude) {
            this.nom = nom;
            this.description = description;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getNom() { 
            return nom; 
        }
        public String getDescription() { 
            return description; 
        }
        public String getLatitude() { 
            return latitude; 
        }
        public String getLongitude() {
            return longitude;
        }
    }

    public void ajouterLieu() {
        lieux.add(new Lieu(nom, description, latitude, longitude));
        nom = "";
        description = "";
        latitude = "";
        longitude = "";
    }

}

    
    

