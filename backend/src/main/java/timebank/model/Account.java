package timebank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "accountNr")
  private long accountNr;

  @Column(name = "owner")
  private String owner;

  @Column(name = "balance")
  private int balance;

  @JsonIgnore
  public long getAccountNr() {
    return accountNr;
  }

  public void setAccountNr(long accountNr) {
    this.accountNr = accountNr;
  }

  @JsonIgnore
  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public int getAmount() {
    return balance;
  }

  public void setAmount(int amount) {
    this.balance = amount;
  }

  public void changeAccountBalance(int amount) {
    this.balance += amount;
  }
}
