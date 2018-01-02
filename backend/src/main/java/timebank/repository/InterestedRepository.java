package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Interested;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository("interestedRepository")
public interface InterestedRepository extends JpaRepository<Interested, Long> {

  Optional<Interested> findByIdAdvertAndInterested(long idAdvert, String interested);

  Iterable<Interested> findAllByIdAdvert(long idAdvert);

  Iterable<Interested> findAllByInterested(String interested);

  @Transactional
  void deleteByIdAdvertAndInterested(long idAdvert, String interested);

  @Transactional
  void deleteByIdAdvert(long idAdvert);

}
