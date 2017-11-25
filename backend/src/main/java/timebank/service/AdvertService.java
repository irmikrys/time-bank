package timebank.service;

import timebank.dto.AdvertDTO;
import timebank.model.Advert;

public interface AdvertService {

  Advert findByIdAdvert(Long idAdvert);

  Iterable<Advert> findAllByUsername(String username);

  Iterable<Advert> findAll();

  Advert createAdvert(AdvertDTO advertDTO, String username);

  Advert updateAdvert(AdvertDTO advertDTO, Advert advert);

}
