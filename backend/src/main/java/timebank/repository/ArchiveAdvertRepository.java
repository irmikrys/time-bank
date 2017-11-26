package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.ArchiveAdvert;
import java.util.Optional;

@Repository("archiveAdvertRepository")
public interface ArchiveAdvertRepository extends JpaRepository<ArchiveAdvert, Long> {
  
  Optional<ArchiveAdvert> findByIdAdvert(long idAdvert);
  
  Iterable<ArchiveAdvert> findAllByEmployerOrPerformer(String employer, String performer);
  
}
