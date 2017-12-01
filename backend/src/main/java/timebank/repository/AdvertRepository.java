package timebank.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Advert;

import java.util.Optional;

@Repository("advertRepository")
public interface AdvertRepository extends PagingAndSortingRepository<Advert, Integer> {

  Optional<Advert> findByIdAdvert(long idAdvert);

  Iterable<Advert> findAllByEmployer(String username);

  void deleteByIdAdvert(long idAdvert);

}
