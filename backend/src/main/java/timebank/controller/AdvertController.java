package timebank.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import timebank.dto.AdvertDTO;
import timebank.dto.AdvertDetailsDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.AccessingPrivateResourcesException;
import timebank.exceptions.AdvertException;
import timebank.exceptions.ShowInterestException;
import timebank.model.*;
import timebank.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


@RestController
public class AdvertController {

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  @Qualifier("advertService")
  private AdvertService advertService;

  @Autowired
  @Qualifier("locationService")
  private LocationService locationService;

  @Autowired
  @Qualifier("interestedService")
  private InterestedService interestedService;

  @Autowired
  @Qualifier("categoryService")
  private CategoryService categoryService;

  private final Log log = LogFactory.getLog(getClass());


  @RequestMapping(method=GET, path="/api/adverts")
  public @ResponseBody Page<Advert> getAdverts(@RequestParam(name="type", required=false) String type,
                                               @RequestParam(name="idCategory", required=false) String idCategory,
                                               @RequestParam(name="title", required=false) String title,
                                               Pageable pageable) {
    return this.advertService.findAllByParams(type, idCategory, title, pageable);
  }

  @RequestMapping(method=GET, path="/api/categories")
  public @ResponseBody Iterable<Category> getAllCategories() {
    return this.categoryService.findAll();
  }

  @RequestMapping(method=GET, path="/api/advert/{id}")
  public @ResponseBody ResponseEntity<AdvertDetailsDTO> getAdvert(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new AdvertException("getAdvert.error.advertNotFound"));
    User employer = this.userService.findByUsername(advert.getEmployer()).orElseThrow(
      () -> new AdvertException("getAdvert.error.userNotFound"));
    Location location = this.locationService.findByIdLocation(advert.getIdLocation()).orElseThrow(
      () -> new AdvertException("getAdvert.error.locationNotFound"));
    Iterable<Interested> interested = this.interestedService.findAllByIdAdvert(advert.getIdAdvert());
    AdvertDetailsDTO advertDetails = new AdvertDetailsDTO(advert, location, interested, employer.getEmail());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getAdvert", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(advertDetails);
  }

  @RequestMapping(method=POST, path="/api/advert")
  public @ResponseBody ResponseEntity<Advert> createAdvert(@Valid @RequestBody AdvertDTO advertDTO, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.createAdvert(userSession.getUsername(), advertDTO);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "createAdvert", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(advert);
  }

  @RequestMapping(method=RequestMethod.DELETE, path="/api/deleteAdvert/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> deleteAdvert(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new AdvertException("removeAdvert.error.advertNotFound"));
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new AdvertException("removeAdvert.error.unauthorised");
    }
    this.advertService.deleteAdvert(advert.getIdAdvert(), advert.getIdLocation());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "deleteAdvert", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

//  @RequestMapping(method=PUT, path="/api/advert/{id}")
//  public @ResponseBody ResponseEntity<Advert> updateAdvert(@PathVariable("id") long idAdvert, @Valid @RequestBody AdvertDTO advertDTO, HttpSession session) throws AccessingPrivateResourcesException, AdvertException {
//    UserSession userSession = (UserSession) session.getAttribute("user");
//    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
//      () -> new AdvertException("updateAdvert.error.advertNotFound"));
//    if (!userSession.getUsername().equals(advert.getEmployer()))
//      throw new AccessingPrivateResourcesException("updateAdvert.error.unauthorised");
//    Advert updatedAdvert = this.advertService.updateAdvert(advertDTO, advert);
//    return ResponseEntity.ok(updatedAdvert);
//  }

  @RequestMapping(method=PUT, path="/api/advert/switchInterest/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> switchInterest(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("switchInterest.error.advertNotFound"));
    if (advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("switchInterest.error.youCannotSwitchInterestInYourOwnAdvert");
    }
    Optional<Interested> interested = this.interestedService.findByIdAdvertAndInterested(idAdvert, userSession.getUsername());
    interested.ifPresent(
      intrested -> this.advertService.stopShowingInterest(idAdvert, userSession.getUsername()) );
    interested.orElseGet(
      () -> this.advertService.showInterest(idAdvert, userSession.getUsername()) );

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "switchInterest", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/advert/choosePerformer")
  public @ResponseBody ResponseEntity<HttpStatus> chooseFinalPerformer(@RequestParam(name="idAdvert") long idAdvert, @RequestParam(name="performer") String performer, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.advertNotFound"));
    if (!advert.getActive()) {
      throw new ShowInterestException("chooseFinalPerformer.error.advertInactive");
    }
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("chooseFinalPerformer.error.unauthorised");
    }
    this.interestedService.findByIdAdvertAndInterested(idAdvert, performer).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.performerNotInterested"));
    this.advertService.chooseFinalPerformer(idAdvert, performer);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "chooseFinalPerformer", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=RequestMethod.DELETE, path="/api/advert/deletePerformer/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> deleteFinalPerformer(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.advertNotFound"));
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("chooseFinalPerformer.error.unauthorised");
    }
    if (advert.getActive()) {
      throw new ShowInterestException("chooseFinalPerformer.error.performerNotChosen");
    }
    this.advertService.removeFinalPerformer(idAdvert, advert.getPerformer());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "deleteFinalPerformer", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/advert/finalize/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> finalizeTransaction(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("finalizeTransaction.error.advertNotFound"));
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("finalizeTransaction.error.unauthorised");
    }
    if (advert.getActive()) {
      throw new ShowInterestException("finalizeTransaction.error.advertNotReadyForFinalization");
    }
    this.advertService.finalizeAdvert(advert);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "finalizeTransaction", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  // wystawione przez uzytkownika ogloszenia - do podgladu w profilu
  @RequestMapping(method=GET, path="/api/createdAdverts")
  public @ResponseBody Iterable<Advert> getAllCreatedAdverts(HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Iterable<Advert> createdAdverts = this.advertService.findAllByEmployer(userSession.getUsername());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getAllCreatedAdverts", (elapsedTime/Math.pow(10,9))));
    return createdAdverts;
  }

  // ogloszenia ktorymi uzytkownik jest zainteresowany - do podgladu w profilu
  @RequestMapping(method=GET, path="/api/interestingAdverts")
  public @ResponseBody Iterable<Advert> getAllInterestingAdverts(HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Iterable<Advert> interestingAdverts = this.advertService.findAllInterestingAdverts(userSession.getUsername());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getAllInterestingAdverts", (elapsedTime/Math.pow(10,9))));
    return interestingAdverts;
  }

}
