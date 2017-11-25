package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.dto.LocationDTO;
import timebank.model.Location;
import timebank.repository.LocationRepository;

import java.util.Optional;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

  @Autowired
  @Qualifier("locationRepository")
  private LocationRepository locationRepository;

  @Override
  public Optional<Location> findByIdLocation(long idLocation) {
    return locationRepository.findByIdLocation(idLocation);
  }

  @Override
  public Location createLocation(LocationDTO locationDTO) {
    Location location = locationDTO.toLocation();
    return locationRepository.save(location);
  }
}
