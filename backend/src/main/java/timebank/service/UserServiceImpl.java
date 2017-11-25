package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import timebank.dto.UserDTO;
import timebank.model.User;
import timebank.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(UserDTO userDTO) {
    User user = userDTO.toUserInfo(bCryptPasswordEncoder.encode(userDTO.getPassword()),"USER");
    return userRepository.save(user);
  }

  @Override
  public User updateUser(UserDTO userDTO) {
    User updatedUser = userRepository.findByUsername(userDTO.getUsername());
    updatedUser.setFirstName(userDTO.getFirstName());
    updatedUser.setLastName(userDTO.getLastName());
    updatedUser.setEmail(userDTO.getEmail());
    updatedUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    return userRepository.save(updatedUser);
  }
}
