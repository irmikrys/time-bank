package timebank.dto;

import org.hibernate.validator.constraints.NotBlank;
import timebank.model.Advert;

public class AdvertRequest {

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  public String getTitle() { return title; }

  public void setTitle(String title) { this.title = title; }

  public String getDescription() { return description; }

  public void setDescription(String description) { this.description = description; }

  public Advert toAdvert(String username) {
    Advert advert = new Advert();
    advert.setUsername(username);
    advert.setTitle(getTitle());
    advert.setDescription(getDescription());
    return advert;
  }

}
