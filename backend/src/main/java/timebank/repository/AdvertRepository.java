package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Advert;

import java.util.Optional;

@Repository("advertRepository")
public interface AdvertRepository extends JpaRepository<Advert, Long> {

  Optional<Advert> findByIdAdvert(long idAdvert);

  Iterable<Advert> findAllByEmployer(String username);
  
  void deleteByIdAdvert(long idAdvert);

}
