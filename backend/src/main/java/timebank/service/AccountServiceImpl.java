package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.model.Account;
import timebank.repository.AccountRepository;

import java.util.Optional;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

  @Autowired
  @Qualifier("accountRepository")
  private AccountRepository accountRepository;

  @Override
  public Optional<Account> findByAccountNr(Long accountNr) {
    return accountRepository.findByAccountNr(accountNr);
  }

  @Override
  public Optional<Account> findByUsername(String username) {
    return accountRepository.findByUsername(username);
  }

  @Override
  public Account createAccount(String usermane) {
    Account account = new Account();
    account.setUsername(usermane);
    account.setAmount(0);
    return accountRepository.save(account);
  }
}
