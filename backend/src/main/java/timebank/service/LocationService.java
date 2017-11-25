package timebank.service;

import timebank.dto.LocationDTO;
import timebank.model.Location;

import java.util.Optional;

public interface LocationService {

  Optional<Location> findByIdLocation(long idLocation);

  Location createLocation(LocationDTO locationDTO);

}
