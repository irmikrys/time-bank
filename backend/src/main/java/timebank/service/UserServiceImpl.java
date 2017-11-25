package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import timebank.dto.LocationDTO;
import timebank.dto.UserDTO;
import timebank.model.Account;
import timebank.model.Location;
import timebank.model.User;
import timebank.repository.UserRepository;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

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

  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(UserDTO userDTO) {
    Location location = locationService.createLocation(userDTO.getLocationDTO());
    Account account = accountService.createAccount(userDTO.getUsername());
    User user = userDTO.toUser(bCryptPasswordEncoder.encode(userDTO.getPassword()),"USER", location.getIdLocation());
    return userRepository.save(user);
  }

  @Override
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
