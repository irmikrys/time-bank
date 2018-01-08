package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import timebank.dto.AdvertDTO;
import timebank.dto.AdvertDetailsDTO;
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

  private class AdvertRowMapper implements RowMapper<Advert> {
    @Override
    public Advert mapRow(ResultSet rs, int rowNum) throws SQLException {
      Advert advert = new Advert();
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
      return advert;
    }
  }

  private class AdvertDetailsRowMapper implements RowMapper<AdvertDetailsDTO> {
    @Override
    public AdvertDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
      AdvertDetailsDTO advert = new AdvertDetailsDTO();
      advert.setIdAdvert(rs.getLong("a.idAdvert"));
      advert.setActive(rs.getBoolean("a.active"));
      advert.setType(rs.getString("a.type"));
      advert.setOwner(rs.getString("a.owner"));
      advert.setContractor(rs.getString("a.contractor"));
      advert.setTitle(rs.getString("a.title"));
      advert.setDescription(rs.getString("a.description"));
      advert.setIdCategory(rs.getLong("a.idCategory"));
      advert.setValue(rs.getInt("a.value"));
      advert.setCreateDate(rs.getTimestamp("a.createDate"));
      advert.setIdLocation(rs.getLong("a.idLocation"));
      advert.setLocationDescription(rs.getString("l.description"));
      advert.setLatitude(rs.getDouble("l.latitude"));
      advert.setLongitude(rs.getDouble("l.longitude"));
      return advert;
    }
  }

  public Optional<Advert> findByIdAdvert(long idAdvert) {
    return this.advertRepository.findByIdAdvert(idAdvert);
  }

  public Iterable<Advert> findAllByOwner(String username) {
    return this.advertRepository.findAllByOwner(username);
  }

  public Iterable<Advert> findAllInterestingAdverts(String username) {
    // lista obiektow Intrested ktorymi user jest zainteresowany
    Iterable<Interested> interestingList = this.interestedService.findAllByInterested(username);

    // lista id advertow ktorymi user jest zainteresowany (przejscie z listy Interested na long)
    List<Long> interestingAdvertsIds = new ArrayList<Long>();
    for( Interested interestingAdvert : interestingList ) {
      interestingAdvertsIds.add(interestingAdvert.getIdAdvert());
    }

//    System.out.println(interestingAdvertsIds.toString());

    // lista advertow o danych id
    List<Advert> interestingAdvertsResult = new ArrayList<Advert>();
    if (!interestingAdvertsIds.isEmpty()) {
      NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
      MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("advertsID", interestingAdvertsIds);
      interestingAdvertsResult = namedParameterJdbcTemplate.query("SELECT * FROM adverts WHERE idAdvert IN (:advertsID)", parameters, new AdvertRowMapper());
    }
    return interestingAdvertsResult;
  }

  public Page<Advert> findAll(Pageable pageable) {
    return this.advertRepository.findAll(pageable);
  }

  public Page<Advert> findAllByParams(String type, String idCategory, String title, Pageable pageable) {
    return this.advertRepository.findAdvertsByParams(type, idCategory, title, pageable);
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
