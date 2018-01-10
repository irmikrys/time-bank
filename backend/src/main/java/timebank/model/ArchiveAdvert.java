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
  @Column(name = "idAdvert")
  private long idAdvert;

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

  @Column(name ="createDate")
  private Date createDate;

  @CreationTimestamp
  @Column(name ="closeDate")
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

  public Date getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(Date closeDate) {
    this.closeDate = closeDate;
  }
}
