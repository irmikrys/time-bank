package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timebank.dto.AdvertRequest;
import timebank.model.Advert;
import timebank.repository.AdvertRepository;

@Service
public class AdvertServiceImpl implements AdvertService {

  @Autowired
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
  public Advert createAdvert(AdvertRequest advertRequest, String username) {
    Advert advert = advertRequest.toAdvert((username));
    return advertRepository.save(advert);
  }

  @Override
  public Advert updateAdvert(AdvertRequest advertRequest, Advert advert) {
    advert.setTitle(advertRequest.getTitle());
    advert.setDescription(advertRequest.getDescription());
    return advertRepository.save(advert);
  }
}
