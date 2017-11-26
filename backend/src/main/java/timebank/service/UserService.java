package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import timebank.dto.UserDTO;
import timebank.model.Account;
import timebank.model.Location;
import timebank.model.User;
import timebank.repository.UserRepository;
import java.util.Optional;

@Service("userService")
public class UserService {

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Autowired
  @Qualifier("locationService")
  private LocationService locationService;

  @Autowired
  @Qualifier("accountService")
  private AccountService accountService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private JdbcTemplate jdbcTemplate;
  

  public Optional<User> findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  public Optional<User> findByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public Iterable<User> findAll() {
    return this.userRepository.findAll();
  }

  public User createUser(UserDTO userDTO) {
    Location location = this.locationService.createLocation(userDTO.getLocationDTO());
    Account account = this.accountService.createAccount(userDTO.getUsername());
    User user = userDTO.toUser(this.bCryptPasswordEncoder.encode(userDTO.getPassword()),"USER", location.getIdLocation());
    return this.userRepository.save(user);
  }

  public User updateUser(UserDTO userDTO) {
//    User updatedUser = userRepository.findByUsername(userDTO.getUsername());
//    updatedUser.setFirstName(userDTO.getFirstName());
//    updatedUser.setLastName(userDTO.getLastName());
//    updatedUser.setEmail(userDTO.getEmail());
//    updatedUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
//    return userRepository.save(updatedUser);
    return new User();
  }
}
