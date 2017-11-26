package timebank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;
  
  @Column(name = "email")
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "role")
  private String role;
  
  @Column(name = "photo")
  private Byte photo;
  
  @Column(name = "id_location")
  private Long idLocation;
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getRole() {
    return role;
  }
  
  public void setRole(String role) {
    this.role = role;
  }
  
  public Byte getPhoto() {
    return photo;
  }
  
  public void setPhoto(Byte photo) {
    this.photo = photo;
  }
  
  public Long getIdLocation() {
    return idLocation;
  }
  
  public void setIdLocation(Long idLocation) {
    this.idLocation = idLocation;
  }
}
