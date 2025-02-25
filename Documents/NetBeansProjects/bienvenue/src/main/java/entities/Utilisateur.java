// Source code is decompiled from a .class file using FernFlower decompiler.
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "Utilisateur"
)
public class Utilisateur {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      nullable = false,
      unique = true,
      length = 50
   )
   private String username;
   @Column(
      nullable = false,
      unique = true,
      length = 100
   )
   private String email;
   @Column(
      nullable = false,
      length = 255
   )
   private String password;
   private String description;
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
   
    public String getUsername() {
            return username;
    }

   public void setUsername(String username) {
            this.username = username;
    }

   public Utilisateur() {
   }

   public Utilisateur(String username, String email, String password, String description) {
      this.username = username;
      this.email = email;
      this.password = password;
      this.description = description;
   }
}
