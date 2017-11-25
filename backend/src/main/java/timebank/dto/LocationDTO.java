package timebank.dto;

import org.hibernate.validator.constraints.NotBlank;
import timebank.model.Location;

public class LocationDTO {

  @NotBlank
  private String description;

  @NotBlank
  private double latitude;

  @NotBlank
  private double longitude;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public Location toLocation() {
    Location location = new Location();
    location.setDescription(getDescription());
    location.setLatitude(getLatitude());
    location.setLongitude(getLongitude());
    return location;
  }
}
