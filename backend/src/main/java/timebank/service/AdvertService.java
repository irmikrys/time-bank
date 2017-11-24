package timebank.service;

import timebank.dto.AdvertRequest;
import timebank.model.Advert;

public interface AdvertService {

  Advert findByIdAdvert(Long idAdvert);

  Iterable<Advert> findAllByUsername(String username);

  Iterable<Advert> findAll();

  Advert createAdvert(AdvertRequest advertRequest, String username);

  Advert updateAdvert(AdvertRequest advertRequest, Advert advert);

}
