package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import timebank.dto.UserDTO;
import timebank.dto.UserDetailsDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.*;
import timebank.model.ArchiveAdvert;
import timebank.model.User;
import timebank.service.ArchiveAdvertService;
import timebank.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class UserController {

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  @Qualifier("archiveAdvertService")
  private ArchiveAdvertService archiveAdvertService;


  @RequestMapping(method=POST, path="/api/register")
  public @ResponseBody ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws RegisterException {
    this.userService.findByUsername(userDTO.getUsername()).ifPresent(
      user -> { throw new RegisterException("register.error.usernameExists"); });
    this.userService.findByEmail((userDTO.getEmail())).ifPresent(
      user -> { throw new RegisterException("register.error.emailExists"); });
    User user = this.userService.createUser(userDTO);
    return ResponseEntity.ok(user);
  }

//  @RequestMapping(method=PUT, path="/api/users")
//  public @ResponseBody ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO userDTO, HttpSession session) throws AccessingPrivateResourcesException {
//    UserSession userSession = (UserSession) session.getAttribute("user");
//    if (!userSession.getUsername().equals(userDTO.getUsername()))
//      throw new AccessingPrivateResourcesException("updateUser.error.accessDenied");
//    User updatedUser = this.userService.updateUser(userDTO);
//    return ResponseEntity.ok(updatedUser);
//  }

  @RequestMapping(method=GET, path="/api/users")
  public @ResponseBody Iterable<User> getAllUsers() {
    return this.userService.findAll();
  }

  @RequestMapping(method=GET, path="/api/profile")
  public @ResponseBody ResponseEntity<UserDetailsDTO> getUserProfile(HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new UserException("profile.error.userNotFound"));
    return ResponseEntity.ok(this.userService.findByUsernameUserDetails(userSession.getUsername()));
  }

  @RequestMapping(method=GET, path="/api/profile/{username}")
  public @ResponseBody ResponseEntity<UserDetailsDTO> getUserProfile(@PathVariable("username") String username) {
    this.userService.findByUsername(username).orElseThrow(
      () -> new UserException("profile.error.userNotFound"));
    return ResponseEntity.ok(this.userService.findByUsernameUserDetails(username));
  }

  @RequestMapping(method=GET, path="/api/archive")
  public @ResponseBody Iterable<ArchiveAdvert> getArchive(HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new UserException("getArchiveAdverts.error.userNotFound"));
    return this.archiveAdvertService.findAllByEmployerOrPerformer(userSession.getUsername());
  }

}
