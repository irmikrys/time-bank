package timebank.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import timebank.dto.UserDTO;
import timebank.dto.UserDetailsDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.*;
import timebank.model.Account;
import timebank.model.ArchiveAdvert;
import timebank.model.Location;
import timebank.model.User;
import timebank.service.AccountService;
import timebank.service.ArchiveAdvertService;
import timebank.service.LocationService;
import timebank.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class UserController {

  @Autowired
  @Qualifier("accountService")
  private AccountService accountService;

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  @Qualifier("locationService")
  private LocationService locationService;

  @Autowired
  @Qualifier("archiveAdvertService")
  private ArchiveAdvertService archiveAdvertService;

  private final Log log = LogFactory.getLog(getClass());


  @RequestMapping(method=POST, path="/api/register")
  public @ResponseBody ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws RegisterException {
    long start = System.nanoTime();

    this.userService.findByUsername(userDTO.getUsername()).ifPresent(
      user -> { throw new RegisterException("register.error.usernameExists"); });
    this.userService.findByEmail((userDTO.getEmail())).ifPresent(
      user -> { throw new RegisterException("register.error.emailExists"); });
    User user = this.userService.createUser(userDTO);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "createUser", (elapsedTime/Math.pow(10,9))));
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

  private UserDetailsDTO getProfileDetails(String username) {
    User user = this.userService.findByUsername(username).orElseThrow(
      () -> new UserException("profile.error.userNotFound"));
    Location location = this.locationService.findByIdLocation(user.getIdLocation()).orElseThrow(
      () -> new UserException("profile.error.locationNotFound"));
    Account account = this.accountService.findByOwner(user.getUsername()).orElseThrow(
      () -> new UserException("profile.error.accountNotFound"));
    return new UserDetailsDTO(user, location, account);
  }

  @RequestMapping(method=GET, path="/api/profile")
  public @ResponseBody ResponseEntity<UserDetailsDTO> getUserProfile(HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    UserDetailsDTO userDetails = this.getProfileDetails(userSession.getUsername());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getUserProfile", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(userDetails);
  }

  @RequestMapping(method=GET, path="/api/profile/{username}")
  public @ResponseBody ResponseEntity<UserDetailsDTO> getUserProfile(@PathVariable("username") String username) {
    long start = System.nanoTime();

    UserDetailsDTO userDetails = this.getProfileDetails(username);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getUserProfile", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(userDetails);
  }

  @RequestMapping(method=GET, path="/api/archive")
  public @ResponseBody Iterable<ArchiveAdvert> getArchive(HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Iterable<ArchiveAdvert> archive = this.archiveAdvertService.findAllByEmployerOrPerformer(userSession.getUsername());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getArchive", (elapsedTime/Math.pow(10,9))));
    return archive;
  }

  @RequestMapping(value = "/api/uploadProfilePhoto", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<HttpStatus> uploadProfilePhoto(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
    UserSession userSession = (UserSession) session.getAttribute("user");
    User user = this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new UserException("profile.error.userNotFound"));
    if (!file.isEmpty()) {
      this.userService.setProfilePhoto(user, file.getBytes());
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
