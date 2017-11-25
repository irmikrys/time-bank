package timebank.service;

import timebank.model.Account;

import java.util.Optional;

public interface AccountService {

  Optional<Account> findByAccountNr(Long accountNr);

  Optional<Account> findByUsername(String username);

  Account createAccount(String usermane);

}
