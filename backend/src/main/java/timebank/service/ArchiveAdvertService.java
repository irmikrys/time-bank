package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.model.Advert;
import timebank.model.ArchiveAdvert;
import timebank.repository.ArchiveAdvertRepository;

import java.util.Optional;

@Service("archiveAdvertService")
public class ArchiveAdvertService {

  @Autowired
  @Qualifier("archiveAdvertRepository")
  private ArchiveAdvertRepository archiveAdvertRepository;


  public Optional<ArchiveAdvert> findByIdAdvert(long idAdvert) {
    return this.archiveAdvertRepository.findByIdAdvert(idAdvert);
  }

  public Iterable<ArchiveAdvert> findAllByOwnerOrContractor(String username) {
    return this.archiveAdvertRepository.findAllByOwnerOrContractor(username, username);
  }

  public ArchiveAdvert createArchiveAdvert(Advert advert) {
    ArchiveAdvert archiveAdvert = advert.toArchiveAdvert();
    return this.archiveAdvertRepository.save(archiveAdvert);
  }
}
