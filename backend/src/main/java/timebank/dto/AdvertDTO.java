package timebank.dto;

import org.hibernate.validator.constraints.NotBlank;
import timebank.dto.interfaces.LocationDTOHolder;
import timebank.model.Advert;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AdvertDTO implements LocationDTOHolder {

  @NotBlank
  private String type;

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @DecimalMin(value = "0")
  @DecimalMax(value = "9223372036854775807")
  private long idCategory;

  @DecimalMin(value = "0", inclusive = false)
  @DecimalMax(value = "1000")
  private int value;

  @Valid
  private LocationDTO location;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(long idCategory) {
    this.idCategory = idCategory;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public LocationDTO getLocation() {
    return location;
  }

  public void setLocation(LocationDTO locationDTO) {
    this.location = locationDTO;
  }

  public Advert toAdvert(String username, long idLocation) {
    Advert advert = new Advert();
    advert.setActive(true);
    advert.setType(getType());
    advert.setEmployer(username);
    advert.setTitle(getTitle());
    advert.setDescription(getDescription());
    advert.setIdCategory(getIdCategory());
    advert.setValue(getValue());
    advert.setIdLocation(idLocation);
    return advert;
  }

}
