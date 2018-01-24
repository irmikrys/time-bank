package timebank.dto;

import timebank.model.Advert;
import timebank.model.Interested;
import timebank.model.Location;

import java.util.ArrayList;

public class AdvertDetailsDTO {

  private Advert advert;

  private Location location;

  private Iterable<Interested> interested;

  private String userEmail;


  public AdvertDetailsDTO() {
    this.advert = new Advert();
    this.location = new Location();
    this.interested = new ArrayList<>();
  }

  public AdvertDetailsDTO(Advert advert, Location location, Iterable<Interested> interested, String email) {
    this.advert = advert;
    this.location = location;
    this.interested = interested;
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

  public String getUserEmail() {
    return userEmail;
  }

}
