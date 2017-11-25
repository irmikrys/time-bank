package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Account;

import java.util.Optional;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByAccountNr(long accountNr);

  Optional<Account> findByUsername(String username);

}
