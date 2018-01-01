package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import timebank.dto.AdvertDetailsDTO;
import timebank.dto.UserDTO;
import timebank.dto.UserDetailsDTO;
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

  public UserDetailsDTO findByUsernameUserDetails(String username) {
    User user = this.userRepository.findByUsername(username).get();
    Location location = this.locationService.findByIdLocation(user.getIdLocation()).get();
    Account account = this.accountService.findByOwner(username).get();
    return new UserDetailsDTO(user, location, account);
  }

  public Optional<User> findByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public Iterable<User> findAll() {
    return this.userRepository.findAll();
  }

  public User createUser(UserDTO userDTO) {
    Location location = this.locationService.createLocation(userDTO.getLocation());
    User user = userDTO.toUser(this.bCryptPasswordEncoder.encode(userDTO.getPassword()),"USER", location.getIdLocation());
    this.accountService.createAccount(userDTO.getUsername());
    return this.userRepository.save(user);
  }

  // TODO: Photo update
  public User updateUser(UserDTO userDTO) {
    User updatedUser = this.userRepository.findByUsername(userDTO.getUsername()).orElseThrow(
      () -> new UsernameNotFoundException("updateUser.error.usernameNotFound"));
    updatedUser.setFirstName(userDTO.getFirstName());
    updatedUser.setLastName(userDTO.getLastName());
    updatedUser.setEmail(userDTO.getEmail());
    updatedUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    this.locationService.updateLocation(userDTO, updatedUser);
    return userRepository.save(updatedUser);
  }
}
