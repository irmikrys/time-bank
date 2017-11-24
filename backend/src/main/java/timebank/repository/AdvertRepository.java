package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import timebank.model.Advert;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

  Advert findByIdAdvert(Long idAdvert);

  Iterable<Advert> findAllByUsername(String username);

}
