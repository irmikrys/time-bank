package timebank.dto;

import timebank.model.Account;
import timebank.model.Location;
import timebank.model.User;

public class UserDetailsDTO {

  private User user;

  private Location location;

  private Account account;


  public UserDetailsDTO(User user, Location location, Account account) {
    this.user = user;
    this.location = location;
    this.account = account;
  }

  public User getUser() {
    return user;
  }

  public Location getLocation() {
    return location;
  }

  public Account getAccount() {
    return account;
  }
}
