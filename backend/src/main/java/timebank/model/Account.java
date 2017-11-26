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

  @Column(name = "owner")
  private String owner;

  @Column(name = "balance")
  private int balance;
  
  public long getAccountNr() {
    return accountNr;
  }
  
  public void setAccountNr(long accountNr) {
    this.accountNr = accountNr;
  }
  
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
