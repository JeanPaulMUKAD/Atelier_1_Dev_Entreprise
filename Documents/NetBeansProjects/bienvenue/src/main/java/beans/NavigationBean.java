package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;


@Named("NavigationBean")
@SessionScoped

public class NavigationBean implements Serializable {

    public void voirApropos(){
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("a_propos.xhtml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

    
    

