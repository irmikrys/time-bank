package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timebank.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

  UserInfo findByUsername(String username);

  UserInfo findByEmail(String email);

}
