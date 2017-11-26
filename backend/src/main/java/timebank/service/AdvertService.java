package timebank.service;

import timebank.dto.AdvertDTO;
import timebank.dto.LocationDTO;
import timebank.model.Advert;

import java.util.Optional;

public interface AdvertService {

  Optional<Advert> findByIdAdvert(Long idAdvert);

  Iterable<Advert> findAllByUsername(String username);

  Iterable<Advert> findAll();

  Advert createAdvert(String username, AdvertDTO advertDTO);

  Advert updateAdvert(AdvertDTO advertDTO, Advert advert);

  void showInterest(long idAdvert, String username);

}
