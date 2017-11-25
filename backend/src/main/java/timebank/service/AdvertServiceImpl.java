package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.dto.AdvertDTO;
import timebank.model.Advert;
import timebank.repository.AdvertRepository;

@Service("advertService")
public class AdvertServiceImpl implements AdvertService {

  @Autowired
  @Qualifier("advertRepository")
  private AdvertRepository advertRepository;

  @Override
  public Advert findByIdAdvert(Long idAdvert) {
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
  public Advert createAdvert(AdvertDTO advertDTO, String username) {
    Advert advert = advertDTO.toAdvert((username));
    return advertRepository.save(advert);
  }

  @Override
  public Advert updateAdvert(AdvertDTO advertDTO, Advert advert) {
    advert.setTitle(advertDTO.getTitle());
    advert.setDescription(advertDTO.getDescription());
    return advertRepository.save(advert);
  }
}
