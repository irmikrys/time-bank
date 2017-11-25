package timebank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "account_nr")
  private long accountNr;

  @Column(name = "username")
  private String username;

  @Column(name = "amount")
  private int amount;

  public long getAccountNr() {
    return accountNr;
  }

  public void setAccountNr(long accountNr) {
    this.accountNr = accountNr;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

}
