package timebank.repository;

import org.springframework.data.repository.CrudRepository;

import timebank.model.UserInfo;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

  UserInfo findByUsername(String username);

}
