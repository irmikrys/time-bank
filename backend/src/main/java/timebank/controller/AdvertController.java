package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import timebank.dto.AdvertRequest;
import timebank.dto.session.UserSession;
import timebank.exceptions.AccessingPrivateResourcesException;
import timebank.model.Advert;
import timebank.service.AdvertService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class AdvertController {

  @Autowired
  private AdvertService advertService;

  @RequestMapping(method=POST, path="/api/advert")
  public @ResponseBody ResponseEntity<Advert> createAdvert(@Valid @RequestBody AdvertRequest advertRequest, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = advertService.createAdvert(advertRequest, userSession.getUsername());
    return ResponseEntity.ok(advert);
  }

  @RequestMapping(method=PUT, path="/api/advert/{id}")
  public @ResponseBody ResponseEntity<Advert> updateAdvert(@PathVariable("id") long idAdvert, @Valid @RequestBody AdvertRequest advertRequest, HttpSession session) throws AccessingPrivateResourcesException {
    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = advertService.findByIdAdvert(idAdvert);
    if (!userSession.getUsername().equals(advert.getUsername()))
      throw new AccessingPrivateResourcesException("updateAdvert.error.unauthorised");
    Advert updatedAdvert = advertService.updateAdvert(advertRequest, advert);
    return ResponseEntity.ok(updatedAdvert);
  }

  @RequestMapping(method=GET, path="/api/adverts")
  public @ResponseBody Iterable<Advert> getAllAdverts() {
    return advertService.findAll();
  }
}
