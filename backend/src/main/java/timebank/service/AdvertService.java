package timebank.service;

import timebank.dto.AdvertDTO;
import timebank.model.Advert;

import java.util.Optional;

public interface AdvertService {

  Advert findByIdAdvert(Long idAdvert);

  Iterable<Advert> findAllByUsername(String username);

  Iterable<Advert> findAll();

  Advert createAdvert(AdvertDTO advertDTO, String username);

  Advert updateAdvert(AdvertDTO advertDTO, Advert advert);

}
