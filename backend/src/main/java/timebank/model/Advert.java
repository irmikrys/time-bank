package timebank.model;

import org.hibernate.annotations.CreationTimestamp;
import timebank.model.interfaces.LocationIdHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "adverts")
public class Advert implements Serializable, LocationIdHolder {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "id_advert")
  private long idAdvert;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "type")
  private String type;

  @Column(name = "employer")
  private String employer;

  @Column(name = "performer")
  private String performer;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "id_category")
  private long idCategory;

  @Column(name = "value")
  private int value;

  @CreationTimestamp
  @Column(name ="create_date")
  private Date creationDate;

  @Column(name = "id_location")
  private long idLocation;

  public long getIdAdvert() {
    return idAdvert;
  }

  public void setIdAdvert(long idAdvert) {
    this.idAdvert = idAdvert;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getEmployer() {
    return employer;
  }

  public void setEmployer(String employer) {
    this.employer = employer;
  }

  public String getPerformer() {
    return performer;
  }

  public void setPerformer(String performer) {
    this.performer = performer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(long idCategory) {
    this.idCategory = idCategory;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public long getIdLocation() {
    return idLocation;
  }

  public void setIdLocation(long idLocation) {
    this.idLocation = idLocation;
  }

  public ArchiveAdvert toArchiveAdvert() {
    ArchiveAdvert archiveAdvert = new ArchiveAdvert();
    archiveAdvert.setType(getType());
    archiveAdvert.setEmployer(getEmployer());
    archiveAdvert.setPerformer(getPerformer());
    archiveAdvert.setTitle(getTitle());
    archiveAdvert.setDescription(getDescription());
    archiveAdvert.setIdCategory(getIdCategory());
    archiveAdvert.setValue(getValue());
    archiveAdvert.setCreationDate(getCreationDate());
    return archiveAdvert;
  }
}
