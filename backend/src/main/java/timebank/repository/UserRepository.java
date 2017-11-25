package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import timebank.model.User;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {

//  Optional<User> findByUsername(String username);
//
//  Optional<User> findByEmail(String email);

  User findByUsername(String username);

  User findByEmail(String email);

}
