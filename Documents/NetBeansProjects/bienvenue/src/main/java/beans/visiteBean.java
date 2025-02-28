package beans;

import entities.Visite;
import entities.Utilisateur;
import entities.Lieu;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named(value = "formulaireVisiteBean")
@SessionScoped
public class visiteBean implements Serializable {

    private Long idUtilisateur;
    private Long idLieu;
    private LocalDateTime dateVisite; // Utilisation de LocalDateTime
    private int tempsPasse;
    private String observations;
    private double depenses;
    private boolean afficherFormulaireVisite;
    
    private List<Visite> visitesUtilisateur; // Liste des visites

    // Initialisation de la liste des visites
    public visiteBean() {
        visitesUtilisateur = new ArrayList<>();
    }

    // Initialisation de la date de visite avec la date actuelle lors de la création de l'objet
    @PostConstruct
    public void init() {
        dateVisite = LocalDateTime.now(); // Initialisation avec la date actuelle
    }

    // Getters et Setters
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Long getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }

    public LocalDateTime getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDateTime dateVisite) {
        this.dateVisite = dateVisite;
    }

    public int getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(int tempsPasse) {
        this.tempsPasse = tempsPasse;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public double getDepenses() {
        return depenses;
    }

    public void setDepenses(double depenses) {
        this.depenses = depenses;
    }

    public boolean isAfficherFormulaireVisite() {
        return afficherFormulaireVisite;
    }

    public void setAfficherFormulaireVisite(boolean afficherFormulaireVisite) {
        this.afficherFormulaireVisite = afficherFormulaireVisite;
    }

    public List<Visite> getVisitesUtilisateur() {
        return visitesUtilisateur;
    }

    public void setVisitesUtilisateur(List<Visite> visitesUtilisateur) {
        this.visitesUtilisateur = visitesUtilisateur;
    }

    // Afficher le formulaire de visite
    public void afficherFormulaireVisite() {
        afficherFormulaireVisite = true;
    }

    public void sauvegarderVisite() {
        // Créer une nouvelle visite avec le constructeur personnalisé
        // Ici, vous devez récupérer l'utilisateur et le lieu réels, pas en instanciant des objets vides.
        Utilisateur utilisateur = recupererUtilisateur(idUtilisateur);  // Exemple de méthode pour récupérer l'utilisateur
        Lieu lieu = recupererLieu(idLieu);  // Exemple de méthode pour récupérer le lieu

        // Créer la visite avec le constructeur personnalisé
        Visite nouvelleVisite = new Visite(utilisateur, lieu, dateVisite, tempsPasse, observations, depenses);

        // Ajouter la visite à la liste des visites
        visitesUtilisateur.add(nouvelleVisite);

        // Afficher un message de succès
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Visite enregistrée avec succès",
                        null));

        // Désactiver l'affichage du formulaire après l'enregistrement
        afficherFormulaireVisite = false;
    }

    // Méthodes pour récupérer l'utilisateur et le lieu (simulation pour l'exemple)
    private Utilisateur recupererUtilisateur(Long id) {
        // Cette méthode doit récupérer l'utilisateur en fonction de l'id, par exemple via un service ou une base de données.
        // Pour le moment, retournons un objet Utilisateur vide pour l'exemple.
        return new Utilisateur();  // Remplacer par une vraie récupération
    }

    private Lieu recupererLieu(Long id) {
        // Cette méthode doit récupérer le lieu en fonction de l'id, par exemple via un service ou une base de données.
        // Pour le moment, retournons un objet Lieu vide pour l'exemple.
        return new Lieu();  // Remplacer par une vraie récupération
    }
}
