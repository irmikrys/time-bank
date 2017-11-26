package timebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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

  @RequestMapping(method=GET, path="/api/adverts")
  public @ResponseBody Iterable<Advert> getAllAdverts() {
    return this.advertService.findAll();
  }

  @RequestMapping(method=GET, path="/api/categories")
  public @ResponseBody Iterable<Category> getAllCategories() {
    return this.categoryService.findAll();
  }

  @RequestMapping(method=PUT, path="/api/advert/showInterest/{id}")
  public void showInterest(@PathVariable("id") long idAdvert, HttpSession session) {
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
  }

  @RequestMapping(method=PUT, path="/api/advert/removeInterest/{id}")
  public void stopShowingInterest(@PathVariable("id") long idAdvert, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new ShowInterestException("stopShowingInterest.error.userNotFound"));
    this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("stopShowingInterest.error.advertNotFound"));
    this.advertService.stopShowingInterest(idAdvert, userSession.getUsername());
  }

  @RequestMapping(method=PUT, path="/api/advert/{id}/{performer}")
  public void chooseFinalPerformer(@PathVariable("id") long idAdvert, @PathVariable("performer") String performer, HttpSession session) {
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
  }

  @RequestMapping(method=PUT, path="/api/advert/removePerformer/{id}")
  public void removeFinalPerformer(@PathVariable("id") long idAdvert, HttpSession session) {
    UserSession userSession = (UserSession) session.getAttribute("user");
    this.userService.findByUsername(userSession.getUsername()).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.userNotFound"));
    Advert advert = this.advertService.findByIdAdvert(idAdvert).orElseThrow(
      () -> new ShowInterestException("chooseFinalPerformer.error.advertNotFound"));
    if (!advert.getEmployer().equals(userSession.getUsername())) {
      throw new ShowInterestException("chooseFinalPerformer.error.unauthorised");
    }
    this.advertService.removeFinalPerformer(idAdvert, advert.getPerformer());
  }

  @RequestMapping(method=PUT, path="/api/advert/finalize/{id}")
  public void finalizeTransaction(@PathVariable("id") long idAdvert, HttpSession session) {
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
  }

}
