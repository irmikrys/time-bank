package timebank.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import timebank.model.Location;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LocationDTO {

  @NotBlank
  @Size(min=3, max=60)
  private String description;

  @NotNull
  private double latitude;

  @NotNull
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

  @Override
  public String toString() {
    return "LocationDTO{" +
      "description='" + description + '\'' +
      ", latitude=" + latitude +
      ", longitude=" + longitude +
      '}';
  }
}
