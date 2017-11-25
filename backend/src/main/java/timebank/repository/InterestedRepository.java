package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Interested;

@Repository("interestedRepository")
public interface InterestedRepository extends JpaRepository<Interested, Long> {

  Iterable<Interested> findAllByIdAdvert(long idAdvert);

  Iterable<Interested> findAllByInterested(String interested);

}
