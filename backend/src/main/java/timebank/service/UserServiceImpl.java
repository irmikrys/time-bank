package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import timebank.dto.UserInfoRequest;
import timebank.model.User;
import timebank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User findByEmail(String username) {
    return userRepository.findByEmail(username);
  }

  @Override
  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(UserInfoRequest userInfoRequest) {
    User user = userInfoRequest.toUserInfo(bCryptPasswordEncoder.encode(userInfoRequest.getPassword()),"USER");
    return userRepository.save(user);
  }

  @Override
  public User updateUser(UserInfoRequest userInfoRequest) {
    User updatedUser = userRepository.findByUsername(userInfoRequest.getUsername());
    updatedUser.setFirstName(userInfoRequest.getFirstName());
    updatedUser.setLastName(userInfoRequest.getLastName());
    updatedUser.setEmail(userInfoRequest.getEmail());
    updatedUser.setPassword(bCryptPasswordEncoder.encode(userInfoRequest.getPassword()));
    return userRepository.save(updatedUser);
  }
}
