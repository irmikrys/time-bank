package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import timebank.dto.UserDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.AccessingPrivateResourcesException;
import timebank.exceptions.RegisterException;
import timebank.model.User;
import timebank.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class UserController {

  @Autowired
  @Qualifier("userService")
  private UserService userService;


  @RequestMapping(method=POST, path="/api/register")
  public @ResponseBody ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws RegisterException {
    this.userService.findByUsername(userDTO.getUsername()).ifPresent(
      user -> { throw new RegisterException("register.error.usernameExists"); });
    this.userService.findByEmail((userDTO.getEmail())).ifPresent(
      user -> { throw new RegisterException("register.error.emailExists"); });
    User user = this.userService.createUser(userDTO);
    return ResponseEntity.ok(user);
  }

  @RequestMapping(method=PUT, path="/api/users")
  public @ResponseBody ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO userDTO, HttpSession session) throws AccessingPrivateResourcesException {
    UserSession userSession = (UserSession) session.getAttribute("user");
    if (!userSession.getUsername().equals(userDTO.getUsername()))
      throw new AccessingPrivateResourcesException("updateUser.error.accessDenied");
    User updatedUser = this.userService.updateUser(userDTO);
    return ResponseEntity.ok(updatedUser);
  }

  @RequestMapping(method=GET, path="/api/users")
  public @ResponseBody Iterable<User> getAllUsers() {
    return this.userService.findAll();
  }

  @RequestMapping(method=GET, path="/api/profile")
  public @ResponseBody ResponseEntity<User> getUserProfile(HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    return ResponseEntity.ok(this.userService.findByUsername(userSession.getUsername()).get());
  }

  @RequestMapping(method=GET, path="/api/profile/{username}")
  public @ResponseBody ResponseEntity<User> getUserProfile(@PathVariable("username") String username) {
    return ResponseEntity.ok(this.userService.findByUsername(username).get());
  }

}
