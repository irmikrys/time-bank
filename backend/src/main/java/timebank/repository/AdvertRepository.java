package timebank.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import timebank.model.Advert;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository("advertRepository")
public interface AdvertRepository extends JpaRepository<Advert, Long> {

  @QueryHints(forCounting = false)
  @Query(value = "SELECT * FROM adverts WHERE active AND (TYPE LIKE ?1) AND (idCategory LIKE ?2) AND (title LIKE CONCAT('%', ?3 ,'%') OR description LIKE CONCAT('%', ?3 ,'%')) \n#pageable\n",
    nativeQuery = true)
  Slice<Advert> findAdvertsByParams(String type, String idCategory, String phrase, Pageable pageable);

  Iterable<Advert> findAllByIdAdvertIn(Collection<Long> idAdverts);

  Optional<Advert> findByIdAdvert(long idAdvert);

  Iterable<Advert> findAllByOwner(String username);

  @Transactional
  void deleteByIdAdvert(long idAdvert);

}
