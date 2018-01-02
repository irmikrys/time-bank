package timebank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Advert;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository("advertRepository")
public interface AdvertRepository extends PagingAndSortingRepository<Advert, Integer> {

  @Query(value = "SELECT * FROM adverts WHERE (?1 IS NULL OR ?1='' OR TYPE = ?1) AND (?2 IS NULL OR ?2='' OR idCategory = ?2) AND (?3 IS NULL OR ?3='' OR title LIKE CONCAT('%', ?3 ,'%')) \n#pageable\n",
    countQuery = "SELECT COUNT(*) FROM adverts WHERE (?1 IS NULL OR ?1='' OR TYPE = ?1) AND (?2 IS NULL OR ?2='' OR idCategory = ?2) AND (?3 IS NULL OR ?3='' OR title LIKE CONCAT('%', ?3 ,'%')) \n#pageable\n",
    nativeQuery = true)
  Page<Advert> findAdvertsByParams(String type, String idCategory, String title, Pageable pageable);

  Optional<Advert> findByIdAdvert(long idAdvert);

  Iterable<Advert> findAllByEmployer(String username);

  @Transactional
  void deleteByIdAdvert(long idAdvert);

}
