package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import timebank.dto.EmailDTO;
import timebank.service.EmailSenderService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class EmailSenderController {

  @Autowired
  EmailSenderService emailSenderService;

  @RequestMapping(method=POST, path="/api/sendEmail")
  public @ResponseBody ResponseEntity<HttpStatus> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {
    emailSenderService.sendEmail(emailDTO.getEmail(), emailDTO.getTitle(), emailDTO.getContent());
    return ResponseEntity.ok(HttpStatus.OK);
  }
}
