package timebank.service;

import timebank.dto.UserInfoRequest;
import timebank.model.UserInfo;

public interface UserService {

  UserInfo findByUsername(String username);

  UserInfo findByEmail(String username);

  Iterable<UserInfo> findAll();

  UserInfo createUser(UserInfoRequest userInfoRequest);

  UserInfo updateUser(UserInfoRequest userInfoRequest);

}
