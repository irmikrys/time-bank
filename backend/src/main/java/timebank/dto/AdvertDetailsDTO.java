package timebank.dto;

import timebank.model.Advert;
import timebank.model.Interested;
import timebank.model.Location;
import com.google.common.collect.Lists;

import java.sql.Timestamp;
import java.util.ArrayList;

public class AdvertDetailsDTO {

  private Advert advert;

  private Location location;

  private Iterable<Interested> interested;

  private int interestedNumber;

  private String userEmail;


  public AdvertDetailsDTO() {
    this.advert = new Advert();
    this.location = new Location();
    this.interested = new ArrayList<>();
    this.interestedNumber = 0;
  }

  public AdvertDetailsDTO(Advert advert, Location location, Iterable<Interested> interested, String email) {
  public AdvertDetailsDTO(Advert advert, Location location, Iterable<Interested> interested) {
    this.advert = advert;
    this.location = location;
    this.interested = interested;
    this.interestedNumber = Lists.newArrayList(interested).size();
    this.userEmail = email;
  }

  public Advert getAdvert() {
    return advert;
  }

  public Location getLocation() {
    return location;
  }

  public Iterable<Interested> getInterestedList() {
    return interested;
  }

  public int getListSize() {
    return interestedNumber;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setInterested(Iterable<Interested> interested) {
    this.interested = interested;
    this.interestedNumber = Lists.newArrayList(interested).size();
  }

  public void setActive(boolean active) {
    this.advert.setActive(active);
  }

  public void setIdAdvert(long idAdvert) {
    this.advert.setIdAdvert(idAdvert);
  }

  public void setType(String type) {
    this.advert.setType(type);
  }

  public void setEmployer(String employer) {
    this.advert.setEmployer(employer);
  }

  public void setPerformer(String performer) {
    this.advert.setPerformer(performer);
  }

  public void setTitle(String title) {
    this.advert.setTitle(title);
  }

  public void setDescription(String description) {
    this.advert.setDescription(description);
  }

  public void setIdCategory(long idCategory) {
    this.advert.setIdCategory(idCategory);
  }

  public void setValue(int value) {
    this.advert.setValue(value);
  }

  public void setCreateDate(Timestamp createDate) {
    this.advert.setCreateDate(createDate);
  }

  public void setIdLocation(long idLocation) {
    this.advert.setIdLocation(idLocation);
    this.location.setIdLocation(idLocation);
  }

  public void setLocationDescription(String locationDescription) {
    this.location.setDescription(locationDescription);
  }

  public void setLatitude(Double latitude) {
    this.location.setLatitude(latitude);
  }

  public void setLongitude(Double longitude) {
    this.location.setLongitude(longitude);
  }

}
