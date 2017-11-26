package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.model.Account;
import timebank.repository.AccountRepository;

import java.util.Optional;

@Service("accountService")
public class AccountService {

  @Autowired
  @Qualifier("accountRepository")
  private AccountRepository accountRepository;
  
  
  public Optional<Account> findByAccountNr(Long accountNr) {
    return this.accountRepository.findByAccountNr(accountNr);
  }
  
  public Optional<Account> findByOwner(String username) {
    return this.accountRepository.findByOwner(username);
  }
  
  public Account createAccount(String usermane) {
    Account account = new Account();
    account.setOwner(usermane);
    account.setAmount(0);
    return this.accountRepository.save(account);
  }
}
