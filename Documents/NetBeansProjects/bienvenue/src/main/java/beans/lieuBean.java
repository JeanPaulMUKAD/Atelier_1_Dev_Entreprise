package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
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
    private int indexModification = -1; // Indice du lieu en cours de modification (-1 si aucun)
    private List<Lieu> lieux = new ArrayList<>();

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public List<Lieu> getLieux() { return lieux; }

    public int getIndexModification() { return indexModification; }
    public void setIndexModification(int indexModification) { this.indexModification = indexModification; }

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

        public String getNom() { return nom; }
        public String getDescription() { return description; }
        public String getLatitude() { return latitude; }
        public String getLongitude() { return longitude; }

        public void setNom(String nom) { this.nom = nom; }
        public void setDescription(String description) { this.description = description; }
        public void setLatitude(String latitude) { this.latitude = latitude; }
        public void setLongitude(String longitude) { this.longitude = longitude; }
    }

    public void ajouterLieu() {
        lieux.add(new Lieu(nom, description, latitude, longitude));
        resetForm();
    }

    // Méthode pour préparer la modification d'un lieu
    public void preparerModification(Lieu lieu) {
        this.nom = lieu.getNom();
        this.description = lieu.getDescription();
        this.latitude = lieu.getLatitude();
        this.longitude = lieu.getLongitude();
        this.indexModification = lieux.indexOf(lieu);
    }

    // Méthode pour modifier un lieu existant
    public void modifierLieu() {
        if (indexModification >= 0 && indexModification < lieux.size()) {
            Lieu lieu = lieux.get(indexModification);
            lieu.setNom(nom);
            lieu.setDescription(description);
            lieu.setLatitude(latitude);
            lieu.setLongitude(longitude);
        }
        resetForm();
    }

    // Méthode pour supprimer un lieu
    public void supprimerLieu(Lieu lieu) {
        lieux.remove(lieu);
    }

    // Réinitialiser le formulaire après une action
    private void resetForm() {
        nom = "";
        description = "";
        latitude = "";
        longitude = "";
        indexModification = -1;
    }
}
