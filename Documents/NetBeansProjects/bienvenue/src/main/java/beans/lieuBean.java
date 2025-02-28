package beans;

import business.LieuEntrepriseBean;
import business.SessionManager;
import entities.Lieu;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JEAN-PAUL MUKAD
 */

@Named("lieuBean")
@SessionScoped
public class LieuBean implements Serializable {
    private int idLieu = 0;
    private String nom;
    private String description;
    private double latitude;
    private double longitude;
    private String weatherMessage;
    private int selectedLieu;
    


    @Inject
    private LieuEntrepriseBean lieuEntrepriseBean;
    
    @Inject
    private SessionManager sessionmanager;


    public List<Lieu> getListeLieux() {
        return lieuEntrepriseBean.listerTousLesLieux();
    }

    public void enregistrerLieu() {
        if (idLieu == 0) {
            lieuEntrepriseBean.ajouterLieuEntreprise(nom, description, latitude, longitude);
        } else {
            lieuEntrepriseBean.modifierLieuEntreprise(idLieu, nom, description, latitude, longitude);
        }
        resetForm();
    }

    public void supprimerLieu(int id) {
        lieuEntrepriseBean.supprimerLieuEntreprise(id);
    }


    public void preparerModification(Lieu lieu) {
        this.idLieu = lieu.getId();
        this.nom = lieu.getNom();
        this.description = lieu.getDescription();
        this.latitude = lieu.getLatitude();
        this.longitude = lieu.getLongitude();
    }


    private void resetForm() {
        this.idLieu = 0;
        this.nom = "";
        this.description = "";
        this.latitude = 0;
        this.longitude = 0;
    }
    public void fetchWeatherMessage(Lieu l) {

        if (l != null) {
            // Appel au service web pour obtenir les données météorologiques

            String serviceURL = "http://localhost:8080/j-weather/webapi/JarkartaWeather?latitude="+ l.getLatitude() + "&longitude=" + l.getLongitude();

            Client client = ClientBuilder.newClient();
            String response = client.target(serviceURL)
            .request(MediaType.TEXT_PLAIN)
            .get(String.class);

            // Enregistrement du message météo dans la variable weatherMessage
            this.weatherMessage =response;
            sessionmanager.createSession("id_Lieu", l.getId() + "");
        }

    }
    

    public void updateWeatherMessage(AjaxBehaviorEvent event) {

    Lieu lieu=lieuEntrepriseBean.trouverLieuParId(selectedLieu);
    this.fetchWeatherMessage(lieu);
    }

    public String getWeatherMessage() {
    return weatherMessage;
    }

    public void setWeatherMessage(String weatherMessage) {
    this.weatherMessage = weatherMessage;
    }



    public int getIdLieu() {
        return idLieu; 
    }
    public void setIdLieu(int idLieu) { 
        this.idLieu = idLieu; 
    }

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

    public double getLatitude() {
        return latitude; 
    }
    public void setLatitude(double latitude) { 
        this.latitude = latitude; 
    }

    public double getLongitude() {
        return longitude; 
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public String getweatherMessage(){
        return weatherMessage;
    }
    
    public void setweatherMessage(String weatherMessage){
        this.weatherMessage = weatherMessage;
    }
    
    public int getSelectedLieu(){
        return selectedLieu;
    }
    
    public void setSelectedLieu(int selectedLieu){
        this.selectedLieu = selectedLieu;
    }
}