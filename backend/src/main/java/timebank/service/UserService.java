package timebank.service;

import timebank.dto.UserDTO;
import timebank.model.User;

public interface UserService {

  User findByUsername(String username);

  User findByEmail(String email);

  Iterable<User> findAll();

  User createUser(UserDTO userDTO);

  User updateUser(UserDTO userDTO);

}
