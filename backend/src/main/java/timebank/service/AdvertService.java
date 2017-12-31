package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timebank.dto.AdvertDTO;
import timebank.model.Advert;
import timebank.model.ArchiveAdvert;
import timebank.model.Location;
import timebank.repository.AdvertRepository;

import java.util.Optional;

@Service("advertService")
public class AdvertService {

  @Autowired
  @Qualifier("advertRepository")
  private AdvertRepository advertRepository;

  @Autowired
  @Qualifier("locationService")
  private LocationService locationService;

  @Autowired
  @Qualifier("interestedService")
  private InterestedService interestedService;

  @Autowired
  @Qualifier("archiveAdvertService")
  private ArchiveAdvertService archiveAdvertService;

  @Autowired
  private JdbcTemplate jdbcTemplate;


  public Optional<Advert> findByIdAdvert(long idAdvert) {
    return this.advertRepository.findByIdAdvert(idAdvert);
  }

  public Iterable<Advert> findAllByEmployer(String username) {
    return this.advertRepository.findAllByEmployer(username);
  }

  public Page<Advert> findAll(Pageable pageable) {
    return this.advertRepository.findAll(pageable);
  }

  public Advert createAdvert(String username, AdvertDTO advertDTO) {
    Location location = this.locationService.createLocation(advertDTO.getLocation());
    Advert advert = advertDTO.toAdvert(username, location.getIdLocation());
    return this.advertRepository.save(advert);
  }

  @Transactional
  void deleteAdvert(long idAdvert) {
    this.advertRepository.deleteByIdAdvert(idAdvert);
  }

  public Advert updateAdvert(AdvertDTO advertDTO, Advert advert) {
    advert.setTitle(advertDTO.getTitle());
    advert.setDescription(advertDTO.getDescription());
    advert.setIdCategory(advertDTO.getIdCategory());
    this.locationService.updateLocation(advertDTO, advert);
    return advertRepository.save(advert);
  }

  public void showInterest(long idAdvert, String username) {
    this.interestedService.showInterest(idAdvert, username);
  }

  public void stopShowingInterest(long idAdvert, String username) {
    this.interestedService.stopShowingInterest(idAdvert, username);
  }

  public void chooseFinalPerformer(long idAdvert, String performer) {
    final String sql = "UPDATE adverts a SET a.active = FALSE, a.performer = ? WHERE a.id_advert = ?";
    int result = this.jdbcTemplate.update(sql, performer, idAdvert);
//    System.out.println("->-> chooseFinalPerformer: " + result);
  }

  public void removeFinalPerformer(long idAdvert, String performer) {
    final String sql = "UPDATE adverts a SET a.active = TRUE, a.performer = NULL WHERE a.id_advert = ?";
    int result = this.jdbcTemplate.update(sql, idAdvert);
    this.stopShowingInterest(idAdvert, performer);
//    System.out.println("->-> removeFinalPerformer: " + result);
  }

  @Transactional
  public void finalizeAdvert(Advert advert) {
    // 1. stworzenie rekordu z bazy archiwum i go zapisanie
    ArchiveAdvert archiveAdvert = this.archiveAdvertService.createArchiveAdvert(advert);

    // 2. zmiana stanow kont obu uczestnikow transakcji
    String plus, minus;
    if (archiveAdvert.getType().equals("SEEK")) {
      minus = archiveAdvert.getEmployer();
      plus = archiveAdvert.getPerformer();
    } else {
      plus = archiveAdvert.getEmployer();
      minus = archiveAdvert.getPerformer();
    }
    final String sql = "UPDATE accounts a SET a.balance = (a.balance + ?) WHERE a.owner = ?";
    int resultPlus = this.jdbcTemplate.update(sql, archiveAdvert.getValue(), plus);
    int resultMinus = this.jdbcTemplate.update(sql, -archiveAdvert.getValue(), minus);
//    System.out.println("->-> wynikPlus: " + resultPlus);
//    System.out.println("->-> wynikMinus: " + resultMinus);
//    System.out.println("->-> spokojnie to nie jest ostateczna wersja :C ");

    // 3. usuwanie wszystkich niepotrzebnych rekordow z 'adverts', 'interested' i 'locations'
    this.locationService.deleteLocation(advert.getIdLocation());
    this.interestedService.deleteInterestedInAdvert(advert.getIdAdvert());
    this.deleteAdvert(advert.getIdAdvert());
  }
}
