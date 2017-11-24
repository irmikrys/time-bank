package timebank.dto;

import org.hibernate.validator.constraints.Email;
import timebank.model.UserInfo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInfoRequest {

  @Pattern(regexp = "^([a-zA-Z0-9]+[-_.]?)*[a-zA-Z0-9]+$")
  @Size(min=3, max=30)
  private String username;

  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,30}")
  private String password;

  @Pattern(regexp = "^([a-zA-Z]+\\s?)*[a-zA-Z]+$")
  @Size(min=3, max=30)
  private String firstName;

  @Pattern(regexp = "^([a-zA-Z]+\\s?)*[a-zA-Z]+$")
  @Size(min=3, max=30)
  private String lastName;

  @Email
  private String email;

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

  public String getFirstName() { return firstName; }

  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return lastName; }

  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public UserInfo toUserInfo(String password, String role) {
    UserInfo userInfo = new UserInfo();
    userInfo.setUsername(getUsername());
    userInfo.setFirstName(getFirstName());
    userInfo.setLastName(getLastName());
    userInfo.setEmail(getEmail());
    userInfo.setPassword(password);
    userInfo.setRole(role);
    return userInfo;
  }

}
