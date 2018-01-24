package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import timebank.dto.UserDTO;
import timebank.exceptions.UserException;
import timebank.model.Location;
import timebank.model.User;
import timebank.repository.UserRepository;

import java.util.Arrays;
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
    Location location = this.locationService.createLocation(userDTO.getLocation());
    User user = userDTO.toUser(this.bCryptPasswordEncoder.encode(userDTO.getPassword()),"USER", location.getIdLocation());
    this.accountService.createAccount(userDTO.getUsername());
    return this.userRepository.save(user);
  }

  public void updateUser(User oldUserData, Location oldLocation, UserDTO newUserData) {
    if (!this.bCryptPasswordEncoder.matches(newUserData.getPassword(), oldUserData.getPassword())) {
      throw new UserException("updateUser.error.incorrectPassword");
    }
    if (!((oldUserData.getEmail().equals(newUserData.getEmail())) && (oldUserData.getFirstName().equals(newUserData.getFirstName())) && (oldUserData.getLastName().equals(newUserData.getLastName())))) {
      final String sql = "UPDATE users u SET u.email = ?, u.firstName = ?, u.lastName = ? WHERE u.username = ?";
      this.jdbcTemplate.update(sql, newUserData.getEmail(), newUserData.getFirstName(), newUserData.getLastName(), oldUserData.getUsername());
    }
    this.locationService.updateLocation(oldLocation, newUserData.getLocation());
  }

  public void updateUserProfilePhoto(User user, byte[] profilePhoto) {
    if (!Arrays.equals(user.getPhoto(), profilePhoto)) {
      final String sql = "UPDATE users u SET u.photo = ? WHERE u.username = ?";
      this.jdbcTemplate.update(sql, profilePhoto, user.getUsername());
    }
  }

}
