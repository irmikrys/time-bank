package timebank.service;

import timebank.dto.LocationDTO;
import timebank.dto.UserDTO;
import timebank.model.User;

import java.util.Optional;

public interface UserService {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  Iterable<User> findAll();

  User createUser(UserDTO userDTO);

  User updateUser(UserDTO userDTO);

}
