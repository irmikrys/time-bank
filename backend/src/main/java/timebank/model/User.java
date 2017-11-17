package timebank.model;

public class User {

  private String username;

  private String token;

  private boolean authenticated;

  public User() {}

  public User(String username, String token, boolean authenticated) {
    this.username = username;
    this.token = token;
    this.authenticated = authenticated;
  }

  public String getUsername() { return username; }

  public String getToken() { return token; }

  public boolean isAuthenticated() { return authenticated; }
}
