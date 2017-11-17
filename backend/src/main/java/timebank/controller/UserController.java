package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import timebank.model.Credentials;
import timebank.repository.UserRepository;
import timebank.model.UserInfo;


@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @RequestMapping(method=POST, path="/api/register")
  public @ResponseBody String createUser(@RequestBody Credentials credentials) {
    UserInfo user = new UserInfo();
    user.setUsername(credentials.getUsername());
    user.setPassword(bCryptPasswordEncoder.encode(credentials.getPassword()));
    user.setRole("USER");
    userRepository.save(user);
    return "Saved";
  }

  @RequestMapping(path="/api/users")
  public @ResponseBody Iterable<UserInfo> getAllUsers() {
    return userRepository.findAll();
  }
}
