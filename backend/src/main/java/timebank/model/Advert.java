package timebank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "adverts")
public class Advert implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "idAdvert")
  private long idAdvert;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "type")
  private String type;

  @Column(name = "owner")
  private String owner;

  @Column(name = "contractor")
  private String contractor;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "idCategory")
  private long idCategory;

  @Column(name = "value")
  private int value;

  @CreationTimestamp
  @Column(name ="createDate")
  private Date createDate;

  @Column(name = "idLocation")
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

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getContractor() {
    return contractor;
  }

  public void setContractor(String contractor) {
    this.contractor = contractor;
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

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @JsonIgnore
  public long getIdLocation() {
    return idLocation;
  }

  public void setIdLocation(long idLocation) {
    this.idLocation = idLocation;
  }

  public ArchiveAdvert toArchiveAdvert() {
    ArchiveAdvert archiveAdvert = new ArchiveAdvert();
    archiveAdvert.setType(getType());
    archiveAdvert.setOwner(getOwner());
    archiveAdvert.setContractor(getContractor());
    archiveAdvert.setTitle(getTitle());
    archiveAdvert.setDescription(getDescription());
    archiveAdvert.setIdCategory(getIdCategory());
    archiveAdvert.setValue(getValue());
    archiveAdvert.setCreateDate(getCreateDate());
    return archiveAdvert;
  }

}
