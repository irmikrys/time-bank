package timebank.dto;

import timebank.model.Advert;
import timebank.model.Location;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LocalizedAdvertDTO {

  private Advert advert;

  private Location location;

  private double distance;

  public LocalizedAdvertDTO() {
    this.advert = new Advert();
    this.location = new Location();
  }

  public Advert getAdvert() {
    return advert;
  }

  public Location getLocation() {
    return location;
  }

  public double getDistance() {
    return distance;
  }

  public void setIdAdvert(long idAdvert) {
    this.advert.setIdAdvert(idAdvert);
  }

  public void setActive(boolean active) {
    this.advert.setActive(active);
  }

  public void setType(String type) {
    this.advert.setType(type);
  }

  public void setOwner(String owner) {
    this.advert.setOwner(owner);
  }

  public void setContractor(String contractor) {
    this.advert.setContractor(contractor);
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

  public void setDistance(double distance) {
    this.distance = distance;
  }

  @Override
  public String toString() {
    return "A_" + advert.getIdAdvert() +
      "-L_" + location.getIdLocation() + "[" + location.getDescription() + ", lat = " + location.getLatitude() + ", lon = " + location.getLongitude() + "], dist: "
      + new BigDecimal(111.045 * distance).setScale(4, BigDecimal.ROUND_HALF_UP) + " km";
  }
}
