package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.dto.AdvertDTO;
import timebank.dto.LocationDTO;
import timebank.model.Advert;
import timebank.model.Location;
import timebank.repository.AdvertRepository;
import timebank.repository.LocationRepository;

import java.util.Optional;

@Service("advertService")
public class AdvertServiceImpl implements AdvertService {

  @Autowired
  @Qualifier("advertRepository")
  private AdvertRepository advertRepository;

  @Autowired
  @Qualifier("locationService")
  private LocationService locationService;

  @Override
  public Optional<Advert> findByIdAdvert(Long idAdvert) {
    return advertRepository.findByIdAdvert(idAdvert);
  }

  @Override
  public Iterable<Advert> findAllByUsername(String username) {
    return advertRepository.findAllByUsername(username);
  }

  @Override
  public Iterable<Advert> findAll() {
    return advertRepository.findAll();
  }

  @Override
  public Advert createAdvert(String username, AdvertDTO advertDTO) {
    Location location = locationService.createLocation(advertDTO.getLocationDTO());
    Advert advert = advertDTO.toAdvert(username, location.getIdLocation());
    return advertRepository.save(advert);
  }

  @Override
  public Advert updateAdvert(AdvertDTO advertDTO, Advert advert) {
//    advert.setTitle(advertDTO.getTitle());
//    advert.setDescription(advertDTO.getDescription());
//    return advertRepository.save(advert);
    return new Advert();
  }
}
