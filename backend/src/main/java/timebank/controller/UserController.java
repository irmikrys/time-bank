package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import timebank.model.ErrorMessage;
import timebank.model.UserCreateRequest;
import timebank.repository.UserRepository;
import timebank.model.UserInfo;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @RequestMapping(method=POST, path="/api/register")
  public @ResponseBody ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
    if (userRepository.findByUsername(userCreateRequest.getUsername()) != null) {
      return
        ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new ErrorMessage("register.error.usernameExists"));
    }
    if (userRepository.findByEmail(userCreateRequest.getEmail()) != null) {
      return
        ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new ErrorMessage("register.error.emailExists"));
    }
    UserInfo user = userCreateRequest.toUserInfo(bCryptPasswordEncoder.encode(userCreateRequest.getPassword()),"USER");
    userRepository.save(user);
    return ResponseEntity.ok(user);
  }

  @RequestMapping(path="/api/users")
  public @ResponseBody Iterable<UserInfo> getAllUsers() {
    return userRepository.findAll();
  }
}
