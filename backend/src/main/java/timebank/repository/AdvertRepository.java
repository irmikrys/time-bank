package timebank.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import timebank.model.Advert;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository("advertRepository")
public interface AdvertRepository extends JpaRepository<Advert, Long> {

  @QueryHints(forCounting = false) // <<
  @Query(value = "SELECT * FROM adverts WHERE active AND (TYPE LIKE :ty) AND (idCategory LIKE :idC) AND (title LIKE CONCAT('%', :ph ,'%') OR description LIKE CONCAT('%', :ph ,'%')) \n#pageable\n",
    nativeQuery = true)
  Slice<Advert> findAdvertsByParams(@Param("ty") String type, @Param("idC") String idCategory, @Param("ph") String phrase, Pageable pageable);

  Slice<Advert> findAllByActiveTrueOrderByCreateDateDesc(Pageable pageable);

  Iterable<Advert> findAllByIdAdvertIn(Collection<Long> idAdverts);

  Optional<Advert> findByIdAdvert(long idAdvert);

  Iterable<Advert> findAllByOwner(String username);

  @Transactional
  void deleteByIdAdvert(long idAdvert);

}
