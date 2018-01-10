package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import timebank.dto.AdvertDTO;
import timebank.dto.AdvertDetailsDTO;
import timebank.dto.LocalizedAdvertDTO;
import timebank.model.Advert;
import timebank.model.ArchiveAdvert;
import timebank.model.Interested;
import timebank.model.Location;
import timebank.repository.AdvertRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("advertService")
public class AdvertService {

  @Autowired
  @Qualifier("advertRepository")
  private AdvertRepository advertRepository;

  @Autowired
  @Qualifier("locationService")
  private LocationService locationService;

  @Autowired
  @Qualifier("interestedService")
  private InterestedService interestedService;

  @Autowired
  @Qualifier("archiveAdvertService")
  private ArchiveAdvertService archiveAdvertService;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private class LocalizedAdvertRowMapper implements RowMapper<LocalizedAdvertDTO> {
    @Override
    public LocalizedAdvertDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
      LocalizedAdvertDTO advert = new LocalizedAdvertDTO();
      advert.setIdAdvert(rs.getLong("idAdvert"));
      advert.setActive(rs.getBoolean("active"));
      advert.setType(rs.getString("type"));
      advert.setOwner(rs.getString("owner"));
      advert.setContractor(rs.getString("contractor"));
      advert.setTitle(rs.getString("title"));
      advert.setDescription(rs.getString("description"));
      advert.setIdCategory(rs.getLong("idCategory"));
      advert.setValue(rs.getInt("value"));
      advert.setCreateDate(rs.getTimestamp("createDate"));
      advert.setIdLocation(rs.getLong("idLocation"));
      advert.setLocationDescription(rs.getString("locDescription"));
      advert.setLatitude(rs.getDouble("latitude"));
      advert.setLongitude(rs.getDouble("longitude"));
      advert.setDistance(rs.getDouble("dist"));
      return advert;
    }
  }

  public Slice<Advert> findAllByParams(String type, String idCategory, String phrase, Pageable pageable) {
    System.out.println("AdvertService: findAllByParms [type: >" + type + "<, idcategory: >" + idCategory + "<, phrase: >" + phrase + "<, pageable: " + pageable);
    return this.advertRepository.findAdvertsByParams(type, idCategory, phrase, pageable);
  }

  public Iterable<LocalizedAdvertDTO> findAdvertsNearMe(double lat, double lon, double r) {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("lat", lat);
    parameters.addValue("lon", lon);
    parameters.addValue("r", r);
    parameters.addValue("lonRange", (r/(Math.cos(Math.toRadians(lat)))));
    final String sql = "SELECT * FROM (SELECT " +
      "DEGREES(ACOS(COS(RADIANS(:lat)) * COS(RADIANS(l.latitude)) * COS(RADIANS(:lon) - RADIANS(l.longitude)) + SIN(RADIANS(:lat)) * SIN(RADIANS(l.latitude)))) AS dist, a.*, l.description AS locDescription, l.latitude, l.longitude " +
      "FROM adverts a JOIN locations l USING (idLocation) " +
      "WHERE a.active AND l.latitude BETWEEN :lat - :r AND :lat + :r AND l.longitude BETWEEN :lon - :lonRange AND :lon + :lonRange) AS tmp " +
      "WHERE dist <= :r ORDER BY dist";
    return namedParameterJdbcTemplate.query(sql, parameters, new LocalizedAdvertRowMapper());
  }

  public Optional<Advert> findByIdAdvert(long idAdvert) {
    return this.advertRepository.findByIdAdvert(idAdvert);
  }

  public Iterable<Advert> findAllByOwner(String username) {
    return this.advertRepository.findAllByOwner(username);
  }

  public Iterable<Advert> findAllInterestingAdverts(String username) {
    Iterable<Interested> interestingList = this.interestedService.findAllByInterested(username);
    List<Long> interestingAdvertsIds = new ArrayList<>();
    for( Interested interestingAdvert : interestingList ) {
      interestingAdvertsIds.add(interestingAdvert.getIdAdvert());
    }
    return this.advertRepository.findAllByIdAdvertIn(interestingAdvertsIds);
  }

  public Advert createAdvert(String username, AdvertDTO advertDTO) {
    Location location = this.locationService.createLocation(advertDTO.getLocation());
    Advert advert = advertDTO.toAdvert(username, location.getIdLocation());
    return this.advertRepository.save(advert);
  }

  public void deleteAdvert(long idAdvert, long idLocation) {
    this.interestedService.deleteInterestedInAdvert(idAdvert);
    this.locationService.deleteLocation(idLocation);
    this.advertRepository.deleteByIdAdvert(idAdvert);
  }

  public void updateAdvert(Advert oldAdvert, Location oldLocation, AdvertDTO newAdvert) {
    final String sql = "UPDATE adverts a SET a.type = ?, a.value = ?, a.title = ?, a.description = ?, a.idCategory = ? WHERE a.idAdvert = ?";
    this.jdbcTemplate.update(sql, newAdvert.getType(), newAdvert.getValue(), newAdvert.getTitle(), newAdvert.getDescription(), newAdvert.getIdCategory(), oldAdvert.getIdAdvert());
    this.locationService.updateLocation(oldLocation, newAdvert.getLocation());
  }

  public Interested showInterest(long idAdvert, String username) {
    return this.interestedService.showInterest(idAdvert, username);
  }

  public void stopShowingInterest(long idAdvert, String username) {
    this.interestedService.stopShowingInterest(idAdvert, username);
  }

  public void chooseFinalContractor(long idAdvert, String contractor) {
    final String sql = "UPDATE adverts a SET a.active = FALSE, a.contractor = ? WHERE a.idAdvert = ?";
    this.jdbcTemplate.update(sql, contractor, idAdvert);
  }

  public void removeFinalContractor(long idAdvert) {
    final String sql = "UPDATE adverts a SET a.active = TRUE, a.contractor = NULL WHERE a.idAdvert = ?";
    this.jdbcTemplate.update(sql, idAdvert);
  }

  public void finalizeAdvert(Advert advert) {
    ArchiveAdvert archiveAdvert = this.archiveAdvertService.createArchiveAdvert(advert);
    String plus, minus;
    if (archiveAdvert.getType().equals("SEEK")) {
      minus = archiveAdvert.getOwner();
      plus = archiveAdvert.getContractor();
    } else {
      plus = archiveAdvert.getOwner();
      minus = archiveAdvert.getContractor();
    }
    final String sql = "UPDATE accounts a SET a.balance = (a.balance + ?) WHERE a.owner = ?";
    this.jdbcTemplate.update(sql, archiveAdvert.getValue(), plus);
    this.jdbcTemplate.update(sql, -archiveAdvert.getValue(), minus);
    this.deleteAdvert(advert.getIdAdvert(), advert.getIdLocation());
  }
}
