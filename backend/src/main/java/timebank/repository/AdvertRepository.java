package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Advert;

@Repository("advertRepository")
public interface AdvertRepository extends JpaRepository<Advert, Long> {

  Advert findByIdAdvert(Long idAdvert);

  Iterable<Advert> findAllByUsername(String username);

}
