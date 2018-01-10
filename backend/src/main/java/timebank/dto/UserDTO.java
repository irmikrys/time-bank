package timebank.dto;

import org.hibernate.validator.constraints.Email;
import timebank.model.User;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

  @Pattern(regexp = "^([a-zA-Z0-9]+[-_.]?)*[a-zA-Z0-9]+$")
  @Size(min=3, max=30)
  private String username;

  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,30}")
  private String password;

  @Email
  private String email;

  @Pattern(regexp = "^([a-zA-Z]+\\s?)*[a-zA-Z]+$")
  @Size(min=3, max=30)
  private String firstName;

  @Pattern(regexp = "^([a-zA-Z]+\\s?)*[a-zA-Z]+$")
  @Size(min=3, max=30)
  private String lastName;

  @Valid
  private LocationDTO location;

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

  public LocationDTO getLocation() {
    return location;
  }

  public void setLocation(LocationDTO locationDTO) {
    this.location = locationDTO;
  }

  public User toUser(String password, String role, long idLocation) {
    User user = new User();
    user.setUsername(getUsername());
    user.setPassword(password);
    user.setEmail(getEmail());
    user.setFirstName(getFirstName());
    user.setLastName(getLastName());
    user.setRole(role);
    user.setIdLocation(idLocation);
    return user;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
      "username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      '}';
  }
}
