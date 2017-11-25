package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import timebank.dto.LocationDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.AccessingPrivateResourcesException;
import timebank.exceptions.RegisterException;
import timebank.dto.UserDTO;
import timebank.model.User;
import timebank.service.LocationService;
import timebank.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class UserController {

  @Autowired
  @Qualifier("userService")
  private UserService userService;


  @RequestMapping(method=POST, path="/api/register")
  public @ResponseBody ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws RegisterException {
    userService.findByUsername(userDTO.getUsername()).ifPresent(
      user -> { throw new RegisterException("register.error.usernameExists"); });
    userService.findByEmail((userDTO.getEmail())).ifPresent(
      user -> { throw new RegisterException("register.error.emailExists"); });
    User user = userService.createUser(userDTO);
    return ResponseEntity.ok(user);
  }

  @RequestMapping(method=PUT, path="/api/users")
  public @ResponseBody ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO userDTO, HttpSession session) throws AccessingPrivateResourcesException {
    UserSession userSession = (UserSession) session.getAttribute("user");
    if (!userSession.getUsername().equals(userDTO.getUsername()))
      throw new AccessingPrivateResourcesException("updateUser.error.accessDenied");
    User updatedUser = userService.updateUser(userDTO);
    return ResponseEntity.ok(updatedUser);
  }

  @RequestMapping(method=GET, path="/api/users")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userService.findAll();
  }
}
