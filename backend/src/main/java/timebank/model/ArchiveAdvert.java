package timebank.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "archive")
public class ArchiveAdvert implements Serializable {
  
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "id_advert")
  private long idAdvert;
  
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
  
  @Column(name ="create_date")
  private Date creationDate;
  
  @CreationTimestamp
  @Column(name ="close_date")
  private Date closeDate;
  
  public long getIdAdvert() {
    return idAdvert;
  }
  
  public void setIdAdvert(long idAdvert) {
    this.idAdvert = idAdvert;
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
  
  public Date getCloseDate() {
    return closeDate;
  }
  
  public void setCloseDate(Date closeDate) {
    this.closeDate = closeDate;
  }
}
