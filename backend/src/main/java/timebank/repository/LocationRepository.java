package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Location;
import java.util.Optional;

@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location, Long> {

  Optional<Location> findByIdLocation(long idLocation);

}
