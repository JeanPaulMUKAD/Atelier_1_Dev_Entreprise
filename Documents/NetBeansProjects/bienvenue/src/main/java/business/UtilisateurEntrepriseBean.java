package business;

import entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;

@Stateless
public class UtilisateurEntrepriseBean {

    @PersistenceContext
    private EntityManager em;

    public UtilisateurEntrepriseBean() {
    }


    @Transactional
    public void ajouterUtilisateurEntreprise(String username, String email, String password, String description) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        Utilisateur utilisateur = new Utilisateur(username, email, hashedPassword, description);
        em.persist(utilisateur);
    }


    public Utilisateur trouverUtilisateurParUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.username = :username", Utilisateur.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }


    public Utilisateur trouverUtilisateurParEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }
        public Utilisateur authentifier(String email, String password) {
            Utilisateur utilisateur = trouverUtilisateurParEmail(email);
            if (utilisateur != null && BCrypt.checkpw(password, utilisateur.getPassword())) {
                return utilisateur;
            }
            return null;
        }



 
    @Transactional
    public void ajouterUtilisateur(String username, String email, String password, String confirmPassword, String description) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!password.equals(confirmPassword)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Les mots de passe ne correspondent pas", null));
            return;
        }

        ajouterUtilisateurEntreprise(username, email, password, description);
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur ajouté avec succès", null));

   
        username = "";
        email = "";
        password = "";
        confirmPassword = "";
        description = "";
    }
}
