package timebank.dto;

import timebank.model.Advert;
import timebank.model.Interested;
import timebank.model.Location;
import com.google.common.collect.Lists;

public class AdvertDetailsDTO {

  private Advert advert;

  private Location location;

  private Iterable<Interested> interested;

  private int interestedNumber;


  public AdvertDetailsDTO(Advert advert, Location location, Iterable<Interested> interested, boolean usernameIsAdvertCreator) {
    this.advert = advert;
    this.location = location;
    this.interested = interested;
    this.interestedNumber = Lists.newArrayList(interested).size();
    if (!usernameIsAdvertCreator) {
      this.interested = null;
    }
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

}
