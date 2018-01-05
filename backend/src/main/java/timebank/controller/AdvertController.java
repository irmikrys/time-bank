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
import timebank.dto.AdvertDTO;
import timebank.dto.AdvertDetailsDTO;
import timebank.dto.LocationDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.AdvertException;
import timebank.exceptions.ShowInterestException;
import timebank.model.*;
import timebank.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.*;


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
    User owner = this.userService.findByUsername(advert.getOwner()).orElseThrow(
      () -> new AdvertException("getAdvert.error.userNotFound"));
    Location location = this.locationService.findByIdLocation(advert.getIdLocation()).orElseThrow(
      () -> new AdvertException("getAdvert.error.locationNotFound"));
    Iterable<Interested> interested = this.interestedService.findAllByIdAdvert(advert.getIdAdvert());
    AdvertDetailsDTO advertDetails = new AdvertDetailsDTO(advert, location, interested, owner.getEmail());

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
    if (!advert.getOwner().equals(userSession.getUsername())) {
      throw new AdvertException("removeAdvert.error.unauthorised");
    }
    this.advertService.deleteAdvert(advert.getIdAdvert(), advert.getIdLocation());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "deleteAdvert", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/updateAdvert/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> updateAdvert(@PathVariable("id") long idAdvert, @Valid @RequestBody AdvertDTO advertDTO, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new AdvertException("updateAdvert.error.advertNotFound"));
    if (!userSession.getUsername().equals(advert.getOwner()))
      throw new AdvertException("updateAdvert.error.unauthorised");
    Location location = this.locationService.findByIdLocation(advert.getIdLocation()).orElseThrow(
      () -> new AdvertException("updateAdvert.error.locationNotFound"));
    this.advertService.updateAdvert(advert, location, advertDTO);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "updateAdvert", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/advert/switchInterest/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> switchInterest(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("switchInterest.error.advertNotFound"));
    if (advert.getOwner().equals(userSession.getUsername())) {
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

  @RequestMapping(method=PUT, path="/api/advert/chooseContractor")
  public @ResponseBody ResponseEntity<HttpStatus> chooseFinalContractor(@RequestParam(name="idAdvert") long idAdvert, @RequestParam(name="contractor") String contractor, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("chooseFinalContractor.error.advertNotFound"));
    if (!advert.getActive()) {
      throw new ShowInterestException("chooseFinalContractor.error.advertInactive");
    }
    if (!advert.getOwner().equals(userSession.getUsername())) {
      throw new ShowInterestException("chooseFinalContractor.error.unauthorised");
    }
    this.interestedService.findByIdAdvertAndInterested(idAdvert, contractor).orElseThrow(
      () -> new ShowInterestException("chooseFinalContractor.error.contractorNotInterested"));
    this.advertService.chooseFinalContractor(idAdvert, contractor);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "chooseFinalContractor", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=RequestMethod.DELETE, path="/api/advert/deleteContractor/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> deleteFinalContractor(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("deleteFinalContractor.error.advertNotFound"));
    if (!advert.getOwner().equals(userSession.getUsername())) {
      throw new ShowInterestException("deleteFinalContractor.error.unauthorised");
    }
    if (advert.getActive()) {
      throw new ShowInterestException("deleteFinalContractor.error.contractorNotChosen");
    }
    this.advertService.removeFinalContractor(idAdvert, advert.getContractor());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "deleteFinalContractor", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/advert/finalize/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> finalizeTransaction(@PathVariable("id") long idAdvert, HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("finalizeTransaction.error.advertNotFound"));
    if (!advert.getOwner().equals(userSession.getUsername())) {
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

  @RequestMapping(method=GET, path="/api/createdAdverts")
  public @ResponseBody Iterable<Advert> getAllCreatedAdverts(HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Iterable<Advert> createdAdverts = this.advertService.findAllByOwner(userSession.getUsername());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getAllCreatedAdverts", (elapsedTime/Math.pow(10,9))));
    return createdAdverts;
  }

  @RequestMapping(method=GET, path="/api/interestingAdverts")
  public @ResponseBody Iterable<Advert> getAllInterestingAdverts(HttpSession session) {
    long start = System.nanoTime();

    UserSession userSession = (UserSession) session.getAttribute("user");
    Iterable<Advert> interestingAdverts = this.advertService.findAllInterestingAdverts(userSession.getUsername());

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "getAllInterestingAdverts", (elapsedTime/Math.pow(10,9))));
    return interestingAdverts;
  }

  @RequestMapping(method=POST, path="/api/updateLocation/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> updateLocation(@Valid @RequestBody LocationDTO locationDTO, @PathVariable("id") long idLocation) {
    long start = System.nanoTime();
    System.out.println("updateLocation początek: " +locationDTO.toString());

    Location oldLocation = this.locationService.findByIdLocation(idLocation).orElseThrow(
      () -> new AdvertException("updateLocation.error.locationNotFound"));
    this.locationService.updateLocation(oldLocation, locationDTO);

    long elapsedTime = System.nanoTime() - start;
    log.info(format("%s: %.10f [s]", "updateLocation", (elapsedTime/Math.pow(10,9))));
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
