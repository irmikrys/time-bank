package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import timebank.dto.session.UserSession;
import timebank.exceptions.AccessingPrivateResourcesException;
import timebank.exceptions.RegisterException;
import timebank.dto.UserInfoRequest;
import timebank.model.UserInfo;
import timebank.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(method=POST, path="/api/register")
  public @ResponseBody ResponseEntity<UserInfo> createUser(@Valid @RequestBody UserInfoRequest userInfoRequest) throws RegisterException {
    if (userService.findByUsername(userInfoRequest.getUsername()) != null) {
      throw new RegisterException("register.error.usernameExists");
    }
    if (userService.findByEmail(userInfoRequest.getEmail()) != null) {
      throw new RegisterException("register.error.emailExists");
    }
    UserInfo userInfo = userService.createUser(userInfoRequest);
    return ResponseEntity.ok(userInfo);
  }

  @RequestMapping(method=PUT, path="/api/users")
  public @ResponseBody ResponseEntity<UserInfo> updateUser(@Valid @RequestBody UserInfoRequest userInfoRequest, HttpSession session) throws AccessingPrivateResourcesException {
    UserSession userSession = (UserSession) session.getAttribute("user");
    if (!userSession.getUsername().equals(userInfoRequest.getUsername()))
      throw new AccessingPrivateResourcesException("updateUser.error.accessDenied");
    UserInfo updatedUser = userService.updateUser(userInfoRequest);
    return ResponseEntity.ok(updatedUser);
  }

  @RequestMapping(method=GET, path="/api/users")
  public @ResponseBody Iterable<UserInfo> getAllUsers() {
    return userService.findAll();
  }
}
