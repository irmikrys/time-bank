package timebank.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import timebank.dto.AdvertDTO;
import timebank.dto.session.UserSession;
import timebank.exceptions.AccessingPrivateResourcesException;
import timebank.exceptions.AdvertException;
import timebank.exceptions.ShowInterestException;
import timebank.model.Advert;
import timebank.model.Category;
import timebank.service.AdvertService;
import timebank.service.CategoryService;
import timebank.service.InterestedService;
import timebank.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class AdvertController {

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  @Qualifier("advertService")
  private AdvertService advertService;

  @Autowired
  @Qualifier("categoryService")
  private CategoryService categoryService;

  @Autowired
  @Qualifier("interestedService")
  private InterestedService interestedService;

  @RequestMapping(method=GET, path="/api/adverts")
  public @ResponseBody Page<Advert> getAdverts(Pageable pageable) {
    return this.advertService.findAll(pageable);
  }

  @RequestMapping(method=GET, path="/api/categories")
  public @ResponseBody Iterable<Category> getAllCategories() {
    return this.categoryService.findAll();
  }

  @RequestMapping(method=GET, path="/api/advert/{id}")
  public @ResponseBody ResponseEntity<Advert> getAdvert(@PathVariable("id") long idAdvert) {
    Advert updatedAdvert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new AdvertException("getAdvert.error.advertNotFound"));
    return ResponseEntity.ok(updatedAdvert);
  }

  @RequestMapping(method=POST, path="/api/advert")
  public @ResponseBody ResponseEntity<Advert> createAdvert(@Valid @RequestBody AdvertDTO advertDTO, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new AdvertException("createAdvert.error.userNotFound"));
    Advert advert = this.advertService.createAdvert(userSession.getUsername(), advertDTO);
    return ResponseEntity.ok(advert);
  }

  @RequestMapping(method=PUT, path="/api/advert/{id}")
  public @ResponseBody ResponseEntity<Advert> updateAdvert(@PathVariable("id") long idAdvert, @Valid @RequestBody AdvertDTO advertDTO, HttpSession session) throws AccessingPrivateResourcesException, AdvertException {
    UserSession userSession = (UserSession) session.getAttribute("user");
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new AdvertException("updateAdvert.error.advertNotFound"));
    if (!userSession.getUsername().equals(advert.getEmployer()))
      throw new AccessingPrivateResourcesException("updateAdvert.error.unauthorised");
    Advert updatedAdvert = this.advertService.updateAdvert(advertDTO, advert);
    return ResponseEntity.ok(updatedAdvert);
  }

  @RequestMapping(method=PUT, path="/api/advert/showInterest/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> showInterest(@PathVariable("id") long idAdvert, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new ShowInterestException("showInterest.error.userNotFound"));
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("showInterest.error.advertNotFound"));
    this.interestedService.findByIdAdvertAndInterested(idAdvert, userSession.getUsername()).ifPresent(
      intrested -> { throw new ShowInterestException("showInterest.error.alreadyInterested"); });
    if (advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("showInterest.error.youCannotShowInterestInYourOwnAdvert");
    }
    this.advertService.showInterest(idAdvert, userSession.getUsername());
    return ResponseEntity.ok(HttpStatus.OK);
  }

//  @RequestMapping(method=POST, path="/api/advert/showInterest2")
//  public @ResponseBody ResponseEntity<HttpStatus> showInterest(@RequestBody JSONObject jsonObject, HttpSession session) {
//    long idAdvert = ((Number) jsonObject.get("idAdvert")).longValue();
//    UserSession userSession = (UserSession) session.getAttribute("user");
//    // [..]
//    this.advertService.showInterest(idAdvert, userSession.getUsername());
//    return ResponseEntity.ok(HttpStatus.OK);
//  }

  @RequestMapping(method=PUT, path="/api/advert/removeInterest/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> stopShowingInterest(@PathVariable("id") long idAdvert, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new ShowInterestException("stopShowingInterest.error.userNotFound"));
    this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("stopShowingInterest.error.advertNotFound"));
    this.advertService.stopShowingInterest(idAdvert, userSession.getUsername());
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/advert/{id}/{performer}")
  public @ResponseBody ResponseEntity<HttpStatus> chooseFinalPerformer(@PathVariable("id") long idAdvert, @PathVariable("performer") String performer, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.userNotFound"));
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.advertNotFound"));
    if (!advert.getActive()) {
      throw new ShowInterestException("chooseFinalPerformer.error.advertInactive");
    }
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("chooseFinalPerformer.error.unauthorised");
    }
    this.advertService.chooseFinalPerformer(idAdvert, performer);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/advert/removePerformer/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> removeFinalPerformer(@PathVariable("id") long idAdvert, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.userNotFound"));
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.advertNotFound"));
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("chooseFinalPerformer.error.unauthorised");
    }
    this.advertService.removeFinalPerformer(idAdvert, advert.getPerformer());
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @RequestMapping(method=PUT, path="/api/advert/finalize/{id}")
  public @ResponseBody ResponseEntity<HttpStatus> finalizeTransaction(@PathVariable("id") long idAdvert, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new ShowInterestException("finalizeTransaction.error.userNotFound"));
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("finalizeTransaction.error.advertNotFound"));
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("finalizeTransaction.error.unauthorised");
    }
    if (advert.getActive()) {
      throw new ShowInterestException("finalizeTransaction.error.advertNotReadyForFinalization");
    }
    this.advertService.finalizeAdvert(advert);
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
