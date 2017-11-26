package timebank.dto;

import org.hibernate.validator.constraints.NotBlank;
import timebank.model.Advert;
import timebank.model.Location;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AdvertDTO {

  @NotBlank
  private String type;

  @NotBlank
  private String title;

  @NotBlank
  private String description;
  
  @NotNull
  private long idCategory;

  @NotNull
  private int value;

  @Valid
  private LocationDTO locationDTO;
  
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
  
  public LocationDTO getLocationDTO() {
    return locationDTO;
  }
  
  public void setLocationDTO(LocationDTO locationDTO) {
    this.locationDTO = locationDTO;
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
