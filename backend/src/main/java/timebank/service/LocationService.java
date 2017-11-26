package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timebank.dto.LocationDTO;
import timebank.model.Location;
import timebank.repository.LocationRepository;

import java.util.Optional;

@Service("locationService")
public class LocationService {

  @Autowired
  @Qualifier("locationRepository")
  private LocationRepository locationRepository;
  

  public Optional<Location> findByIdLocation(long idLocation) {
    return this.locationRepository.findByIdLocation(idLocation);
  }

  public Location createLocation(LocationDTO locationDTO) {
    Location location = locationDTO.toLocation();
    return this.locationRepository.save(location);
  }
  
  @Transactional
  public void deleteLocation(long idLocation) {
    this.locationRepository.deleteByIdLocation(idLocation);
  }
}
