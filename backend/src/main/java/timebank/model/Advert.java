package timebank.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="adverts")
public class Advert implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "id_advert")
  private long idAdvert;

  @Column(name ="username")
  private String username;

  @Column(name ="title")
  private String title;

  @Column(name ="description")
  private String description;

  @CreationTimestamp
  @Column(name ="date")
  private Date date;

  public long getIdAdvert() {
    return idAdvert;
  }

  public void setIdAdvert(long idAdvert) {
    this.idAdvert = idAdvert;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
