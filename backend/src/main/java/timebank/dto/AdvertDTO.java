package timebank.dto;

import org.hibernate.validator.constraints.NotBlank;
import timebank.model.Advert;
import timebank.model.Location;

public class AdvertDTO {

  @NotBlank
  private String type;

  @NotBlank
  private long idCategory;

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotBlank
  private int value;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(long idCategory) {
    this.idCategory = idCategory;
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

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Advert toAdvert(String username, long idLocation) {
    Advert advert = new Advert();
    advert.setUsername(username);
    advert.setIdLocation(idLocation);
    advert.setType(getType());
    advert.setIdCategory(getIdCategory());
    advert.setTitle(getTitle());
    advert.setDescription(getDescription());
    advert.setValue(getValue());
    return advert;
  }

}
