package timebank.service;

import timebank.dto.UserInfoRequest;
import timebank.model.User;

public interface UserService {

  User findByUsername(String username);

  User findByEmail(String username);

  Iterable<User> findAll();

  User createUser(UserInfoRequest userInfoRequest);

  User updateUser(UserInfoRequest userInfoRequest);

}
