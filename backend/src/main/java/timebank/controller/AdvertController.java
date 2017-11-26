package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import timebank.dto.AdvertDTO;
import timebank.dto.LocationDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.AccessingPrivateResourcesException;
import timebank.exceptions.CreateAdvertException;
import timebank.exceptions.ShowInterestException;
import timebank.model.Advert;
import timebank.model.Interested;
import timebank.service.AdvertService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class AdvertController {

  @Autowired
  @Qualifier("advertService")
  private AdvertService advertService;


  @RequestMapping(method=POST, path="/api/advert")
  public @ResponseBody ResponseEntity<Advert> createAdvert(@Valid @RequestBody AdvertDTO advertDTO, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = advertService.createAdvert(userSession.getUsername(), advertDTO);
    return ResponseEntity.ok(advert);
  }

  @RequestMapping(method=PUT, path="/api/advert/{id}")
  public @ResponseBody ResponseEntity<Advert> updateAdvert(@PathVariable("id") long idAdvert, @Valid @RequestBody AdvertDTO advertDTO, HttpSession session) throws AccessingPrivateResourcesException, CreateAdvertException {
    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new CreateAdvertException("updateAdvert.error.advertnotfound"));
    if (!userSession.getUsername().equals(advert.getUsername()))
      throw new AccessingPrivateResourcesException("updateAdvert.error.unauthorised");
    Advert updatedAdvert = advertService.updateAdvert(advertDTO, advert);
    return ResponseEntity.ok(updatedAdvert);
  }

  @RequestMapping(method=GET, path="/api/adverts")
  public @ResponseBody Iterable<Advert> getAllAdverts() {
    return advertService.findAll();
  }

  public void showInterest(long idAdvert, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("showInterest.error.advertnotfound"));
    // kiedy ogloszenie wystawil obecnie zalogowany uzytkownik
    if (advert.getUsername().equals(userSession.getUsername())) {
      throw new ShowInterestException("showInterest.error.youcannotshowinterestinyourownadvert");
    }
    advertService.showInterest(idAdvert, userSession.getUsername());
  }
}
