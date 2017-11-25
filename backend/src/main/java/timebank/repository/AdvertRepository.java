package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Advert;

import java.util.Optional;

@Repository("advertRepository")
public interface AdvertRepository extends JpaRepository<Advert, Long> {

  Optional<Advert> findByIdAdvert(Long idAdvert);

  Iterable<Advert> findAllByUsername(String username);

}
